package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import java.util.List;
/**
 * 改造
 */
public class gaizAction extends AbstractGameAction {
    private final List<AbstractCard> cards;

    public gaizAction(List<AbstractCard> cards) {
        this.cards = cards;
    }

    @Override
    public void update() {
        for (AbstractCard card : cards) {
            if (card.type == AbstractCard.CardType.ATTACK) {
                    int baseDamage = card.baseDamage;
                    baseDamage += 2;
                    card.baseDamage = baseDamage;
                    card.applyPowers();
            }
        }
        isDone = true;
    }

}
