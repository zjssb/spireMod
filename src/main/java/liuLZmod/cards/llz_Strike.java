package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.patches.EnumPatch;


public class llz_Strike extends CustomCard {
    public static final String ID = "llz_Strike";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/Strike.png";
    private static final int COST = 1;  //卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;  //卡牌类型,攻击牌、技能牌、能力牌、诅咒牌、状态牌
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;//卡牌颜色，比如原版的红、绿、蓝、紫、无色，诅咒
    private static final CardRarity RARITY = CardRarity.BASIC;//卡牌稀有度。
    private static final CardTarget TARGET = CardTarget.ENEMY;//卡牌指向类型的目标。实际功能只有是否指向敌人的区分。

    public llz_Strike() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;
        this.tags.add(CardTags.STARTER_STRIKE);//添加STARTER_STRIKE（基础打击）让潘多拉变化这张牌
        this.tags.add(CardTags.STRIKE);//添加STRIKE（打击）让完美打击计算这张牌
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeDamage(3); // 将该卡牌的伤害提高3点。
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }


    @Override
    /*
     * 当卡牌被使用时，调用这个方法。
     *
     * @param p 你的玩家实体类。
     * @param m 指向的怪物类。（无指向时为null，包括攻击所有敌人时）
     */
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage,DamageInfo.DamageType.NORMAL))//type是伤害类型。攻击伤害NORMAL，非攻击伤害THORNS，失去生命HP_LOS
        );*/
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }
    public AbstractCard makeCopy() {
        /* 44 */     return new llz_Strike();
        /*    */   }

}