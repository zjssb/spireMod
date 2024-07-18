package liuLZmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

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

    public llz_shengNaPower(AbstractCreature owner,AbstractMonster m) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;

        if(llz_shengNaPower.m != null){
            llz_shengNaPower.m.powers.remove(this);
            this.addToTop(new RemoveSpecificPowerAction(llz_shengNaPower.m, llz_shengNaPower.m, ID));
        }
        llz_shengNaPower.m = m;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;

        // 添加一大一小两张能力图
        String path128 = "ModliuLZ/img/powers/sengn_p.png";
        String path48 = "ModliuLZ/img/powers/sengn.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 128, 128);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 48, 48);

        // 首次添加能力更新描述
        this.updateDescription();
    }


}
