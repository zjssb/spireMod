package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.cards.llz_cubhl;

import java.util.UUID;

/**
 * 储备火力降级
 */
public class DowngradeCardAction extends AbstractGameAction {
    private AbstractPlayer player;
    private UUID uuid;
    public DowngradeCardAction(UUID targetUUID, AbstractPlayer player) {
        this.uuid = targetUUID;
        this.player = player;
    }

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(this.uuid)) {
                player.masterDeck.group.set(player.masterDeck.group.indexOf(c), new llz_cubhl());
            }
        }
        this.isDone = true;
    }
}
