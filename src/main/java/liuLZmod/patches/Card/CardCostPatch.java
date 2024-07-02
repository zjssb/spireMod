package liuLZmod.patches.Card;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CardCostPatch {

    // 给 AbstractCard 类添加一个新的属性 costReduction
    @SpirePatch(clz = AbstractCard.class, method = SpirePatch.CLASS)
    public static class CostReductionField {
        public static SpireField<Integer> costReduction = new SpireField<>(() -> 0);
    }

    // 修改卡牌费用
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class UseCardPatch {
        public static void Postfix(AbstractPlayer __instance, AbstractCard card, AbstractMonster monster, int energyOnUse) {
            int reduction = CostReductionField.costReduction.get(card);
            card.modifyCostForCombat(reduction);


            CostReductionField.costReduction.set(card, 0);
            card.isCostModified = false;
            card.applyPowers();
            card.initializeDescription();
        }
    }

    // 实现减费效果叠加
    @SpirePatch(clz = AbstractPlayer.class, method = "bottledCardUpgradeCheck")
    public static class AddCardToHandPatch {
        public static void Postfix(AbstractPlayer __instance, AbstractCard card) {
            if (card != null) {
                int currentReduction = CostReductionField.costReduction.get(card);
                CostReductionField.costReduction.set(card, currentReduction + 1);
            }
        }
    }
}
