package liuLZmod.patches.Card;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;

import java.util.Objects;

/**
 * CardPatches:
 * 卡牌补丁。
 */
public class CardPatch {
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
            int cost = c.costForTurn;
            if (c.cost == -1) {
                // 对x牌单独判断
                cost = c.energyOnUse;
            }
            abstract_llz_jiXie.addEnergy(cost);
        }
    }

    //    AbstractDungeon.effectList.add
    @SpirePatch(clz = AbstractRelic.class, method = "onObtainCard")
    public static class addCradPatch {
        static int count = 0;

        public static void Prefix(AbstractRelic a, AbstractCard c) {
            if(count==0){
                count = AbstractDungeon.player.relics.size();
            }
            count--;
            if(count==0){
                // 填写对应代码
                if(c.cardID == "llz_zuzbx"){AbstractDungeon.player.gainGold(c.magicNumber);}
                //System.out.println("测试");
            }


        }
    }
}
