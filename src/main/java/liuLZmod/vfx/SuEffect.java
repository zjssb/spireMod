package liuLZmod.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;

/**
 * 数字显示
 */
public class SuEffect extends AbstractGameEffect {

    private static SuEffect instance;

    private float x;
    private float y;
    private int a;
    private int b;
    private boolean blueText;

    public SuEffect(float x, float y, int a, int b, boolean blueText) {
        this.x = x;//坐标
        this.y = y;//坐标
        this.a = a;//显示数
        this.b = b;//乘数，为1不显示
        this.blueText = blueText;//是否染色
    }

    public static void play(float x, float y, int a, int b, boolean blueText) {
        instance = new SuEffect(x, y, a, b, blueText);
        AbstractDungeon.topLevelEffectsQueue.add(instance);
    }

    public void update() {
        AbstractDungeon.topLevelEffectsQueue.remove(this);
        isDone = true;
    }

    public void render(SpriteBatch sb) {
        String text = (b == 1) ? String.valueOf(a) : (a + "x" + b);
        Color textColor = (blueText) ? Color.BLUE : Color.WHITE;
        if (!this.isDone) {
            FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, text, x, y, textColor);
        }
    }

    public void dispose() {}
}
