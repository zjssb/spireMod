package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.vfx.FineTuningEffect;

import java.util.ArrayList;

public class CuihAction extends AbstractGameAction {
    public static final String[] TEXT = new String[]{""};

    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();


    public CuihAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;

    }

    public void update() {
                 if (this.duration == Settings.ACTION_DUR_FAST) {




                       for (AbstractCard c : this.p.hand.group) {
                             if (!(c.baseDamage> 0 ||c.baseBlock >0 ||c.type == AbstractCard.CardType.STATUS)) {
                                   this.cannotUpgrade.add(c);
                                 }
                           }


                       if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
                             this.isDone = true;

                             return;
                           }
                       if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
                             for (AbstractCard c : this.p.hand.group) {
                                   if (true) {
                                       addToBot(new gaizAction(p,c,"hand"));
                                       AbstractDungeon.effectList.add(new FineTuningEffect(c));
                                       this.isDone = true;


                                         return;
                                       }
                                 }
                           }

                       this.p.hand.group.removeAll(this.cannotUpgrade);

                       if (this.p.hand.group.size() > 1) {
                             AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                             tickDuration(); return;
                           }
                       if (this.p.hand.group.size() == 1) {
                             this.p.hand.getTopCard().upgrade();
                             this.p.hand.getTopCard().superFlash();
                             returnCards();
                             this.isDone = true;
                           }
                     }


                 if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                             addToBot(new gaizAction(p,c,"hand"));
                           AbstractDungeon.effectList.add(new FineTuningEffect(c));
                             this.p.hand.addToTop(c);
                           }

                       returnCards();
                       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                       AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
                       this.isDone = true;
                     }

                 tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : cannotUpgrade) {
            p.hand.addToTop(c);
        }
        p.hand.refreshHandLayout();
    }
}
