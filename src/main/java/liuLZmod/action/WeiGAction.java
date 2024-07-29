package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import liuLZmod.monster.llz_shaoW;

public class WeiGAction extends AbstractGameAction {
    public WeiGAction(){}
    @Override
    public void update() {
        llz_shaoW.act();
        llz_shaoW.setEnergy(0);
        isDone =true;
    }
}
