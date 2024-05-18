package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import liuLZmod.action.gaizAction;
import liuLZmod.patches.EnumPatch;

import java.util.List;

/**
 * 淬火
 */

public class llz_cuih extends CustomCard {
    public static final String ID = "llz_cuih";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/cuih.png";
    private static final int COST = 1;  //卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public llz_cuih() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            upgradeMagicNumber(1);
            this.initializeDescription();
        }

    }


    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), EnumPatch.CUIH_GJ));
        //addToBot((AbstractGameAction)new SelectCardsInHandAction(1, (CardCrawlGame.languagePack.getUIString("champ:EnchantUI")).TEXT[1], c -> (c.baseDamage > 0), cards -> ((AbstractCard)cards.get(0)).baseDamage += this.magicNumber));
        addToBot((AbstractGameAction)new SelectCardsInHandAction(1, "改造", c -> (c.baseDamage > 0), cards -> new gaizAction(cards)));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
             boolean canUse = false;
             for (AbstractCard c : p.hand.group) {
                   if (c.baseDamage > 0) {
                         canUse = true;
                         break;
                       }
                 }
             if (!canUse) {
                   //this.cantUseMessage = this.EXTENDED_DESCRIPTION[0];
                   return false;
                 }
             return super.canUse(p, m);
           }

}