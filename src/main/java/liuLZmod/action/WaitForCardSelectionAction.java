package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

/**
 * 变化卡牌
 */
public class WaitForCardSelectionAction extends AbstractGameAction {

    public WaitForCardSelectionAction() {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0) {
            AbstractCard cardToTransform = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.player.masterDeck.removeCard(cardToTransform);
            AbstractDungeon.transformCard(cardToTransform, false, AbstractDungeon.miscRng);
            AbstractCard transformedCard = AbstractDungeon.getTransformedCard();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(transformedCard, Settings.WIDTH / 1.5F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        this.isDone = true;
    }
}
