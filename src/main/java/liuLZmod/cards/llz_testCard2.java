package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.monsters.*;

/**
 * llz_testCard2: 测试卡牌2
 */
public class llz_testCard2 extends CustomCard {

    public static final String ID = "llz_testCard2";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = "测试卡牌2";
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/Strike.png";
    private static final int COST = 1;  //卡牌费用
    private static final String DESCRIPTION = "测试卡牌2"; // 读取本地化的描述
    private static final AbstractCard.CardType TYPE = CardType.SKILL;  //卡牌类型,攻击牌、技能牌、能力牌、诅咒牌、状态牌
    private static final AbstractCard.CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;//卡牌颜色，比如原版的红、绿、蓝、紫、无色，诅咒
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;//卡牌稀有度。
    private static final AbstractCard.CardTarget TARGET = CardTarget.NONE;//卡牌指向类型的目标。实际功能只有是否指向敌人的区分。

    public llz_testCard2() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 0;
        this.isInnate = true;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        llz_zhengQJ.act();
    }
}
