package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.cards.*;

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
            }
            if(card.baseBlock >0){
                card.baseBlock += 1;
            }
            if(card.type == AbstractCard.CardType.STATUS){
                int index = AbstractDungeon.player.hand.group.indexOf(card);
                if(Objects.equals(card.cardID, "Slimed")){
                    if (index != -1) {
                        AbstractCard newCard = new llz_lenqj();
                        AbstractDungeon.player.hand.group.set(index, newCard);}}
                else
                if(Objects.equals(card.cardID, "Wound")){
                    if (index != -1) {
                        AbstractCard newCard = new llz_hej();
                        AbstractDungeon.player.hand.group.set(index, newCard);}}
                else
                if(Objects.equals(card.cardID, "Dazed")){
                    if (index != -1) {
                        AbstractCard newCard = new llz_jiangz();
                        AbstractDungeon.player.hand.group.set(index, newCard);}}
                else
                if(Objects.equals(card.cardID, "Burn")){
                    if (index != -1) {
                        AbstractCard newCard = new llz_leis();
                        AbstractDungeon.player.hand.group.set(index, newCard);}}
                else
                if(Objects.equals(card.cardID, "Void")){
                    if (index != -1) {
                        AbstractCard newCard = new llz_anwz();
                        AbstractDungeon.player.hand.group.set(index, newCard);}}
                else
                    card.baseMagicNumber +=1;
            }
            card.applyPowers();
        }
        isDone = true;
    }

}
