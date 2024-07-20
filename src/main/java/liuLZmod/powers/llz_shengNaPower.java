package liuLZmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

/**
 * 声纳能力：与机械：与鱼群联动，
 * 鱼群优先攻击带有声纳能力的敌人
 * 全场唯一
 */
public class llz_shengNaPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "llz_shengNa";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    /**全场唯一携带声纳buff的敌人*/
    public static AbstractMonster m = null;

    public llz_shengNaPower(AbstractCreature owner, AbstractMonster m) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 移除所有怪物上的llz_shengNaPower能力
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster.hasPower(POWER_ID) && monster != this.owner) {
                monster.powers.removeIf(power -> power.ID.equals(POWER_ID));
                this.addToTop(new RemoveSpecificPowerAction(monster, monster, POWER_ID));
            }
        }

        // 设置新的携带该能力的敌人
        llz_shengNaPower.m = m;

        // 设置能力数量
        this.amount = -1;

        // 添加一大一小两张能力图
        String path128 = "ModliuLZ/img/powers/sengn_p.png";
        String path48 = "ModliuLZ/img/powers/sengn.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 128, 128);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 48, 48);

        // 首次添加能力更新描述
        this.updateDescription();
    }


    public void onDeath() {
        llz_shengNaPower.m = null;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
