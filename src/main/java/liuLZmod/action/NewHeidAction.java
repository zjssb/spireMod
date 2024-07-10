package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import liuLZmod.cards.*;

import java.util.Objects;

/**
 * 替换虚空卡牌
 */
public class NewHeidAction extends AbstractGameAction {
    private AbstractCard card;
    private AbstractPlayer player;

    public NewHeidAction(AbstractCard card,AbstractPlayer player) {
        this.card = card;
        this.player = player;
    }

    @Override
    public void update() {
        for (AbstractCard c : player.hand.group) {
            if (c == card) {
                AbstractCard newCard = new VoidCard();
                    float x = c.current_x;
                    float y = c.current_y;
                    this.player.hand.group.remove(c);
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(newCard, x, y));
                    newCard.superFlash();
                break;
            }
        }
        isDone = true;
    }
}