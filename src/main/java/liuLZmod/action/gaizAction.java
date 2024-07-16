package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import liuLZmod.cards.*;

import java.util.Objects;

/**
 * 改造
 */
public class gaizAction extends AbstractGameAction {
    private final int a; // 改造次数
    private AbstractPlayer player;
    private AbstractCard card;

    public gaizAction(AbstractPlayer player, AbstractCard card, String groupType, int a) {
        this.player = player;
        this.card = card;
        this.a = a;
    }

    @Override
    public void update() {
        if (this.player.hasRelic("llz_jiusj")) {
            card.upgrade();
        }
        for (int i = 0; i < a; i++) {
            if (card != null) {
                if (card.baseDamage > 0) {
                    card.baseDamage += 1;
                }
                if (card.baseBlock > 0) {
                    card.baseBlock += 1;
                    if (Objects.equals(card.cardID, "llz_yinqhm") && card.magicNumber < 99) {
                        card.magicNumber += 2;
                        card.baseMagicNumber += 2;
                    } else if (Objects.equals(card.cardID, "llz_feixv")) {
                        addToTop(new MakeTempCardInDrawPileAction(new llz_leis(), 1, true, true));
                    }
                }
                if (card.type == AbstractCard.CardType.STATUS) {
                    if(card.cardID.startsWith("llz")){
                        card.magicNumber += 1;
                        card.baseMagicNumber += 1;
                    }else replaceCardInGroup(player);
                }
                card.applyPowers();
            }
        }
        isDone = true;
    }

    private void replaceCardInGroup(AbstractPlayer player) {
        // 查找并替换卡牌
        if (player.hand.contains(card)) {
            replaceCardHand(player.hand);
        } else if (player.drawPile.contains(card)) {
            replaceCardInGroup(player.drawPile);
        } else if (player.discardPile.contains(card)) {
            replaceCardInGroup(player.discardPile);
        } else if (player.exhaustPile.contains(card)) {
            replaceCardInGroup(player.exhaustPile);
        }
    }

    private void replaceCardInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c == card) {
                AbstractCard newCard = getReplacementCard(card.cardID);
                cardGroup.group.set(cardGroup.group.indexOf(card), newCard);
                card = newCard;
                break;
            }
        }
    }

    private void replaceCardHand(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c == card) {
                AbstractCard newCard = getReplacementCard(card.cardID);
                float x = c.current_x;
                float y = c.current_y;
                this.player.hand.group.remove(c);
                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(newCard, x, y));
                card = newCard;
                break;
            }
        }
    }

    private AbstractCard getReplacementCard(String cardID) {
        switch (cardID) {
            case "Slimed":
                return new llz_lenqj();
            case "Wound":
                return new llz_hej();
            case "Dazed":
                return new llz_jiangz();
            case "Burn":
                return new llz_leis();
            case "Void":
                return new llz_anwz();
            default:
                return new llz_jinj();
        }
    }
}
