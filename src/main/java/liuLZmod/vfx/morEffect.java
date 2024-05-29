package liuLZmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import liuLZmod.util.TextureLoader;

/**
 * 扬尘特效
 */
public class morEffect extends AbstractGameEffect {

    private static TextureRegion[] MorTextures = {
            new TextureRegion(TextureLoader.getTexture("ModliuLZ/img/vfx/moryy_6.png")),
            new TextureRegion(TextureLoader.getTexture("ModliuLZ/img/vfx/moryy_7.png")),
            new TextureRegion(TextureLoader.getTexture("ModliuLZ/img/vfx/moryy_8.png"))
    };
    private final TextureRegion morTexture;

    private float x;
    private float y;
    private float timer;
    private float alpha;

    public morEffect(float x, float y) {
        this.x = x;
        this.y = y;
        this.timer = 0.0f;
        this.alpha = 1.0f;
        this.duration = 0.3f;
        int randomIndex = MathUtils.random(MorTextures.length - 1);
        this.morTexture = MorTextures[randomIndex];
    }

    public void update() {
        this.timer += Gdx.graphics.getDeltaTime();

        if (this.timer < 0.1f) {
            this.alpha = 1.0f;
        } else {
            this.alpha = Interpolation.fade.apply(1.0f, 0.0f, (this.duration - 0.1f) / 0.2f);
        }

        this.duration -= Gdx.graphics.getDeltaTime();

        if (this.duration < 0.0f) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        Color color = Color.WHITE.cpy();
        color.a = this.alpha;
        sb.setColor(color);

        sb.draw(morTexture, this.x - morTexture.getRegionWidth() / 2, this.y - morTexture.getRegionHeight() / 2);
    }

    public void dispose() {}
}
