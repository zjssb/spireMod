package liuLZmod.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;

public class SuEffect extends AbstractGameEffect {

    private static SuEffect instance;

    private float x;
    private float y;
    private int a;
    private int b;
    private boolean blueText;

    public SuEffect(float x, float y, int a, int b, boolean blueText) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.blueText = blueText;
    }

    public static void play(float x, float y, int a, int b, boolean blueText) {
        instance = new SuEffect(x, y, a, b, blueText);
        AbstractDungeon.topLevelEffectsQueue.add(instance);
    }

    public void update() {
        AbstractDungeon.topLevelEffectsQueue.remove(this); // Remove previous instance, if any
        String text = (b == 1) ? String.valueOf(a) : (a + " × " + b);
        Color textColor = (blueText) ? Color.BLUE : Color.WHITE;
        AbstractDungeon.topLevelEffectsQueue.add(new SimpleTextEffect(x, y, text, textColor));
        isDone = true; // Mark as done after displaying the effect
    }

    public void render(SpriteBatch sb) {}

    public void dispose() {}
}
