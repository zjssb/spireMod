package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import java.util.UUID;

public class CuanQDJAction extends AbstractGameAction {
    private int miscIncrease;
    private UUID uuid;
    private boolean increaseBaseDamage;
    private boolean increaseMagicNumber;

    public CuanQDJAction(UUID targetUUID, int miscIncrease, boolean increaseBaseDamage, boolean increaseMagicNumber) {
        this.miscIncrease = miscIncrease;
        this.uuid = targetUUID;
        this.increaseBaseDamage = increaseBaseDamage;
        this.increaseMagicNumber = increaseMagicNumber;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (!c.uuid.equals(this.uuid)) continue;
            huansuan(c);
            c.isBlockModified = false;
        }
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            huansuan(c);
        }
        this.isDone = true;
    }

    private void huansuan(AbstractCard c) {
        if (increaseBaseDamage && c.misc < 1000000) c.misc +=1000;
        if (increaseMagicNumber) c.misc++;
        c.applyPowers();
        int su = (int) (c.misc * 0.001);
        if (!c.upgraded){
            c.baseDamage = su;
        }else c.baseDamage = (su+3);
        c.magicNumber = c.baseMagicNumber = (c.misc - su*1000);
    }
}
