package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;

/**
 * 变化一张牌
 */
public class TransformCardInDeckAction extends AbstractGameAction {
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Transmorgrifier");

    public TransformCardInDeckAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        CardGroup deck = AbstractDungeon.player.masterDeck;
        if (!deck.isEmpty()) {
            CardGroup transformableCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard card : deck.group) {
                transformableCards.addToTop(card);
            }

            if (!transformableCards.isEmpty()) {
                AbstractDungeon.gridSelectScreen.open(transformableCards, 1, eventStrings.OPTIONS[2], false, false, false, false);
                AbstractDungeon.actionManager.addToBottom(new WaitForCardSelectionAction());
            }
        }
        this.isDone = true;
    }
}