package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import liuLZmod.Characters.MyCharacter;

/**
 * 仿生茧
 */
public class llz_fangsj extends CustomCard {
    public static final String ID = "llz_fangsj";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/fangsj.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.LIULANGZE_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_fangsj() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 4;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(2);
            this.initializeDescription();
        }

    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        int i = 0;
        for (AbstractCard c : p.hand.group) {
            if (!c.canUse(p,null)) {
                c.superFlash();
                i++;
            }
        }
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber +i), this.magicNumber +i));
    }

    public void applyPowers() {
        super.applyPowers();
        int i = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!c.canUse(AbstractDungeon.player,null)) {
                i++;
            }
        }
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0] + (this.magicNumber +i);
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new llz_fangsj();
    }


}

