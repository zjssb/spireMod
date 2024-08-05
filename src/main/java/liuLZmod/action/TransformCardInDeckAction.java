package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.core.Settings;

/**
 * 选择变化一张牌
 */
public class TransformCardInDeckAction extends AbstractGameAction {
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Transmorgrifier");
    private boolean cardSelected = false;

    public TransformCardInDeckAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        if (!cardSelected) {
            CardGroup deck = AbstractDungeon.player.masterDeck;
            if (!deck.isEmpty()) {
                // 获取可以移除的卡牌，并排除瓶装卡牌
                CardGroup transformableCards = CardGroup.getGroupWithoutBottledCards(deck.getPurgeableCards());

                if (!transformableCards.isEmpty()) {
                    AbstractDungeon.gridSelectScreen.open(transformableCards, 1, eventStrings.OPTIONS[2], false, false, false, false);
                    cardSelected = true;
                    return;
                }
            }
            this.isDone = true;
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0) {
            AbstractCard cardToTransform = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.player.masterDeck.removeCard(cardToTransform);
            AbstractDungeon.transformCard(cardToTransform, false, AbstractDungeon.miscRng);
            AbstractCard transformedCard = AbstractDungeon.getTransformedCard();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(transformedCard, Settings.WIDTH / 1.5F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        this.isDone = true;
    }
}
