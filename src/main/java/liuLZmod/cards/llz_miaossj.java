package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.miaossjAction;
import liuLZmod.vfx.SeJiEffect;


public class llz_miaossj extends CustomCard {
    public static final String ID = "llz_miaossj";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/miaossj.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    int count;

    public llz_miaossj() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 2;
        this.magicNumber = this.baseMagicNumber = 4;
        count =4;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(2);
            count = 6;
            this.initializeDescription();
        }

    }


    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i=0;i<this.magicNumber;i++){
            addToBot(new VFXAction(new SeJiEffect(m.hb.cX, m.hb.cY)));
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
        addToBot(new miaossjAction(count,this));
    }
    public void triggerOnOtherCardPlayed(final AbstractCard c) {
        if(this.magicNumber >0){
            magicNumber--;
            baseMagicNumber--;
        }
    }

    public void onMoveToDiscard() {addToBot(new miaossjAction(count,this));}
    public void atTurnStart() {addToBot(new miaossjAction(count,this));}
    public void triggerWhenDrawn() {addToBot(new miaossjAction(count,this));}

    public AbstractCard makeCopy() {
        return new llz_miaossj();
    }

}