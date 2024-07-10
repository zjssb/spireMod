package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;

import java.util.ArrayList;

/**
 * 化为乌有行动
 */
public class HuawwyAction extends AbstractGameAction {
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Designer");
    private final int a;

    private AbstractPlayer p;
    private ArrayList<AbstractCard> nonAttackCards = new ArrayList<>();

    public HuawwyAction(int a) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.a = a;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : this.p.hand.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    this.nonAttackCards.add(c);
                }
            }

            if (this.nonAttackCards.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.nonAttackCards.size() == 1) {
                for (AbstractCard c : this.p.hand.group) {
                    if (!(c.type == AbstractCard.CardType.ATTACK)) {
                        this.p.hand.group.remove(c);
                        addToTop(new MakeTempCardInHandAction(new Dazed(), 1));
                        this.isDone = true;
                        return;
                    }
                }
            }

            this.p.hand.group.removeAll(this.nonAttackCards);

            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(eventStrings.OPTIONS[11], 1, false, false);
                tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                AbstractCard c = this.p.hand.getTopCard();
                this.p.hand.group.remove(c);
                addToTop(new MakeTempCardInHandAction(new Dazed(), 1));
                returnCards();
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.group.remove(c);
                addToTop(new MakeTempCardInHandAction(new Dazed(), 1));
            }
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.nonAttackCards) {
            p.hand.addToTop(c);
        }
        p.hand.refreshHandLayout();
    }
}
