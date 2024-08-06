package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import liuLZmod.action.abstracts.beiyljAction;

/**
 * 备用零件
 */
public class llz_beiylj extends CustomRelic {
    // 遗物ID
    public static final String ID = "llz_beiylj";
    // 图片路径
    private static final String IMG_PATH = "ModliuLZ/img/relics/beiylj.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.HEAVY;

    public llz_beiylj() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void atTurnStart() {
        addToBot(new beiyljAction());
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        addToBot(new beiyljAction());
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_beiylj();
    }
}
