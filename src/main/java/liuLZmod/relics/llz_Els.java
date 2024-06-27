package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

/**
 * 鹅卵石
 */
public class llz_Els extends CustomRelic {
    // 遗物ID
    public static final String ID = "llz_Els";
    // 图片路径
    private static final String IMG_PATH = "ModliuLZ/img/relics/els.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.SOLID;

    // 已给予的敏捷数量
    private int dexGiven = 0;

    public llz_Els() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        this.counter = 1;
    }

    @Override
    public void onEquip() {
        flash();
    }

    @Override
    public void atTurnStart() {
        // 如果已给予的敏捷数量小于counter值，则在回合开始时给予1点敏捷
        if (dexGiven < this.counter) {
            flash();
            dexGiven++;
            if(dexGiven == this.counter){this.grayscale = true;}
            addToTop((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) AbstractDungeon.player, 1), 1));
            addToTop((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
        }
    }

    public void justEnteredRoom(AbstractRoom room) {
        dexGiven = 0;
        this.grayscale = false;
    }

    // 获取遗物描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_Els();
    }
}
