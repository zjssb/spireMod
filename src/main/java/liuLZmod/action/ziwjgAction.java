package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

/**
 * 自我解构行动
 */
public class ziwjgAction extends AbstractGameAction {
    private final int a;

    public ziwjgAction(int a) {
        this.a = a;
    }

    @Override
    public void update() {
        for (int i =0;i <a;i++) {
            addToBot(new AllGaizAction(1));
        }
        isDone = true;
    }
}
