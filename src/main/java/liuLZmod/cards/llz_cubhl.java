package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.DowngradeCardAction;
import liuLZmod.powers.llz_cubhlPowers;
import liuLZmod.vfx.SpineEffect;

/**
 * 储备火力
 */
public class llz_cubhl extends CustomCard {
    public static final String ID = "llz_cubhl";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/cubhl.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_cubhl() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 99;
        this.baseBlock = 16;
        this.exhaust = true;

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isEthereal = true;
            this.target = CardTarget.ENEMY;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        if(this.upgraded){
            if(m != null){
                SpineEffect spineEffect = new SpineEffect("ModliuLZ/img/vfx/daodan/skeleton.atlas", "ModliuLZ/img/vfx/daodan/skeleton37.json", "new", p.drawX,p.drawY+100,1.5f);
                addToBot(new VFXAction(spineEffect,0.2f));
                addToBot(new ApplyPowerAction(m, p, new llz_cubhlPowers(m, this.magicNumber), this.magicNumber));
            }
            addToBot(new DowngradeCardAction(this.uuid, p));
        }
    }

    public AbstractCard makeCopy() {
        return new llz_cubhl();
    }


}

