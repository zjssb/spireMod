package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.Objects;

/**
 * 黑洞行动
 */
public class HeidAction extends AbstractGameAction {
    private AbstractPlayer player;

    public HeidAction(AbstractPlayer player) {
        this.player = player;
    }

    @Override
    public void update() {
        // 遍历抽牌堆
        replaceStatusCardsInGroup(player.drawPile);

        // 遍历弃牌堆
        replaceStatusCardsInGroup(player.discardPile);

        for (AbstractCard card : player.hand.group) {
            if (card.type == AbstractCard.CardType.STATUS && !Objects.equals(card.cardID, "Void")) {
                addToTop(new NewHeidAction(card,player));
            }else card.superFlash();
        }

        isDone = true;
    }

    private void replaceStatusCardsInGroup(CardGroup cardGroup) {
        for (int i = 0; i < cardGroup.size(); i++) {
            AbstractCard card = cardGroup.group.get(i);
            if (card.type == AbstractCard.CardType.STATUS) {
                cardGroup.group.set(i, new VoidCard());
            }
        }
    }

}
