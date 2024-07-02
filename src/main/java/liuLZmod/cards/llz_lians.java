package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ForTheEyesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import liuLZmod.Characters.MyCharacter;
import com.badlogic.gdx.graphics.Color;

/**
 * 碾碎
 */

public class llz_lians extends CustomCard {
    public static final String ID = "llz_lians";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/lians.png";
    private static final int COST = 3;  //卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public llz_lians() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 20;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeDamage(4);
            upgradeMagicNumber(2);
            this.initializeDescription();
        }

    }


    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
                   addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
                   for (int i = 0; i < 5; i++) {
                         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(m.hb.cX, m.hb.cY)));
                       }
                 } else {
                   addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
                   for (int i = 0; i < 5; i++) {
                         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(m.hb.cX, m.hb.cY)));
                       }
                 }
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction)new ForTheEyesAction(this.magicNumber, m));

    }
    public void triggerOnGlowCheck() {
             this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
             for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                   if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
                         this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                         break;
                       }
                 }
           }
    public AbstractCard makeCopy() {
             return new llz_lians();
           }

}