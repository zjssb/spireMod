 package liuLZmod.action;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

 public class benyAction extends AbstractGameAction {
     private AbstractPlayer p;
     public benyAction() {
         this.p = AbstractDungeon.player;
     }
       public void update() {
             if (true) {
                   upgradeAllCardsInGroup(p.hand);
                   upgradeAllCardsInGroup(p.drawPile);
                   upgradeAllCardsInGroup(p.discardPile);
                   upgradeAllCardsInGroup(p.exhaustPile);

                   this.isDone = true;
                 }
           }

       private void upgradeAllCardsInGroup(CardGroup cardGroup) {
             for (AbstractCard c : cardGroup.group) {
                   if (c.baseDamage> 0 ||c.baseBlock >0 ||c.type == AbstractCard.CardType.STATUS) {
                         if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                               c.superFlash();
                             }
                         addToBot(new gaizAction(p,c));
                         c.applyPowers();
                       }
                 }
           }
     }
