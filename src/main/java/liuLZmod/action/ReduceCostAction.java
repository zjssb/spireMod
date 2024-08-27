package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import liuLZmod.patches.Card.CardCostPatch;

public class ReduceCostAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    private int reduceAmount;

    public ReduceCostAction(int reduceAmount) {
        this.p = AbstractDungeon.player;
        this.reduceAmount = reduceAmount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() == 1) {
                AbstractCard c = this.p.hand.getTopCard();
                applyCostReduction(c);
                this.p.hand.moveToDiscardPile(c);
                this.isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                applyCostReduction(c);

                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }

    private void applyCostReduction(AbstractCard card) {
        int currentCost = card.cost;
        if (currentCost > 0) {
            int currentReduction = CardCostPatch.CostReductionField.costReduction.get(card);
            CardCostPatch.CostReductionField.costReduction.set(card, currentReduction + reduceAmount);
            card.modifyCostForCombat(-reduceAmount);
            card.applyPowers();
        }
    }
}
