package liuLZmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 戒备
 */
public class llz_jiebPowers extends AbstractPower {
    public static final String POWER_ID = "llz_jieb";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public llz_jiebPowers(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.amount = -1;

        String path128 = "ModliuLZ/img/powers/jieb_P.png";
        String path48 = "ModliuLZ/img/powers/jieb.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 128, 128);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 48, 48);

        this.updateDescription();
    }

    public void onAfterCardPlayed(final AbstractCard usedCard) {
        if (usedCard.type == AbstractCard.CardType.STATUS) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "llz_jieb"));
        }
    }

    public void atEndOfTurn(final boolean isPlayer) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "llz_jieb"));
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}