/*    */ package liuLZmod.vfx;
/*    */
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */

/**
 * 末日预演特效
 */
/*    */ public class moryyEffect extends AbstractGameEffect {
    /* 11 */   private float timer = 0.1F;
    /*    */
    /*    */   public moryyEffect() {
        /* 14 */     this.duration = 2.0F;
        /*    */   }
    /*    */
    /*    */   public void update() {
        /* 18 */     if (this.duration == 2.0F) {
            /* 19 */       AbstractDungeon.effectsQueue.add(new guangEffect());
            AbstractDungeon.effectsQueue.add(new guangzEffect());
            /*    */     }
        /*    */
        /* 22 */     this.duration -= Gdx.graphics.getDeltaTime();
        /* 23 */     this.timer -= Gdx.graphics.getDeltaTime();
        /*    */
        /* 25 */     if (this.timer < 0.0F) {
            /* 26 */       this.timer += 0.1F;
            /* 27 */       AbstractDungeon.effectsQueue.add(new piaolwEffect());
            /* 28 */       AbstractDungeon.effectsQueue.add(new piaolwEffect());
            /*    */     }
        /*    */
        /* 31 */     if (this.duration < 0.0F)
            /* 32 */       this.isDone = true;
        /*    */   }
    /*    */
    /*    */   public void render(SpriteBatch sb) {}
    /*    */
    /*    */   public void dispose() {}
    /*    */ }
