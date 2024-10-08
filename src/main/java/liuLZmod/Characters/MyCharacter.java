package liuLZmod.Characters;// 省略package路径和部分import，复制的时候不要忘记写上自己的package

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Slot;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import liuLZmod.cards.llz_Strike;
import liuLZmod.cards.llz_diedq;
import liuLZmod.modcore.liuLZMod;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;

import java.util.ArrayList;

import static liuLZmod.Characters.MyCharacter.Enums.LIULANGZE_CARD;
import static liuLZmod.Characters.MyCharacter.Enums.LIULZ_CHARACTER;

public class MyCharacter extends CustomPlayer {
    // 火堆的人物立绘（行动前）
    private static final String MY_CHARACTER_SHOULDER_1 = "ModliuLZ/img/char/shoulder11.png";
    // 火堆的人物立绘（行动后）
    private static final String MY_CHARACTER_SHOULDER_2 = "ModliuLZ/img/char/shoulder22.png";
    // 人物死亡图像
    private static final String CORPSE_IMAGE = "ModliuLZ/img/char/corpse_2.png";
    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            "ModliuLZ/img/UI/orb/zg2.png",
            "ModliuLZ/img/UI/orb/zg3.png",
            "ModliuLZ/img/UI/orb/zg5.png",
            "ModliuLZ/img/UI/orb/zg4.png",
            "ModliuLZ/img/UI/orb/zg1.png",
            "ModliuLZ/img/UI/orb/zg6.png",
            "ModliuLZ/img/UI/orb/zg2d.png",
            "ModliuLZ/img/UI/orb/zg3d.png",
            "ModliuLZ/img/UI/orb/zg5d.png",
            "ModliuLZ/img/UI/orb/zg4d.png",
            "ModliuLZ/img/UI/orb/zg1d.png"
    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]{-20.0F, 30.0F, 0.0F, 0.0F, 0.0F, -10.0F, 8.0F, 0.0F, 0.0F, 0.0F};
    // 人物的本地化文本，如卡牌的本地化文本一样，如何书写见下
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("liuLZMod:MyCharacter");

    public MyCharacter(String name) {
        super(name, LIULZ_CHARACTER,ORB_TEXTURES,"ModliuLZ/img/UI/orb/vfx2.png", LAYER_SPEED, null, null);


        // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);


        // 初始化你的人物，如果你的人物只有一张图，那么第一个参数填写你人物图片的路径。
        this.initializeClass(
                "ModliuLZ/img/char/xgllz_3/skeleton.png", // 人物图片
                MY_CHARACTER_SHOULDER_2, MY_CHARACTER_SHOULDER_1,
                CORPSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                160.0F, 180.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(3) // 初始每回合的能量
        );


        // 如果你的人物没有动画，那么这些不需要写
        this.loadAnimation("ModliuLZ/img/char/xgllz_3/skeleton.atlas", "ModliuLZ/img/char/xgllz_3/skeleton37.json", 0.8F);
        /*AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        e.setTimeScale(1.2F);*/
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.eye = this.skeleton.findSlot("eye");

        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;


    }
    public void damage(DamageInfo info) {
             if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
                   AnimationState.TrackEntry e;
                   e = this.state.setAnimation(0, "Hit", false);
                   this.state.addAnimation(0, "Idle", true, 0.0F);
                   e.setTimeScale(2.0F);
                 }

                super.damage(info);
            }

    // 初始卡组的ID，可直接写或引用变量
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for(int x = 0; x<4; x++) {
            retVal.add("llz_Strike");
        }
        for(int x = 0; x<4; x++) {
            retVal.add("llz_Defend");
        }
        retVal.add("llz_xianq");
        retVal.add("llz_diedq");
        return retVal;
    }

    // 初始遗物的ID，可以先写个原版遗物凑数
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("llz_zallc");
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterStrings.NAMES[0], // 人物名字
                characterStrings.TEXT[0], // 人物介绍
                75, // 当前血量
                75, // 最大血量
                0, // 初始充能球栏位
                99, // 初始携带金币
                5, // 每回合抽牌数量
                this, // 别动
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(), // 初始卡组
                false // 别动
        );
    }

    // 人物名字（出现在游戏左上角）
    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    // 你的卡牌颜色（这个枚举在最下方创建）
    @Override
    public AbstractCard.CardColor getCardColor() {
        return LIULANGZE_CARD;
    }

    // 翻牌事件出现的你的职业牌（一般设为打击）
    @Override
    public AbstractCard getStartCardForEvent() {
        return new llz_diedq();
    }

    // 卡牌轨迹颜色
    @Override
    public Color getCardTrailColor() {
        return liuLZMod.MY_COLOR;
    }

    // 高进阶带来的生命值损失
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // 卡牌的能量字体，没必要修改
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", 1.0F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    // 碎心图片
    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("ModliuLZ/img/char/Victory11.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("ModliuLZ/img/char/Victory22.png"));
        panels.add(new CutscenePanel("ModliuLZ/img/char/Victory33.png"));
        return panels;
    }

    // 自定义模式选择你的人物时播放的音效
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    // 游戏中左上角显示在你的名字之后的人物名称
    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    // 创建人物实例，照抄
    @Override
    public AbstractPlayer newInstance() {
        return new MyCharacter(this.name);
    }

    // 第三章面对心脏说的话（例如战士是“你握紧了你的长刀……”之类的）
    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    // 打心脏的颜色，不是很明显
    @Override
    public Color getSlashAttackColor() {
        return liuLZMod.MY_COLOR;
    }

    // 吸血鬼事件文本，主要是他（索引为0）和她（索引为1）的区别（机器人另外）
    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    // 卡牌选择界面选择该牌的颜色
    @Override
    public Color getCardRenderColor() {
        return liuLZMod.MY_COLOR;
    }

    // 第三章面对心脏造成伤害时的特效
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    /**
     * 在胜利后调用
     */
    @Override
    public void onVictory() {
        super.onVictory();
        abstract_llz_jiXie.clearJiXie();
    }
    // 为原版人物枚举、卡牌颜色枚举扩展的枚举，需要写，接下来要用
    // ***填在SpireEnum中的name需要一致***
    public static class Enums {
        @SpireEnum
        public static PlayerClass LIULZ_CHARACTER;

        @SpireEnum(name = "LIULANGZE_GREEN")
        public static AbstractCard.CardColor LIULANGZE_CARD;

        @SpireEnum(name = "LIULANGZE_GREEN")
        public static CardLibrary.LibraryType LIULANGZE_LIBRARY;
    }
    public Slot eye;
}