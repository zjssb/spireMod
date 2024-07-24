package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * llz_dianHQG:电弧切割
 * 电刀每回合给予的牌
 */
public class llz_dianHQG extends CustomCard {
    public static final String ID = "llz_dianHQG";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/dianhqg.png";
    private static final int COST = 1;  //卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;  //卡牌类型,攻击牌、技能牌、能力牌、诅咒牌、状态牌
    private static final CardColor COLOR = CardColor.COLORLESS ;//卡牌颜色，比如原版的红、绿、蓝、紫、无色，诅咒
    private static final CardRarity RARITY = CardRarity.SPECIAL;//卡牌稀有度。
    private static final CardTarget TARGET = CardTarget.ENEMY;//卡牌指向类型的目标。实际功能只有是否指向敌人的区分。

    public llz_dianHQG(){
        this(0);
    }

    public llz_dianHQG(int damage){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = damage;
        this.isInnate = false;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            upgradeBaseCost(0);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractCard makeCopy() {
        return new llz_dianHQG();
    }
}
