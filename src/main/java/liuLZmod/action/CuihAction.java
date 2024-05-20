package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;

public class CuihAction extends AbstractGameAction {
    private static final String uiStrings = "xx";
    public static final String[] TEXT = new String[]{""};

    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();


    public CuihAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;

    }

    public void update() {
            /*  30 */     if (this.duration == Settings.ACTION_DUR_FAST) {
                /*     */
                /*     */
                /*  33 */
                /*     */
                /*  46 */       for (AbstractCard c : this.p.hand.group) {
                    /*  47 */         if (!(c.baseDamage> 0 ||c.baseBlock >0 ||c.type == AbstractCard.CardType.STATUS)) {
                        /*  48 */           this.cannotUpgrade.add(c);
                        /*     */         }
                    /*     */       }
                /*     */
                /*     */
                /*  53 */       if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
                    /*  54 */         this.isDone = true;
                    /*     */
                    /*     */         return;
                    /*     */       }
                /*  58 */       if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
                    /*  59 */         for (AbstractCard c : this.p.hand.group) {
                        /*  60 */           if (true) {
                            /*  61 */             //c.baseDamage++;
                            addToBot(new gaizAction(p,c));
                            /*  64 */             this.isDone = true;
                            /*     */
                            /*     */
                            /*     */             return;
                            /*     */           }
                        /*     */         }
                    /*     */       }
                /*     */
                /*  72 */       this.p.hand.group.removeAll(this.cannotUpgrade);
                /*     */
                /*  74 */       if (this.p.hand.group.size() > 1) {
                    /*  75 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                    /*  76 */         tickDuration(); return;
                    /*     */       }
                /*  78 */       if (this.p.hand.group.size() == 1) {
                    /*  79 */         this.p.hand.getTopCard().upgrade();
                    /*  80 */         this.p.hand.getTopCard().superFlash();
                    /*  81 */         returnCards();
                    /*  82 */         this.isDone = true;
                    /*     */       }
                /*     */     }
            /*     */
            /*     */
            /*  87 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                /*  88 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    /*  89 */         addToBot(new gaizAction(p,c));
                    /*  92 */         this.p.hand.addToTop(c);
                    /*     */       }
                /*     */
                /*  95 */       returnCards();
                /*  96 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                /*  97 */       AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
                /*  98 */       this.isDone = true;
                /*     */     }
            /*     */
            /* 101 */     tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : cannotUpgrade) {
            p.hand.addToTop(c);
        }
        p.hand.refreshHandLayout();
    }
}
