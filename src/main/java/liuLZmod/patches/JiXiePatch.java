package liuLZmod.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.monster.llz_dianD;

public class JiXiePatch {
    public JiXiePatch() {
    }
    /**机械的初始化补丁
     * 在回合开始前，清空机械列表*/
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
/**
 * 机械的渲染补丁
 * 执行在player渲染之前*/
    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
            method = "render"
    )
    public static class RenderPatch {
        public RenderPatch() {
        }

        public static void Prefix(AbstractPlayer _instance, SpriteBatch sb) {
            MonsterGroup minions = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    if (JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player).monsters.size() > 0) {
                        minions.render(sb);
                    }
                default:
            }
        }
    }
    /**
     * 机械的update补丁
     * 执行在player之后*/
    @SpirePatch(
            cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
            method = "update"
    )
    public static class UpdatePatch {
        public UpdatePatch() {
        }

        public static void Prefix(AbstractPlayer player) {
            MonsterGroup minions = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            switch (AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    if (JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player).monsters.size() > 0) {
                        minions.update();
                    }
                default:
            }
        }
    }
    /**
     * 电刀的行动*/
    @SpirePatch(
            clz = com.megacrit.cardcrawl.characters.AbstractPlayer.class,
            method = "applyStartOfTurnCards"
    )
    public static class dianDaoActPatch{

        public static void Prefix(AbstractPlayer player)
        {
            if(llz_dianD.DD!=null){
                llz_dianD.act();
            }
        }
    }
    /**电刀的充能损失补丁*/
    @SpirePatch(clz=com.megacrit.cardcrawl.characters.AbstractPlayer.class,
    method = "damage")
    public static class dianDaoLoss{
        public static void Prefix(AbstractPlayer player){
            llz_dianD.lossEnergy();
        }
    }

}
