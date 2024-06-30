package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.vfx.SeJiEffect;

/**
 * 钢铁洪流
 */
public class llz_gangthl extends CustomCard {
    public static final String ID = "llz_gangthl";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/gangthl.png";
    private static final int COST = 3;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public llz_gangthl() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 4;
        this.isMultiDamage = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
        }

    }


    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("ATTACK_WHIRLWIND"));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WhirlwindEffect(), 0.0F));
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new SeJiEffect(m.hb.cX, m.hb.cY)));
        for (int i = 0; i < 2; i++) {
            addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
        }
        for (int i = 0; i < 3; i++) {
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn,AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }
    public AbstractCard makeCopy() {
        return new llz_gangthl();
    }

}