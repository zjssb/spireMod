package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.zjDamageAction;

/**
 * 超负荷
 */
public class llz_caofh extends CustomCard {
    public static final String ID = "llz_caofh";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/caofh.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.LIULANGZE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_caofh() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 0;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            addToBot(new GainEnergyAction(2));
        }else addToBot(new GainEnergyAction(3));
        int count = 0;
        for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (card.cardID.equals(this.cardID) && count <9) {
                count++;
            }
        }
        //if(!this.upgraded && count <9)count++;

        this.baseMagicNumber = count;
        this.magicNumber = this.baseMagicNumber;
        for (int i=0;i < count;i++){
            addToBot(new zjDamageAction(i+1));
        }


        if(this.upgraded){
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        }else {
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
        }
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new llz_caofh();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        int count = 1;
        for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (card.cardID.equals(this.cardID) && count <9) {
                count++;
            }
        }
        //if(!this.upgraded && count <9)count++;

        this.baseMagicNumber = count;
        this.magicNumber = this.baseMagicNumber;

        if(this.upgraded){
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        }else {
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
        }
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        if(this.upgraded){
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        }else {
            this.rawDescription = CARD_STRINGS.DESCRIPTION;
        }
        initializeDescription();
    }
}
