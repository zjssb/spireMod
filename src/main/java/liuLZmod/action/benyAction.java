 package liuLZmod.action;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

 public class benyAction extends AbstractGameAction {
     private AbstractPlayer p;
     public benyAction() {
         this.p = AbstractDungeon.player;
     }
     public void update() {
         if (true) {
             upgradeAllCardsInGroup(AbstractDungeon.player.hand, "hand");
             upgradeAllCardsInGroup(AbstractDungeon.player.drawPile, "drawPile");
             //upgradeAllCardsInGroup(AbstractDungeon.player.discardPile, "discardPile");
             //upgradeAllCardsInGroup(AbstractDungeon.player.exhaustPile, "exhaustPile");
             this.isDone = true;
         }
     }

     private void upgradeAllCardsInGroup(CardGroup cardGroup, String groupType){
             for (AbstractCard c : cardGroup.group) {
                   if (c.baseDamage> 0 ||c.baseBlock >0 ||c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.ATTACK) {
                       if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                           c.superFlash();
                       }else addToBot(new gaizAction(p,c,groupType,1));
                       c.applyPowers();
                   }
             }
     }
 }
