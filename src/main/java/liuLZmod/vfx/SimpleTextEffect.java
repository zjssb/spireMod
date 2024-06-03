package liuLZmod.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SimpleTextEffect extends AbstractGameEffect {
    private static final float TEXT_DURATION = 0.2f; // 修改显示时间为0.2秒
    private float x;
    private float y;
    private String msg;
    private Color color;

    public SimpleTextEffect(float x, float y, String msg, Color color) {
        this.duration = TEXT_DURATION; // 设置显示时间
        this.startingDuration = TEXT_DURATION;
        this.msg = msg;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void update() {
    }

    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.msg, this.x, this.y, this.color);
        }
    }

    public void dispose() {
    }
}