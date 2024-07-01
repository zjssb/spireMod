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
 * 合击技行动
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

            for (int i = 0; i < AbstractDungeon.player.drawPile.size(); i++) {
                AbstractCard card = AbstractDungeon.player.drawPile.group.get(i);

                if (card.type == AbstractCard.CardType.ATTACK && card.cost == 0) {
                    AbstractDungeon.player.drawPile.group.remove(card);
                    AbstractDungeon.getCurrRoom().souls.remove(card);
                    AbstractDungeon.player.limbo.group.add(card);

                    card.applyPowers();

                    // 获取一个随机的目标怪物或传入空值
                    AbstractMonster targetMonster = (card.target == CardTarget.SELF) ? null : AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                    addToTop(new NewQueueCardAction(card, targetMonster, false, true));
                    addToTop(new UnlimboAction(card));

                    if (!Settings.FAST_MODE) {
                        addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                    } else {
                        addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                    }
                }
            }

            this.isDone = true;
        }
    }
}
