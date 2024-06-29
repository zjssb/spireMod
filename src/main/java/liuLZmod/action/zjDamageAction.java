package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * 自伤
 */
public class zjDamageAction extends AbstractGameAction {
    private int damageAmount;

    public zjDamageAction(int damageAmount) {
        this.damageAmount = damageAmount;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasPower("llz_wanq")) {
            damageAmount = 0;
        }
        DamageInfo info = new DamageInfo(AbstractDungeon.player, damageAmount, DamageInfo.DamageType.THORNS);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        this.isDone = true;
    }
}
