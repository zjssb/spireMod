package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

/**
 * 蒸汽机行动
 */
public class zengQjAction extends AbstractGameAction {
    private DamageInfo info;

    public zengQjAction() {
    }

    @Override
    public void update() {
        AbstractMonster m;
        m = AbstractDungeon.getRandomMonster();

        addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, 1, false)));
        addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, 1, false)));

        this.isDone = true;
    }
}
