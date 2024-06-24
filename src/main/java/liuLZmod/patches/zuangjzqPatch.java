package liuLZmod.patches;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import liuLZmod.modcore.liuLZMod;
import liuLZmod.util.TextureLoader;

public class zuangjzqPatch {
    private static Texture HermitGggTexture = TextureLoader.getTexture("ModliuLZ/img/vfx/zuangjzq.png");

    @SpirePatch(clz = FlashAtkImgEffect.class, method = "loadImage")
    public static class vfx {
        @SpirePrefixPatch
        public static SpireReturn Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect ___effect) {
            if (___effect == EnumPatch.ZUANGJZQ_GJ) {
                return SpireReturn.Return(new TextureAtlas.AtlasRegion(HermitGggTexture, 0, 0, HermitGggTexture.getWidth(), HermitGggTexture.getHeight()));
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = FlashAtkImgEffect.class, method = "playSound")
    public static class sfx {
        @SpirePrefixPatch
        public static SpireReturn Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect effect) {
            if (effect == EnumPatch.ZUANGJZQ_GJ) {
                CardCrawlGame.sound.playV("BLUNT_HEAVY", 1.25F);
            } else {
                return SpireReturn.Continue();
            }
            return SpireReturn.Return(null);
        }
    }
}
