package liuLZmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

/**
 * 创世纪
 */
public class llz_cuangsjPowers extends AbstractPower {
    public static final String POWER_ID = "llz_cuangsj";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public llz_cuangsjPowers(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;

        String path128 = "ModliuLZ/img/powers/cuangsj_p.png";
        String path48 = "ModliuLZ/img/powers/cuangsj.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 128, 128);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 48, 48);

        this.updateDescription();
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        AbstractPower randomPower = getRandomPower(this.owner, this.amount);
        if (randomPower != null) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, randomPower, this.amount));
        }
    }

    private AbstractPower getRandomPower(AbstractCreature owner, int amount) {
        int roll = AbstractDungeon.cardRandomRng.random(0, 23);

        switch (roll) {
            case 0:
                return new llz_cuangsjPowers(owner, amount);
            case 1:
                return new StrengthPower(owner, amount);
            case 2:
                return new DexterityPower(owner, amount);
            case 3:
                return new llz_jih(owner, amount);
            case 4:
                return new llz_cuansgdPowers(owner, amount);
            case 5:
                return new llz_huawwyPowers(owner, amount);
            case 6:
                return new llz_jinmyqPowers(owner, amount);
            case 7:
                return new ArtifactPower(owner, amount);
            case 8:
                return new MetallicizePower(owner, amount);
            case 9:
                return new PlatedArmorPower(owner, amount);
            case 10:
                return new ThornsPower(owner, amount);
            case 11:
                return new IntangiblePlayerPower(owner, amount);
            case 12:
                return new BufferPower(owner, amount);
            case 13:
                return new VigorPower(owner, amount);
            case 14:
                return new NextTurnBlockPower(owner, amount);
            case 15:
                return new rour(owner, amount);
            case 16:
                return new DrawPower(owner, amount);
            case 17:
                return new EnergizedBluePower(owner, amount);
            case 18:
                return new RitualPower(owner, amount,true);
            case 19:
                return new llz_suiyqsPowers(owner, amount);
            case 20:
                return new llz_wanqPowers(owner, amount);
            case 21:
                return new llz_ziwjgPowers(owner, amount);
            case 22:
                return new llz_zutwzPowers(owner, amount);
            case 23:
                return new DuplicationPower(owner, amount);
            default:
                return null;
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
