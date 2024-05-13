package liuLZmod.util;

/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.utils.GdxRuntimeException;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class TextureLoader
        /*    */ {
    /* 20 */   private static HashMap<String, Texture> textures = new HashMap<>();
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */   public static Texture getTexture(String textureString) {
        /* 29 */     if (textures.get(textureString) == null) {
            /*    */       try {
                /* 31 */         loadTexture(textureString);
                /* 32 */       } catch (GdxRuntimeException e) {
                /* 33 */         System.out.println("Could not find texture: " + textureString);
                /* 34 */         return getTexture("ModliuLZ/img/UI/missing_texture.png");
                /*    */       }
            /*    */     }
        /* 37 */     return textures.get(textureString);
        /*    */   }
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */   private static void loadTexture(String textureString) throws GdxRuntimeException {
        /* 48 */     System.out.println("HermitMod | Loading Texture: " + textureString);
        /* 49 */     Texture texture = new Texture(textureString);
        /* 50 */     texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        /* 51 */     textures.put(textureString, texture);
        /*    */   }
    /*    */
    /*    */   @SpirePatch(clz = Texture.class, method = "dispose")
    /*    */   public static class DisposeListener
            /*    */   {
        /*    */     @SpirePrefixPatch
        /*    */     public static void DisposeListenerPatch(Texture __instance) {
            /* 59 */       TextureLoader.textures.entrySet().removeIf(entry -> {
                /*    */             if (((Texture)entry.getValue()).equals(__instance))
                    /*    */               System.out.println("TextureLoader | Removing Texture: " + (String)entry.getKey());
                /*    */             return ((Texture)entry.getValue()).equals(__instance);
                /*    */           });
            /*    */     }
        /*    */   }
    /*    */ }

