package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * 陀螺仪
 */
public class llz_tuoly extends CustomRelic {
    public static final String ID = "llz_tuoly";
    private static final String IMG_PATH = "ModliuLZ/img/relics/tuoly.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.SOLID;
    private static boolean usedThisCombat = false;

    public llz_tuoly() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_tuoly();
    }

    @Override
    public void atPreBattle() {
        usedThisCombat = false;
    }

    // 洗牌时触发效果
    @Override
    public void onShuffle() {
        if (!usedThisCombat) {
            flash();
            addToBot(new DrawCardAction(AbstractDungeon.player, 10));
            usedThisCombat = true;
        }
    }

    @Override
    public void onVictory() {
        usedThisCombat = false;
    }
}
