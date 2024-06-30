package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.zjDamageAction;

/**
 * 战术诱导
 */
public class llz_zansyd extends CustomCard {
    public static final String ID = "llz_zansyd";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/zansyd.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_zansyd() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 15;
        this.magicNumber = this.baseMagicNumber = 5;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new zjDamageAction(this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
    }
    public AbstractCard makeCopy() {
        return new llz_zansyd();
    }


}

