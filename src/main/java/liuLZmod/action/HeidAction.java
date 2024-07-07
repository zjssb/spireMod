package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

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

        // 遍历手牌并让手牌闪烁
        for (AbstractCard card : player.hand.group) {
            card.superFlash();
            if (isStatusCard(card)) {
                AbstractCard voidCard = new VoidCard();
                player.hand.group.set(player.hand.group.indexOf(card), voidCard);
            }
        }

        isDone = true;
    }

    private void replaceStatusCardsInGroup(CardGroup cardGroup) {
        for (int i = 0; i < cardGroup.size(); i++) {
            AbstractCard card = cardGroup.group.get(i);
            if (isStatusCard(card)) {
                cardGroup.group.set(i, new VoidCard());
            }
        }
    }

    private boolean isStatusCard(AbstractCard card) {
        return card.type == AbstractCard.CardType.STATUS;
    }
}
