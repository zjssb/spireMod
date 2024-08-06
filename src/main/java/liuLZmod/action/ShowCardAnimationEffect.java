package liuLZmod.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;

/**
 * 显示变化的卡牌
 */
public class ShowCardAnimationEffect extends AbstractGameEffect {
    private static final float EFFECT_DUR = 2.0F;
    private static final float FAST_DUR = 0.5F;
    private AbstractCard card;
    private static final float PADDING = 30.0F * Settings.scale;
    private boolean converge;

    public ShowCardAnimationEffect(AbstractCard card, float x, float y, boolean convergeCards) {
        // 标记卡牌为已见
        UnlockTracker.markCardAsSeen(card.cardID);

        // 将卡牌添加到卡牌统计中
        CardHelper.obtain(card.cardID, card.rarity, card.color);
        this.card = card;
        this.converge = convergeCards;

        this.duration = Settings.FAST_MODE ? FAST_DUR : EFFECT_DUR;
        identifySpawnLocation(x, y);
        AbstractDungeon.effectsQueue.add(new CardPoofEffect(card.target_x, card.target_y));
        card.drawScale = 0.01F;
        card.targetDrawScale = 1.0F;
    }

    public ShowCardAnimationEffect(AbstractCard card, float x, float y) {
        this(card, x, y, false);
    }

    private void identifySpawnLocation(float x, float y) {
        int effectCount = 0;
        for (AbstractGameEffect e : AbstractDungeon.effectList) {
            if (e instanceof ShowCardAnimationEffect) {
                effectCount++;
            }
        }

        this.card.current_x = x;
        this.card.current_y = y;

        if (this.converge) {
            this.card.target_y = Settings.HEIGHT * 0.5F;
            switch (effectCount) {
                case 0:
                    this.card.target_x = Settings.WIDTH * 0.5F;
                    return;
                case 1:
                    this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
                    return;
                case 2:
                    this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
                    return;
                case 3:
                    this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
                    return;
                case 4:
                    this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
                    return;
            }
            this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
            this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
        } else {
            this.card.target_x = this.card.current_x;
            this.card.target_y = this.card.current_y;
        }
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.card.update();

        if (this.duration < 0.0F) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                r.onObtainCard(this.card);
            }
            this.isDone = true;
            this.card.shrink();
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                r.onMasterDeckChange();
            }
        }
    }

    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            this.card.render(sb);
        }
    }

    public void dispose() {}
}
