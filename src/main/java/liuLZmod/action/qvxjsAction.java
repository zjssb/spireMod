package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.vfx.FineTuningEffect;

public class qvxjsAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final boolean upgraded;
    private int i;

    public qvxjsAction(AbstractPlayer p, boolean upgraded,int i) {
        this.p = p;
        this.upgraded = upgraded;
        this.duration = Settings.ACTION_DUR_FAST;
        this.i = i;
    }

    @Override
    public void update() {
        if ( i < GameActionManager.turn) {
            if (!AbstractDungeon.player.discardPile.isEmpty() && AbstractDungeon.player.drawPile.isEmpty() &&AbstractDungeon.player.hand.size() <= 10){i--;}
            i++;
            addToTop(new qvxjsAction(p, this.upgraded, i));


            if (!AbstractDungeon.player.drawPile.isEmpty()&& AbstractDungeon.player.hand.size() <= 10) {
                AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
                AbstractDungeon.effectList.add(new FineTuningEffect(card));
                addToTop(new DrawCardAction(p, 1));
                addToTop(new gaizAction(p, card, "drawPile", 1));

            } else if (!AbstractDungeon.player.discardPile.isEmpty() && AbstractDungeon.player.drawPile.isEmpty() && AbstractDungeon.player.hand.size() <= 10) {
                addToTop(new ShuffleAction(AbstractDungeon.player.drawPile, false));
                addToTop(new EmptyDeckShuffleAction());
            }
        }
        this.isDone = true;
    }
}
