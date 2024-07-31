package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;

/**
 *赝品
 */
public class llz_paog extends CustomCard {
    public static final String ID = "llz_paog";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/paog.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_paog() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.exhaust = false;
            this.initializeDescription();
        }

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        int i =0;
        for (AbstractCard c : p.hand.group) {
            if (i <10 && c != null &&c.type == AbstractCard.CardType.STATUS) {
                addToBot(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
                i++;
            }
        }
        if(i >0)addToBot(new GainEnergyAction(i));
    }

    public AbstractCard makeCopy() {
        return new llz_paog();
    }


}

