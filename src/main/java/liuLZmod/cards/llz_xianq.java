package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.abstracts.jiXieAction;
import liuLZmod.modcore.liuLZMod;

/**
 * 先驱
 */
public class llz_xianq extends CustomCard {

    public static final String ID = "llz_xianq";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/xianq.png";
    private static final int COST = 0;  //卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;  //卡牌类型,攻击牌、技能牌、能力牌、诅咒牌、状态牌
    private static final CardColor COLOR = MyCharacter.Enums.LIULANGZE_CARD;//卡牌颜色，比如原版的红、绿、蓝、紫、无色，诅咒
    private static final CardRarity RARITY = CardRarity.BASIC;//卡牌稀有度。
    private static final CardTarget TARGET = CardTarget.ENEMY;//卡牌指向类型的目标。实际功能只有是否指向敌人的区分。

    public llz_xianq() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 3;
        this.cardsToPreview = new Wound();
        this.tags.add(liuLZMod.SAOWEI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.cardsToPreview = new llz_hej();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview,1));
        addToBot(new jiXieAction("llz_shaoW"));
    }
    public AbstractCard makeCopy() {
             return new llz_xianq();
    }

}
