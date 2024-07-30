package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.List;

public class WangsAction extends AbstractGameAction {

    private AbstractPlayer p;
    private boolean allowChoice;
    private boolean retrieveCard = false;

    public WangsAction(AbstractPlayer player, boolean allowChoice) {
        this.p = player;
        this.allowChoice = allowChoice;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (allowChoice) {
                List<AbstractCard> possibleCards = new ArrayList<>();
                for (int i = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1; i >= 0; i--) {
                    AbstractCard card = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i);
                    if (!card.cardID.equals("llz_wangs")) {
                        possibleCards.add(card.makeStatEquivalentCopy());
                        if (possibleCards.size() == 3) break;
                    }
                }

                if (possibleCards.size() == 0) {
                    this.isDone = true;
                    return;
                }

                if (possibleCards.size() == 1) {
                    playCard(possibleCards.get(0));
                } else {
                    AbstractDungeon.cardRewardScreen.customCombatOpen(new ArrayList<>(possibleCards), CardRewardScreen.TEXT[1], false);
                    tickDuration();
                    return;
                }
            } else {
                AbstractCard cardToCopy = null;
                for (int i = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1; i >= 0; i--) {
                    AbstractCard card = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i);
                    if (!card.cardID.equals("llz_wangs")) {
                        cardToCopy = card.makeStatEquivalentCopy();
                        break;
                    }
                }

                if (cardToCopy != null) {
                    playCard(cardToCopy);
                }
            }

            this.isDone = true;
        }

        if (!this.retrieveCard && AbstractDungeon.cardRewardScreen.discoveryCard != null) {
            AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
            playCard(disCard);
            AbstractDungeon.cardRewardScreen.discoveryCard = null;
            this.retrieveCard = true;
        }

        tickDuration();
    }

    private void playCard(AbstractCard cardToCopy) {
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

        if (AbstractDungeon.player.hand.size() < 10) {
            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(tmp, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        } else {
            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(tmp, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        }
    }
}
