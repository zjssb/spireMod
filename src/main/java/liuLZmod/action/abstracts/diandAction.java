package liuLZmod.action.abstracts;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.monster.llz_dianD;

/**
 * 在伤害结算后计算掉血，电刀用
 */
public class diandAction extends AbstractGameAction {
    private int num;

    public diandAction(int num) {
        this.num = num;
    }


    @Override
    public void update() {
        if(AbstractDungeon.player.currentHealth < num) {
            llz_dianD.DDEnergy();
        }
            this.isDone = true;
    }
}
