package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WangsAction extends AbstractGameAction {

    private AbstractPlayer p;

    public WangsAction(AbstractPlayer player) {
        this.p = player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractCard cardToCopy = null;
            for (int i = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1; i >= 0; i--) {
                AbstractCard card = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i);
                if (!card.cardID.equals("llz_wangs")) {
                    cardToCopy = card;
                    break;
                }
            }

            if (cardToCopy != null) {
                AbstractMonster m = null;
                if (AbstractDungeon.getCurrRoom().monsters.monsters.size() > 0) {
                    m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                }

                AbstractCard tmp = cardToCopy.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = cardToCopy.current_x;
                tmp.current_y = cardToCopy.current_y;
                tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                tmp.target_y = Settings.HEIGHT / 2.0F;

                if (m != null) {
                    tmp.calculateCardDamage(m);
                }

                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, cardToCopy.energyOnUse, true, true), true);
            }

            this.isDone = true;
        }

        tickDuration();
    }
}
