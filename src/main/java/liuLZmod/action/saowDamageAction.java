package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 哨卫攻击行动
 */
public class saowDamageAction extends AbstractGameAction {
    private DamageInfo info;

    public saowDamageAction(AbstractCreature source, int damage) {
        this.info = new DamageInfo(source, damage);
    }

    @Override
    public void update() {
        AbstractMonster m;
        m = AbstractDungeon.getRandomMonster();

        if (m != null) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(m, this.info, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }

        this.isDone = true;
    }
}
