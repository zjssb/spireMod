package liuLZmod.patches.Card;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BottledFlame;
import com.megacrit.cardcrawl.relics.BottledLightning;
import com.megacrit.cardcrawl.relics.BottledTornado;
import liuLZmod.modcore.liuLZMod;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.relics.llz_Els;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * CardPatches:
 * 卡牌补丁。
 */
public class CardPatch {
    private static final Set<Class<? extends AbstractRelic>> EXCLUDED_RELICS = new HashSet<>();

    static {
        EXCLUDED_RELICS.add(BottledFlame.class);
        EXCLUDED_RELICS.add(BottledLightning.class);
        EXCLUDED_RELICS.add(BottledTornado.class);
    }
    private static boolean shouldGiveRandomRelic = false;
    private static boolean shouldGiveSpecificRelic = false;

    /**
     * 对卡牌 use方法进行注入。
     * 效果：在卡牌使用后，执行机械充能函数
     */
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class usePatch {
        public usePatch() {
        }

        public static void Postfix(AbstractPlayer player, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            if (c.purgeOnUse) {
                return;
            }
            int cost =0;
            if (c.cost >0 && !c.freeToPlayOnce)cost  = c.costForTurn;
            else if (c.cost == -1) {
                // 对x牌单独判断
                cost = c.energyOnUse;
            }
            abstract_llz_jiXie.addEnergy(cost);
        }
    }

    /**
     * 卡牌获得时效果
     */

    @SpirePatch(clz = AbstractRelic.class, method = "onObtainCard")
    public static class addCardPatch {
        static int count = 0;

        public static void Prefix(AbstractRelic a, AbstractCard c) {
            if (count == 0) {
                count = AbstractDungeon.player.relics.size();
            }
            count--;
            if (count == 0) {
                if (c.hasTag(liuLZMod.ZZBX)) {
                    AbstractDungeon.player.gainGold(c.magicNumber);
                    shouldGiveRandomRelic = true;
                } else if (c.hasTag(liuLZMod.CZBB)) {
                    handleSpecificRelic();
                }
            }
        }

        private static void handleSpecificRelic() {
            AbstractRelic existingRelic = AbstractDungeon.player.getRelic(llz_Els.ID);
            if (existingRelic != null) {
                existingRelic.counter++;
                existingRelic.flash();
            } else {
                shouldGiveSpecificRelic = true;
            }
        }
    }
    //给遗物
    @SpirePatch(clz = AbstractDungeon.class, method = "update")
    public static class DungeonUpdatePatch {
        public static void Postfix() {
            if (shouldGiveRandomRelic) {
                shouldGiveRandomRelic = false;
                giveRandomRelic();
            }
            if (shouldGiveSpecificRelic) {
                shouldGiveSpecificRelic = false;
                giveSpecificRelic();
            }
        }

        public static void giveRandomRelic() {
            AbstractRelic relic;
            do {
                relic = AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
            } while (EXCLUDED_RELICS.contains(relic.getClass()));
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, relic);
        }

        public static void giveSpecificRelic() {
            AbstractRelic relic = new llz_Els();
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, relic);
        }
    }
}
