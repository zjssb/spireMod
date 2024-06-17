package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.Objects;

/**
 * 秒速射击，初始化魔法数字
 * 摆锤增伤
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
        if (Objects.equals(card.cardID, "llz_hundbc")){
            card.damage += a;
            card.baseDamage += a;
        }
        card.magicNumber = a;
        card.baseMagicNumber = a;
        isDone = true;
    }
}
