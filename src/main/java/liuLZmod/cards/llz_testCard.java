package liuLZmod.cards;

import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.monsters.llz_shaowei;

public class llz_testCard extends CustomCard {
    public static final String ID = "llz_testCard";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/Strike.png";
    private static final int COST = 1;  //卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;  //卡牌类型,攻击牌、技能牌、能力牌、诅咒牌、状态牌
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;//卡牌颜色，比如原版的红、绿、蓝、紫、无色，诅咒
    private static final CardRarity RARITY = CardRarity.BASIC;//卡牌稀有度。
    private static final CardTarget TARGET = CardTarget.ENEMY;//卡牌指向类型的目标。实际功能只有是否指向敌人的区分。

    public llz_testCard(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        llz_shaowei sw = new llz_shaowei(0f,0f);
        sw.drawX = AbstractDungeon.player.drawX;
        sw.drawY = AbstractDungeon.player.drawY;
        sw.index=1;

        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(sw, true));

        if(llz_shaowei.ll != null){
            System.out.println(llz_shaowei.ll.drawX);
            System.out.println(llz_shaowei.ll.isDead);
        }else{
            llz_shaowei.ll = sw;
        }

    }
}
