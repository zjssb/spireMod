package liuLZmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/**
 * 动画文件播放
 */
public class SpineEffect extends AbstractGameEffect {
    private Skeleton skeleton;
    private AnimationState state;
    private SkeletonRenderer renderer;
    private float x;
    private float y;
    private float speed;

    // Constructor to use coordinates
    public SpineEffect(String atlasPath, String jsonPath, String animationName, float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        initialize(atlasPath, jsonPath, animationName);
    }

    // Constructor to use monster instance
    public SpineEffect(String atlasPath, String jsonPath, String animationName, AbstractCreature monster, float speed) {
        this.x = monster.drawX;
        this.y = monster.drawY + monster.hb.height / 2.0f;
        this.speed = speed;
        initialize(atlasPath, jsonPath, animationName);
    }

    // Initialize common parts
    private void initialize(String atlasPath, String jsonPath, String animationName) {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasPath));
        SkeletonJson json = new SkeletonJson(atlas);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(jsonPath));

        skeleton = new Skeleton(skeletonData);
        AnimationStateData stateData = new AnimationStateData(skeletonData);
        state = new AnimationState(stateData);

        state.setAnimation(0, animationName, false); // Play animation once
        state.getData().setDefaultMix(speed); // Set the animation speed
        renderer = new SkeletonRenderer();
    }

    @Override
    public void update() {
        state.update(Gdx.graphics.getDeltaTime() * speed);
        state.apply(skeleton);
        skeleton.setPosition(x, y);
        skeleton.updateWorldTransform();

        if (state.getCurrent(0).isComplete()) {
            isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(1, 1, 1, 1);
        renderer.draw(sb, skeleton);
    }

    @Override
    public void dispose() {
        // Dispose resources if necessary
    }
}
