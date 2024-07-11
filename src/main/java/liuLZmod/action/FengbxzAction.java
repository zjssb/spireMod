package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FengbxzAction extends AbstractGameAction {
    private AbstractPlayer player = AbstractDungeon.player;

    public FengbxzAction() {}

    @Override
    public void update() {
        for (AbstractRelic relic : player.relics) {
            if (relic.relicId.equals("llz_fengbxz")) {
                if (player.hand.size() >= 10 && !relic.grayscale) {
                    relic.flash();
                    addToBot(new GainEnergyAction(1));
                    relic.grayscale = true;
                }
                break;
            }
        }
        isDone = true;
    }
}
