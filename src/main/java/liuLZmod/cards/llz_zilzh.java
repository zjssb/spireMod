package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.abstracts.jiXieAction;
import liuLZmod.action.zilzhAction;
import liuLZmod.modcore.liuLZMod;

/**
 * 质能转换
 */
public class llz_zilzh extends CustomCard {
    public static final String ID = "llz_zilzh";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/zilzh.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_zilzh() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 6;
        this.tags.add(liuLZMod.SAOWEI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(-2);
        }

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new jiXieAction("llz_shaoW"));
        addToBot(new jiXieAction(-999));
        addToBot(new zilzhAction(this.magicNumber));
    }
    public AbstractCard makeCopy() {
        return new llz_zilzh();
    }


}

