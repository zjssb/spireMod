package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * 初始遗物
 */
// 继承CustomRelic
public class MyRelic extends CustomRelic {
    // 遗物ID
    public static final String ID = "llz:MyRelic";
    // 图片路径
    private static final String IMG_PATH = "ModliuLZ/img/relics/yiwu.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public MyRelic() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }


    // ...其余省略
    @Override
    public void atBattleStart() {
        super.atBattleStart();
        this.addToBot(new DrawCardAction(1));
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new MyRelic();
    }
}