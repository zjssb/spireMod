package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import liuLZmod.powers.llz_jih;

/**
 * 原子钟
 */
public class llz_yuanzz extends CustomRelic {
    public static final String ID = "llz_yuanzz";
    private static final String IMG_PATH = "ModliuLZ/img/relics/yuanzz.png";
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    private static final LandingSound LANDING_SOUND = LandingSound.SOLID;

    public llz_yuanzz() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }
    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        this.counter++;
        AbstractPlayer p = AbstractDungeon.player;
        if (this.counter == 1) {
            flash();
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        }
        if (this.counter == 2) {
            flash();
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
        }
        if (this.counter == 3) {
            flash();
            addToBot(new ApplyPowerAction(p, p, new llz_jih(p, 1), 1));
            this.grayscale = true;
        }
    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }


    // 获取遗物描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_yuanzz();
    }

}
