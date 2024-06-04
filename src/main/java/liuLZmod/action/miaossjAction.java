package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 秒速射击，初始化魔法数字
 */
public class miaossjAction extends AbstractGameAction {
    private final int a;
    private final AbstractCard card;

    public miaossjAction(int a, AbstractCard card) {
        this.a = a;
        this.card = card;
    }

    @Override
    public void update() {
        card.magicNumber = a;
        card.baseMagicNumber = a;
        isDone = true;
    }
}
