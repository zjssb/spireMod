package liuLZmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;

public class FineTuningEffect extends AbstractGameEffect {
    private static final float SOUND_COOLDOWN = 0.1f; // 音效冷却时间
    private static long lastPlayTime = -1; // 上次播放音效的时间

    private boolean clang1 = false;
    private boolean clang2 = false;
    private AbstractCard owningCard;

    public FineTuningEffect(AbstractCard c) {
        this.duration = 0.8F;
        this.owningCard = c;
    }

    public void update() {
        if (this.duration < 0.6F && !this.clang1) {
            if (canPlaySound()) {
                CardCrawlGame.sound.playA("MONSTER_BYRD_ATTACK_3", 1.0F);
                lastPlayTime = System.currentTimeMillis(); // 更新播放时间
            }
            this.clang1 = true;
            clank(this.owningCard.current_x - 80.0F * this.owningCard.targetDrawScale * Settings.scale, this.owningCard.target_y);
        }

        if (this.duration < 0.2F && !this.clang2) {
            this.clang2 = true;
            clank(this.owningCard.current_x + 90.0F * this.owningCard.targetDrawScale * Settings.scale, this.owningCard.target_y - 110.0F * this.owningCard.targetDrawScale * Settings.scale);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            clank(this.owningCard.current_x + 30.0F * this.owningCard.targetDrawScale * Settings.scale, this.owningCard.target_y + 120.0F * this.owningCard.targetDrawScale * Settings.scale);
            this.isDone = true;
        }
    }

    private void clank(float x, float y) {
        AbstractDungeon.topLevelEffectsQueue.add(new ScaledHammerImprintEffect(x, y, this.owningCard.targetDrawScale));
        if (Settings.DISABLE_EFFECTS) {
            return;
        }

        for (int i = 0; i < 10; i++) {
            AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineParticleEffect(x + MathUtils.random(-10.0F, 10.0F) * Settings.scale * this.scale, y + MathUtils.random(-10.0F, 10.0F) * Settings.scale * this.scale));
        }
    }

    private boolean canPlaySound() {
        return System.currentTimeMillis() - lastPlayTime >= SOUND_COOLDOWN * 1000;
    }

    public void render(SpriteBatch sb) {}

    public void dispose() {}
}
