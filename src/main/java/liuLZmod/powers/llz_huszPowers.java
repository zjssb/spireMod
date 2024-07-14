package liuLZmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * 护身咒
 */
public class llz_huszPowers extends AbstractPower {
    public static final String POWER_ID = "llz_husz";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public llz_huszPowers(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;

        String path128 = "ModliuLZ/img/powers/husz_p.png";
        String path48 = "ModliuLZ/img/powers/husz.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 128, 128);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 48, 48);

        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        int statusCardCount = 0;

        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.type == AbstractCard.CardType.STATUS) {
                statusCardCount++;
            }
        }

        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card.type == AbstractCard.CardType.STATUS) {
                statusCardCount++;
            }
        }

        for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (card.type == AbstractCard.CardType.STATUS) {
                statusCardCount++;
            }
        }

        if (statusCardCount > 0) {
            this.flash();
            addToBot(new DrawCardAction(AbstractDungeon.player, statusCardCount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
