package liuLZmod.patches.Card;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.monsters.abstracrt.abstract_llz_jiXie;

/**
 * CardPatches:
 * 卡牌补丁。
 * 对卡牌 use方法进行注入。
 * 效果：在卡牌使用后，执行机械充能函数
 */
public class CardPatch {

    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class usePatch {
        public usePatch() {
        }


        public static void Prefix(AbstractPlayer player, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            abstract_llz_jiXie.addEnergy(c.cost);
        }
    }
}
