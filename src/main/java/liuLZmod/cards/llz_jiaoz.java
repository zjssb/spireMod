package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.AllGaizAction;

/**
 * 校准
 */
public class llz_jiaoz extends CustomCard {
    public static final String ID = "llz_jiaoz";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/jiaoz.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_jiaoz() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new Dazed();
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
        int i =0;
        addToBot(new AllGaizAction(1));
        if(this.upgraded)addToBot(new AllGaizAction(1));
        for (AbstractCard c : p.hand.group) {
            if (i <10 && c != null && (c.baseDamage > 0 || c.baseBlock > 0 || c.type == AbstractCard.CardType.STATUS)) {
                i++;
            }
        }
        if(i >5) {
            addToBot(new MakeTempCardInDiscardAction(new Dazed(), 5));
            addToBot(new WaitAction(1.0F));
            addToBot(new MakeTempCardInDiscardAction(new Dazed(), i-5));
        }else if(i >0)addToBot(new MakeTempCardInDiscardAction(new Dazed(), i));
    }

    public AbstractCard makeCopy() {
        return new llz_jiaoz();
    }


}

