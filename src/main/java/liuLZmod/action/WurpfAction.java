package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

import java.util.ArrayList;

public class WurpfAction extends AbstractGameAction {
    private boolean cardAddedToDrawPile = false;
    private boolean cardAddedToHand = false;
    private boolean cardAddedToDiscardPile = false;

    public WurpfAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> choices = generateCardChoices();

            if (!choices.isEmpty()) {
                AbstractCard card1 = choices.get(0);
                AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card1, true, true));

                if (choices.size() > 1) {
                    AbstractCard card2 = choices.get(1);
                    if (AbstractDungeon.player.hand.size() < 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(card2));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(card2));
                    }
                }

                if (choices.size() > 2) {
                    AbstractCard card3 = choices.get(2);
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(card3));
                }
            }
            this.cardAddedToDrawPile = true;
            this.cardAddedToHand = true;
            this.cardAddedToDiscardPile = true;

            tickDuration();
            return;
        }

        if (this.cardAddedToDrawPile && this.cardAddedToHand && this.cardAddedToDiscardPile) {
            this.isDone = true;
        }

        tickDuration();
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> allStatusCards = new ArrayList<>();
        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (card.type == AbstractCard.CardType.STATUS && !card.cardID.startsWith("llz")) {
                allStatusCards.add(card);
            }
        }

        ArrayList<AbstractCard> choices = new ArrayList<>();
        while (choices.size() < 3 && !allStatusCards.isEmpty()) {
            int index = AbstractDungeon.cardRandomRng.random(allStatusCards.size() - 1);
            AbstractCard card = allStatusCards.remove(index).makeCopy();
            choices.add(card);
        }

        return choices;
    }
}
