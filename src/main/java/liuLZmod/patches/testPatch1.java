
package liuLZmod.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.monsters.abstracrt.abstract_llz_jiXie;

public class testPatch1 {
    public testPatch1() {
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
            method = "preBattlePrep"
    )
    public static class PreBattlePatch {
        public PreBattlePatch() {
        }

        public static void Postfix(AbstractPlayer _instance) {
            abstract_llz_jiXie.clearJiXie();
        }
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
            method = "render"
    )
    public static class RenderPatch {
        public RenderPatch() {
        }

        public static void Prefix(AbstractPlayer _instance, SpriteBatch sb) {
            MonsterGroup minions = (MonsterGroup)testPatch.f_minions.get(AbstractDungeon.player);
            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    if (((MonsterGroup)testPatch.f_minions.get(AbstractDungeon.player)).monsters.size() > 0) {
                        minions.render(sb);
                    }
                default:
            }
        }
    }

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
            method = "update"
    )
    public static class UpdatePatch {
        public UpdatePatch() {
        }

        public static void Postfix(AbstractPlayer player) {
            MonsterGroup minions = (MonsterGroup)testPatch.f_minions.get(AbstractDungeon.player);
            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    if (((MonsterGroup)testPatch.f_minions.get(AbstractDungeon.player)).monsters.size() > 0) {
                        minions.update();
                    }
                default:
            }
        }
    }
}
