package liuLZmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Objects;

/**
 * 过热
 */
public class llz_guor extends AbstractPower {
    public static final String POWER_ID = "llz_guor";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public llz_guor(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.amount = Amount;

        String path128 = "ModliuLZ/img/powers/guor_p.png";
        String path48 = "ModliuLZ/img/powers/guor.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 128, 128);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 48, 48);

        this.updateDescription();
    }

    //打出卡牌后相关更新
    public void onAfterCardPlayed(final AbstractCard usedCard) {
        if(usedCard.exhaust){this.amount++;}
        if (Objects.equals(usedCard.cardID, "llz_zuangjzq")){this.amount = 0;}
        updateExistingShivs();
    }
    //层数提升时，更新卡牌
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        updateExistingShivs();
    }

    //更新已有的牌
    private void updateExistingShivs() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (Objects.equals(c.cardID, "llz_zuangjzq")) {
                if (!c.upgraded) {
                    c.baseDamage = 12 + 2*this.amount;
                } else {
                    c.baseDamage = 12 + 4*this.amount;
                }
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (Objects.equals(c.cardID, "llz_zuangjzq")) {
                if (!c.upgraded) {
                    c.baseDamage = 12 + 2*this.amount;
                } else {
                    c.baseDamage = 12 + 4*this.amount;
                }
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (Objects.equals(c.cardID, "llz_zuangjzq")) {
                if (!c.upgraded) {
                    c.baseDamage = 12 + 2*this.amount;
                } else {
                    c.baseDamage = 12 + 4*this.amount;
                }
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (Objects.equals(c.cardID, "llz_zuangjzq")) {
                if (!c.upgraded) {
                    c.baseDamage = 12 + 2*this.amount;
                } else {
                    c.baseDamage = 12 + 4*this.amount;
                }
            }
        }
    }

    public void onDrawOrDiscard() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (Objects.equals(c.cardID, "llz_zuangjzq")) {
                if (!c.upgraded) {
                    c.baseDamage = 12 + 2*this.amount;
                } else {
                    c.baseDamage = 12 + 4*this.amount;
                }
            }
        }
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}