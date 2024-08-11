package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.powers.llz_cij;

/**
 * 电枪给buff
 */
public class DianCqjAction extends AbstractGameAction {
    private final int a;
    private AbstractPlayer p;
    private AbstractMonster m;

    public DianCqjAction(AbstractPlayer player, AbstractMonster monster, int a) {
        this.a = a;
        this.p = player;
        this.m = monster;
    }

    @Override
    public void update() {
        addToBot(new ApplyPowerAction(m, p, new llz_cij(m, a), a));
        isDone = true;
    }
}
