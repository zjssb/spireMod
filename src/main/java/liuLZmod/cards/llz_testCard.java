package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import basemod.devcommands.hand.HandAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.monster.*;
import liuLZmod.powers.llz_shengNaPower;

public class llz_testCard extends CustomCard {
    public static final String ID = "llz_testCard";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = "测试卡牌1";
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/Strike.png";
    private static final int COST = 1;  //卡牌费用
    private static final String DESCRIPTION = "测试卡牌1"; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;  //卡牌类型,攻击牌、技能牌、能力牌、诅咒牌、状态牌
    private static final CardColor COLOR = MyCharacter.Enums.LIULANGZE_CARD;//卡牌颜色，比如原版的红、绿、蓝、紫、无色，诅咒
    private static final CardRarity RARITY = CardRarity.BASIC;//卡牌稀有度。
    private static final CardTarget TARGET = CardTarget.ENEMY;//卡牌指向类型的目标。实际功能只有是否指向敌人的区分。

    public llz_testCard(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;
        this.isInnate = true;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //llz_shaoW.SpawnMinion();
        //llz_dianD.SpawnMinion();
        //llz_zhengQJ.SpawnMinion();
        //llz_yuQ.SpawnMinion();
        //llz_xuYing.SpawnMinion();
    }
}
