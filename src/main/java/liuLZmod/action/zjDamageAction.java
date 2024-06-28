package liuLZmod.action;

/*    */
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */

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
            DamageInfo info = new DamageInfo(AbstractDungeon.player, damageAmount, DamageInfo.DamageType.THORNS);
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        this.isDone = true;
    }
}
