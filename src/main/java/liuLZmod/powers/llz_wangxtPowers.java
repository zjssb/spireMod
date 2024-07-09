package liuLZmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 万古形态
 */
public class llz_wangxtPowers extends AbstractPower {
    public static final String POWER_ID = "llz_wangxt";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public llz_wangxtPowers(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;

        String path128 = "ModliuLZ/img/powers/wangxt_p.png";
        String path48 = "ModliuLZ/img/powers/wangxt.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 128, 128);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 48, 48);

        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        int statusCardCount = 0;

        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.cost > 0) {
                card.freeToPlayOnce = true;
                statusCardCount++;
            }
        }

        if(statusCardCount >0)this.flash();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
