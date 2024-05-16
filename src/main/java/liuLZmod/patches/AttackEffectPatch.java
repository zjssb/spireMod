package liuLZmod.patches;

/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ import liuLZmod.modcore.liuLZMod;
/*    */ import liuLZmod.util.TextureLoader;
/*    */
/*    */

/**
 * 攻击特效
 */
/*    */ public class AttackEffectPatch
        /*    */ {
    /* 18 */   private static Texture GhostFireTexture = TextureLoader.getTexture("ModliuLZ/img/vfx/HermitGhostFire.png");
    /*    */
    /*    */
    /*    */   @SpirePatch(clz = FlashAtkImgEffect.class, method = "loadImage")
    /*    */   public static class vfx
            /*    */   {
        /*    */     @SpirePrefixPatch
        /*    */     public static SpireReturn Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect ___effect) {
            /* 26 */       if (___effect == EnumPatch.HERMIT_GUN0 || ___effect == EnumPatch.HERMIT_GUN || ___effect == EnumPatch.HERMIT_GUN2 || ___effect == EnumPatch.HERMIT_GUN3) {
                /* 27 */         return SpireReturn.Return(ImageMaster.ATK_BLUNT_LIGHT);
                /*    */       }
            /*    */
            /* 30 */       if (___effect == EnumPatch.HERMIT_GHOSTFIRE)
                /*    */       {
                /* 32 */         return SpireReturn.Return(new TextureAtlas.AtlasRegion(AttackEffectPatch.GhostFireTexture, 0, 0, AttackEffectPatch.GhostFireTexture.getWidth(), AttackEffectPatch.GhostFireTexture.getHeight()));
                /*    */       }
            /*    */
            /* 35 */       return SpireReturn.Continue();
            /*    */     }
        /*    */   }
    /*    */
    /*    */
    /*    */   @SpirePatch(clz = FlashAtkImgEffect.class, method = "playSound")
    /*    */   public static class sfx
            /*    */   {
        /*    */     @SpirePrefixPatch
        /*    */     public static SpireReturn Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect effect) {
            /* 45 */       if (effect == EnumPatch.HERMIT_GUN) {
                /* 46 */         CardCrawlGame.sound.playV(liuLZMod.makeID("GUN1"), 1.25F);
                /*    */       }
            /* 48 */       else if (effect == EnumPatch.HERMIT_GUN2) {
                /* 49 */         CardCrawlGame.sound.playV(liuLZMod.makeID("GUN2"), 1.25F);
                /*    */       }
            /* 51 */       else if (effect == EnumPatch.HERMIT_GUN3) {
                /* 52 */         CardCrawlGame.sound.playV(liuLZMod.makeID("GUN3"), 1.25F);
                /*    */       }
            /* 54 */       else if (effect == EnumPatch.HERMIT_GHOSTFIRE) {
                /* 55 */         CardCrawlGame.sound.playV("ATTACK_FIRE", 1.25F);
                /*    */       } else {
                /*    */
                /* 58 */         return SpireReturn.Continue();
                /*    */       }
            /*    */
            /* 61 */       return SpireReturn.Return(null);
            /*    */     }
        /*    */   }
    /*    */ }

