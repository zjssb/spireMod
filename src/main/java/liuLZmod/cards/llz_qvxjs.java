package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.qvxjsAction;

/**
 * 曲线加速
 */
public class llz_qvxjs extends CustomCard {
    public static final String ID = "llz_qvxjs";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/qvxjs.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_qvxjs() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        int I = 0;
        if (this.upgraded) {
            I = -1;
        }
        addToBot(new qvxjsAction(p, this.upgraded,I));
        if(this.upgraded){
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        }else this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
             super.applyPowers();
        if(this.upgraded){
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.magicNumber = GameActionManager.turn +1;
            this.baseMagicNumber = GameActionManager.turn +1;
        }else {
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
            this.magicNumber = GameActionManager.turn;
            this.baseMagicNumber = GameActionManager.turn;
        }
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void onMoveToDiscard() {
        if(this.upgraded){
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        }else this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }


    public AbstractCard makeCopy() {
        return new llz_qvxjs();
    }


}

