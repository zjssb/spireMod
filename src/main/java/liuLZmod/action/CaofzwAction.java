package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class CaofzwAction extends AbstractGameAction {
    private final boolean update;
    private boolean freeToPlayOnce = false;
    private int energyOnUse = -1;

    private AbstractPlayer p;

    public CaofzwAction(AbstractPlayer p,boolean upgrade, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.update = upgrade;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if(update)effect++;

        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                addToBot(new AllGaizAction(1));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
