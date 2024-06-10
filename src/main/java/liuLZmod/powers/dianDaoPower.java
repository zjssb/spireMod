package liuLZmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import liuLZmod.cards.llz_dianHQG;
import liuLZmod.monsters.llz_dianD;

/**
 * testPower:
 */
public class dianDaoPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "llz_dianDaoPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public dianDaoPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;

        // 添加一大一小两张能力图
        String path128 = "ModliuLZ/img/powers/diand_p.png";
        String path48 = "ModliuLZ/img/powers/diand.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1];
    }
    public void atStartOfTurn() {
        // 发牌
    }//触发时机：当玩家回合开始时触发。

    public static void StartOfTurn(){
//        AbstractDungeon.actionManager.addToTurnStart(new MakeTempCardInHandAction(new llz_dianHQG(llz_dianD.getEnergy()*10)));
    }

    public int onLoseHp(final int damageAmount) {
        return damageAmount;
    }//触发时机：当失去生命值时，返回伤害数值，可用来修改伤害数值。
}
