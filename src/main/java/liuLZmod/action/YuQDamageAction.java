package liuLZmod.action;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import liuLZmod.powers.llz_shengNaPower;

/**
 * 鱼群，给锁定，转火
 */
public class YuQDamageAction extends AbstractGameAction {
    private DamageInfo info;

    public YuQDamageAction(AbstractCreature source, int damage) {
        this.info = new DamageInfo(source, damage);
    }

    @Override
    public void update() {
        AbstractMonster m = null;

        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (monster.hasPower("llz_shengNa") && monster.currentHealth > 0) {
                m = monster;
                break;
            }
        }

        // 如果没有找到拥有 llz_shengNaPower 的怪物，随机选择一个怪物
        if (m == null) {
            m = AbstractDungeon.getRandomMonster();
            if (m != null) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, source, new llz_shengNaPower(m, m)));
            }
        }

        if (m != null) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(m, this.info, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToTop(new VFXAction(new BiteEffect(m.hb.cX +
                    MathUtils.random(-50.0F, 50.0F) * Settings.scale, m.hb.cY + MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.CHARTREUSE.cpy())));
        }

        this.isDone = true;
    }
}
