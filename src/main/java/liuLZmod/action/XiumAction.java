package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class XiumAction extends AbstractGameAction {
    private AbstractPlayer p;

    public XiumAction() {
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.DISCARD;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            // 创建临时列表来存储需要丢弃的牌
            ArrayList<AbstractCard> cardsToDiscard = new ArrayList<>();

            for (AbstractCard card : this.p.hand.group) {
                cardsToDiscard.add(card);
            }

            for (AbstractCard card : this.p.drawPile.group) {
                cardsToDiscard.add(card);
            }

            // 将临时列表中的牌丢弃到弃牌堆
            for (AbstractCard card : cardsToDiscard) {
                if (this.p.hand.contains(card)) {
                    this.p.hand.moveToDiscardPile(card);
                } else if (this.p.drawPile.contains(card)) {
                    this.p.drawPile.moveToDiscardPile(card);
                }
            }

            this.p.hand.refreshHandLayout();
        }

        this.isDone = true;
    }
}
