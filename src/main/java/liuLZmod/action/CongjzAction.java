package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

/**
 * 冲击钻行动
 */
public class CongjzAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractCard card;

    public CongjzAction(AbstractCreature target, DamageInfo info, AbstractCard card) {
        this.info = info;
        this.card = card;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (shouldCancelAction()) {
            this.isDone = true;
            return;
        }

        tickDuration();

        if (this.isDone) {
            int initialBlock = this.target.currentBlock;

            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));

            this.target.damage(this.info);

            int finalBlock = this.target.currentBlock;

            // 检查伤害值和实际受到的伤害值，并且检查格挡值是否减少
            if (this.info.output > 0 && this.target.lastDamageTaken == 0 && finalBlock < initialBlock) {
                AbstractMonster m = (AbstractMonster) this.target;
                AbstractCard tmp = this.card.makeSameInstanceOf();
                tmp.purgeOnUse = true; // 标记为一次性使用
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = this.card.current_x;
                tmp.current_y = this.card.current_y;
                tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                tmp.target_y = Settings.HEIGHT / 2.0F;

                if (m != null) {
                    tmp.calculateCardDamage(m);
                }

                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, this.card.energyOnUse, true, true), true);
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            } else {
                addToTop(new WaitAction(0.1F));
            }
            addToTop(new AllGaizAction(1));
        }
    }
}
