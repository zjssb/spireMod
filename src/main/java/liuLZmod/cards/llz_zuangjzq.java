package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.patches.EnumPatch;
import liuLZmod.powers.llz_guor;
import liuLZmod.powers.rour;

/**
 * 装甲重拳
 */

public class llz_zuangjzq extends CustomCard {
    public static final String ID = "llz_zuangjzq";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/zuangjzq.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = MyCharacter.Enums.LIULANGZE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public static final int ATTACK_DMG = 12;
    public static int UPG_ATTACK_DMG = 2;

    public llz_zuangjzq() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        if(this.upgraded)UPG_ATTACK_DMG = 4;
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("llz_guor")) {
                   this.baseDamage = ATTACK_DMG + UPG_ATTACK_DMG*(AbstractDungeon.player.getPower("llz_guor")).amount;
                 } else {
                   this.baseDamage = ATTACK_DMG;
                 }
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(2);
        }

    }


    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), EnumPatch.ZUANGJZQ_GJ));
        if (! AbstractDungeon.player.hasPower("llz_guor"))addToBot(new ApplyPowerAction(p, p, new llz_guor(p,0),0));
    }
    public AbstractCard makeCopy() {
        return new llz_zuangjzq();
    }

}