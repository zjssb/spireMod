package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import liuLZmod.monster.llz_ZZWZ;

public class ZZWZAnimationAction extends AbstractGameAction {
    public ZZWZAnimationAction(){}
    @Override
    public void update() {
        llz_ZZWZ.enterSecondPhase();
        this.isDone =true;
    }
}
