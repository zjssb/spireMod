package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.vfx.moryyEffect;

/**
 * 末日预演
 */
public class llz_moryy extends CustomCard {
    public static final String ID = "llz_moryy";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/moryy.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_moryy() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(11);
        }

    }


    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
        addToBot((AbstractGameAction)new SFXAction("ATTACK_DEFECT_BEAM"));
        if (Settings.FAST_MODE) {
                   addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new moryyEffect(), 0.7F));
                 } else {
                   addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new moryyEffect(), 1.0F));
                 }
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        addToBot((AbstractGameAction)new GainEnergyAction(3));

    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
             if (GameActionManager.turn < 7) {
                   this.cantUseMessage = CARD_STRINGS.UPGRADE_DESCRIPTION;
                   return false;
                 }
             return true;
           }
    public AbstractCard makeCopy() {
        return new llz_moryy();
    }

}