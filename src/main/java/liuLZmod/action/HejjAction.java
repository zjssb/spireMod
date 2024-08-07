package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 合击技
 */
public class HejjAction extends AbstractGameAction {

    public HejjAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.source = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }

            for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                if (card.type == AbstractCard.CardType.ATTACK && card.cost == 0) {
                    AbstractDungeon.player.drawPile.group.remove(card);
                    AbstractDungeon.getCurrRoom().souls.remove(card);
                    AbstractDungeon.player.limbo.group.add(card);

                    card.current_y = -200.0F * Settings.scale;
                    card.target_x = Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                    card.target_y = Settings.HEIGHT / 2.0F;
                    card.targetAngle = 0.0F;
                    card.lighten(false);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;

                    card.applyPowers();

                    AbstractMonster targetMonster = (card.target == CardTarget.SELF) ? null : AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                    addToTop(new NewQueueCardAction(card, targetMonster, false, true));
                    addToTop(new UnlimboAction(card));

                    if (!Settings.FAST_MODE) {
                        addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                    } else {
                        addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                    }
                    break; // 打出第一张费用为0的攻击牌后停止遍历
                }
            }

            this.isDone = true;
        }
    }
}
