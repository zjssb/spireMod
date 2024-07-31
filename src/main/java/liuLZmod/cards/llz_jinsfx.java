package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import liuLZmod.Characters.MyCharacter;

/**
 * 金属防线
 */
public class llz_jinsfx extends CustomCard {
    public static final String ID = "llz_jinsfx";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/jinsfx.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_jinsfx() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 15;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (GameActionManager.turn < 4) {
            this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            if (GameActionManager.turn >= 4) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
    }
    public AbstractCard makeCopy() {
        return new llz_jinsfx();
    }


}

