package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;

/**
 * 光能转化器
 */
public class llz_guanglzhq extends CustomRelic {
    public static final String ID = "llz_guanglzhq";
    private static final String IMG_PATH = "ModliuLZ/img/relics/guanglzhq.png";
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public llz_guanglzhq() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }
    public void atTurnStart() {
        AbstractPlayer player = AbstractDungeon.player;
        flash();
        abstract_llz_jiXie.addEnergy(player.energy.energyMaster);
    }

    // 获取遗物描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_guanglzhq();
    }

}
