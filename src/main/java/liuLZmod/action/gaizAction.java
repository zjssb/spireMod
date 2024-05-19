package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.cards.llz_lenqj;

import java.util.List;
import java.util.Objects;

/**
 * 改造
 */
public class gaizAction extends AbstractGameAction {
    private AbstractPlayer player;
    private AbstractCard card;

    public gaizAction(AbstractPlayer player, AbstractCard card) {
        this.player = player;
        this.card = card;
    }

    @Override
    public void update() {
        if (card != null) {
            if(card.baseDamage >0){
                card.baseDamage += 1;
                card.superFlash();
                card.applyPowers();
            }
            if(card.baseBlock >0){
                card.baseBlock += 1;
                card.superFlash();
                card.applyPowers();
            }
            if(card.type == AbstractCard.CardType.STATUS){
                if(Objects.equals(card.cardID, "Slimed")){
                    AbstractDungeon.player.hand.group.set(0,card = (new llz_lenqj()));}
                else
                if(card.cardID == ""){}
                else
                if(card.cardID == ""){}
                else
                if(card.cardID == ""){}
                else
                if(card.cardID == ""){}
                else
                    card.baseMagicNumber +=1;
                card.superFlash();
                card.applyPowers();
            }
        }
        isDone = true;
    }

}
