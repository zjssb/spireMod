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
    private String groupType; // 牌堆类型信息

    public gaizAction(AbstractPlayer player, AbstractCard card, String groupType, int a) {
        this.player = player;
        this.card = card;
        this.groupType = groupType;
        this.a = a;
    }

    @Override
    public void update() {
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
                    replaceCardInGroup(AbstractDungeon.player, groupType);
                }
                card.applyPowers();
            }
        }
        isDone = true;
    }

    private void replaceCardInGroup(AbstractPlayer player, String groupType) {
        // 传递牌堆类型信息查找并替换卡牌
        switch (groupType) {
            case "hand":
                replaceCardHand(player.hand);
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
                AbstractCard newCard = getReplacementCard(card.cardID);
                if (newCard != null) {
                    cardGroup.group.set(cardGroup.group.indexOf(card), newCard);
                    card = newCard;
                } else {
                    card.magicNumber += 1;
                    card.baseMagicNumber += 1;
                }
                break;
            }
        }
    }

    private void replaceCardHand(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c == card) {
                AbstractCard newCard = getReplacementCard(card.cardID);
                if (newCard != null) {
                    float x = c.current_x;
                    float y = c.current_y;
                    this.player.hand.group.remove(c);
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(newCard, x, y));
                    card = newCard;
                } else {
                    card.magicNumber += 1;
                    card.baseMagicNumber += 1;
                }
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
                return null;
        }
    }
}
