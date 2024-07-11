package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class llz_caodhx extends CustomRelic {
    public static final String ID = "llz_caodhx";
    private static final String IMG_PATH = "ModliuLZ/img/relics/caodhx.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.SOLID;

    public llz_caodhx() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = true;
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (this.grayscale) {
            if(card.cost == 0){
                this.grayscale = false;
                flash();
            }
        }else if(card.cost == 0){
            this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 3));
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }else this.grayscale = true;
    }

    // 获取遗物描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_caodhx();
    }
}
