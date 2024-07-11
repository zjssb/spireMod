package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import liuLZmod.powers.llz_quans;

/**
 * 甲壳类化石
 */
public class llz_jiaklhs extends CustomRelic {
    public static final String ID = "llz_jiaklhs";
    private static final String IMG_PATH = "ModliuLZ/img/relics/jiaklhs.png";
    private static final RelicTier RELIC_TIER = RelicTier.SHOP;
    private static final LandingSound LANDING_SOUND = LandingSound.SOLID;

    public llz_jiaklhs() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void atBattleStart() {
        flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new llz_quans(AbstractDungeon.player, 20), 20));
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }


    // 获取遗物描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_jiaklhs();
    }
}
