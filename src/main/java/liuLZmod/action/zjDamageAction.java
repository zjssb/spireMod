package liuLZmod.action;
/*    */
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */

/**
 * 自伤
 */
public class zjDamageAction extends DamageAction {
        public zjDamageAction(DamageInfo info, AbstractGameAction.AttackEffect effect) {
            super(AbstractDungeon.player, info, effect);
            }
     }