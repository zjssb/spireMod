/*    */ package liuLZmod.vfx;
/*    */
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
/*    */ import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
/*    */

/**
 * 改造特效
 */
/*    */ public class FineTuningEffect
        /*    */   extends AbstractGameEffect {
    /*    */   private boolean clang1 = false;
    /*    */
    /*    */   public FineTuningEffect(AbstractCard c) {
        /* 20 */     this.duration = 0.8F;
        /* 21 */     this.owningCard = c;
        /*    */   }
    /*    */   private boolean clang2 = false; private AbstractCard owningCard;
    /*    */   public void update() {
        /* 25 */     if (this.duration < 0.6F && !this.clang1) {
            /* 26 */       CardCrawlGame.sound.playA("MONSTER_BYRD_ATTACK_3", 1.0F);
            /* 27 */       this.clang1 = true;
            /* 28 */       clank(this.owningCard.current_x - 80.0F * this.owningCard.targetDrawScale * Settings.scale, this.owningCard.target_y + 0.0F * this.owningCard.targetDrawScale * Settings.scale);
            /*    */     }
        /*    */
        /*    */
        /* 32 */     if (this.duration < 0.2F && !this.clang2) {
            /* 33 */       this.clang2 = true;
            /* 34 */       clank(this.owningCard.current_x + 90.0F * this.owningCard.targetDrawScale * Settings.scale, this.owningCard.target_y - 110.0F * this.owningCard.targetDrawScale * Settings.scale);
            /*    */     }
        /*    */
        /*    */
        /* 38 */     this.duration -= Gdx.graphics.getDeltaTime();
        /* 39 */     if (this.duration < 0.0F) {
            /* 40 */       clank(this.owningCard.current_x + 30.0F * this.owningCard.targetDrawScale * Settings.scale, this.owningCard.target_y + 120.0F * this.owningCard.targetDrawScale * Settings.scale);
            /* 41 */       this.isDone = true;
            /*    */     }
        /*    */   }
    /*    */
    /*    */
    /*    */   private void clank(float x, float y) {
        /* 47 */     AbstractDungeon.topLevelEffectsQueue.add(new ScaledHammerImprintEffect(x, y, this.owningCard.targetDrawScale));
        /* 48 */     //AbstractDungeon.topLevelEffectsQueue.add(new TextAboveCreatureEffect(x, y - 200.0F * this.owningCard.targetDrawScale, "+1", Color.LIME));
        /* 49 */     if (Settings.DISABLE_EFFECTS) {
            /*    */       return;
            /*    */     }
        /*    */
        /* 53 */     for (int i = 0; i < 10; i++)
            /* 54 */       AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineParticleEffect(x +
                    /*    */
                    /* 56 */             MathUtils.random(-10.0F, 10.0F) * Settings.scale * this.scale, y +
                    /* 57 */             MathUtils.random(-10.0F, 10.0F) * Settings.scale * this.scale));
        /*    */   }
    /*    */
    /*    */   public void render(SpriteBatch sb) {}
    /*    */
    /*    */   public void dispose() {}
    /*    */ }
