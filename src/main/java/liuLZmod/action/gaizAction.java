package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.cards.*;

import java.util.List;
import java.util.Objects;

/**
 * 改造
 */
public class gaizAction extends AbstractGameAction {
    private final int a;//改造数值
    private AbstractPlayer player;
    private AbstractCard card;
    private String groupType; // 牌堆类型信息


    public gaizAction(AbstractPlayer player, AbstractCard card,String groupType,int a) {
        this.player = player;
        this.card = card;
        this.groupType = groupType;
        this.a =a;
    }

    @Override
    public void update() {
        if (card != null) {
            if(card.baseDamage >0){
                card.baseDamage += a;
            }
            if(card.baseBlock >0){
                card.baseBlock += a;
            }
            if (card.type == AbstractCard.CardType.STATUS) {
                replaceCardInGroup(AbstractDungeon.player, groupType);
            }
            card.applyPowers();
        }
        isDone = true;
    }
    private void replaceCardInGroup(AbstractPlayer player, String groupType) {
        // 传递牌堆类型信息查找并替换卡牌
        switch (groupType) {
            case "hand":
                replaceCardInGroup(player.hand);
                break;
            case "drawPile":
                replaceCardInGroup(player.drawPile);
                break;
            case "discardPile":
                replaceCardInGroup(player.discardPile);
                break;
            case "exhaustPile":
                replaceCardInGroup(player.exhaustPile);
                break;
            default:
                break;
        }
    }

    private void replaceCardInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c == card) {
                AbstractCard newCard = null;
                if (Objects.equals(card.cardID, "Slimed")) {
                    newCard = new llz_lenqj();
                } else if (Objects.equals(card.cardID, "Wound")) {
                    newCard = new llz_hej();
                } else if (Objects.equals(card.cardID, "Dazed")) {
                    newCard = new llz_jiangz();
                } else if (Objects.equals(card.cardID, "Burn")) {
                    newCard = new llz_leis();
                } else if (Objects.equals(card.cardID, "Void")) {
                    newCard = new llz_anwz();
                }else {card.magicNumber +=a;card.baseMagicNumber +=a;}
                if (newCard != null) {
                    cardGroup.group.set(cardGroup.group.indexOf(card), newCard);
                }
                break;
            }
        }
    }

}
