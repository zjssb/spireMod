package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

/**
 * 投掷齿轮行动
 */
public class TouzclAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
       public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    public static int numDiscarded;

    public TouzclAction(AbstractCreature target, AbstractCreature source) {
        this.p = (AbstractPlayer) target;
        setValues(target, source);
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() == 1) {
                AbstractCard cardToDiscard = this.p.hand.getTopCard();
                this.p.hand.moveToDiscardPile(cardToDiscard);
                cardToDiscard.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);

                handleDrawPile(cardToDiscard);
            } else {
                //选择一张牌丢弃
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
                tickDuration();
                return;
            }

            AbstractDungeon.player.hand.applyPowers();
            this.isDone = true;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);

                handleDrawPile(c);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }

    private void handleDrawPile(AbstractCard cardToDiscard) {
        boolean found = false;
        for (AbstractCard c : this.p.drawPile.group) {
            if (c.type == cardToDiscard.type) {
                this.p.drawPile.moveToHand(c, this.p.drawPile);
                found = true;
                break;
            }
        }

        if (!found) {
            addToTop(new DrawCardAction(this.p, 1));
        }
    }
}
