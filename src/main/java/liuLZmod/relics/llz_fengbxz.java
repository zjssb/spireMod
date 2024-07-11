package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import liuLZmod.action.FengbxzAction;

public class llz_fengbxz extends CustomRelic {
    public static final String ID = "llz_fengbxz";
    private static final String IMG_PATH = "ModliuLZ/img/relics/fengbxz.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.SOLID;

    public llz_fengbxz() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void atTurnStart() {
        this.grayscale = false;
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (!this.grayscale) {
            addToBot(new FengbxzAction());
        }
    }

    @Override
    public void onCardDraw(final AbstractCard card) {
        if (!this.grayscale) {
            addToBot(new FengbxzAction());
        }
    }

    // 获取遗物描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_fengbxz();
    }
}
