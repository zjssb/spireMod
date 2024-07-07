package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class JinhsfAction extends AbstractGameAction {
    private DamageInfo info;

    public JinhsfAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            this.target.damage(this.info);

            if (((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0 && !this.target.halfDead && !this.target.hasPower("Minion")) {
                AbstractDungeon.actionManager.addToTop(new TransformCardInDeckAction());
            }

            // Delay the combat end check to allow subsequent actions
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_LONG)); // Adjust duration as needed
            }
        }

        tickDuration();

        if (this.isDone) {
            addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        }
    }

}
