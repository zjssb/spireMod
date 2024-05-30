package liuLZmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import liuLZmod.util.TextureLoader;

/**
 * 光柱特效
 */
public class guangzEffect extends AbstractGameEffect {

    private static TextureRegion GhostFireTexture = new TextureRegion(TextureLoader.getTexture("ModliuLZ/img/vfx/moryy_0.png"));

    private float startX;
    private float endX;
    private float x;
    private float y;
    private float timer;
    private float morTimer = 0.05f;

    public guangzEffect() {
        this.duration = 1.0f;
        this.startX = -GhostFireTexture.getRegionWidth();
        this.endX = Settings.WIDTH;
        this.x = this.startX;
        this.y = Settings.HEIGHT/2.0f;
        this.color = Color.WHITE.cpy();
        this.color.a = 1.0f; // 设置特效透明度为不透明
        this.timer = 0.0f;
    }

    public void update() {
        // 更新特效持续时间
        this.duration -= Gdx.graphics.getDeltaTime();
        this.timer += Gdx.graphics.getDeltaTime();
        if (this.timer >= this.morTimer) {
            this.timer -= this.morTimer;
            AbstractDungeon.effectsQueue.add(new morEffect(this.x, this.y));
        }

        this.x = Interpolation.linear.apply(startX, endX, 1.0f - (this.duration));

        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);

        sb.draw(GhostFireTexture, this.x, 0.0F);

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}
}
