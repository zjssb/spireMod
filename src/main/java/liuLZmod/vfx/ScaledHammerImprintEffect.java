/*    */ package liuLZmod.vfx;
/*    */
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */
/*    */
/*    */

/**
 * 齿轮特效
 */
/*    */ public class ScaledHammerImprintEffect
        /*    */   extends AbstractGameEffect
        /*    */ {
    /*    */   private TextureAtlas.AtlasRegion img;
    /*    */   private static final float DUR = 0.7F;
    /*    */   private float x;
    /*    */   private float y;
    /*    */   private float hammerGlowScale;
    /*    */   private Color shineColor;
    /*    */
    /*    */   public ScaledHammerImprintEffect(float x, float y, float scale) {
        /* 26 */     String imagePath = liuLZmod.modcore.liuLZMod.a_image;
        Texture texture = ImageMaster.loadImage(imagePath); // 加载图片
        this.img = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());

        /* 27 */     this.shineColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
        /* 28 */     this.x = x - (this.img.packedWidth / 2);
        /* 29 */     this.y = y - (this.img.packedHeight / 2);
        /* 30 */     this.color = Color.WHITE.cpy();
        /* 31 */     this.color.a = 0.7F;
        /* 32 */     this.duration = 0.7F;
        /* 33 */     this.scale = scale * Settings.scale / MathUtils.random(1.0F, 1.5F);
        /* 34 */     this.rotation = MathUtils.random(0.0F, 360.0F);
        /* 35 */     this.hammerGlowScale = 1.0F - this.duration;
        /* 36 */     this.hammerGlowScale *= this.hammerGlowScale;
        /*    */   }
    /*    */
    /*    */   public void update() {
        /* 40 */     this.duration -= Gdx.graphics.getDeltaTime();
        /* 41 */     if (this.duration < 0.0F) {
            /* 42 */       this.isDone = true;
            /*    */     }
        /*    */
        /* 45 */     this.color.a = this.duration;
        /* 46 */     this.hammerGlowScale = 1.7F - this.duration;
        /* 47 */     this.hammerGlowScale *= this.hammerGlowScale * this.hammerGlowScale;
        /* 48 */     this.scale += Gdx.graphics.getDeltaTime() / 20.0F * this.scale;
        // 旋转动画
        this.rotation += 720.0f * Gdx.graphics.getDeltaTime();
        if (this.rotation >= 360.0f * 2) {
            this.rotation = 0.0f;
        }
        /*    */   }
    /*    */
    /*    */   public void render(SpriteBatch sb) {
        /* 52 */     sb.setBlendFunction(770, 1);
        /* 53 */     sb.setColor(this.color);
        /* 54 */     sb.draw((TextureRegion)this.img, this.x + MathUtils.random(-2.0F, 2.0F) * Settings.scale, this.y + MathUtils.random(-2.0F, 2.0F) * Settings.scale, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
        /* 55 */     this.color.a /= 10.0F;
        /* 56 */     sb.setColor(this.shineColor);
        /* 57 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.hammerGlowScale, this.hammerGlowScale, this.rotation);
        /* 58 */     sb.setBlendFunction(770, 771);
        /*    */   }
    /*    */
    /*    */   public void dispose() {}
    /*    */ }
