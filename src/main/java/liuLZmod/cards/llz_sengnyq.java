package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.abstracts.jiXieAction;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.monster.llz_shaoW;
import liuLZmod.monster.llz_yuQ;
import liuLZmod.powers.llz_shengNaPower;

import java.util.Objects;

import static liuLZmod.monster.abstracrt.abstract_llz_jiXie.jiXie_list;

/**
 * 声呐鱼群
 */

public class llz_sengnyq extends CustomCard {
    public static final String ID = "llz_sengnyq";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/sengnyq.png";
    private static final int COST = 2;  //卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public llz_sengnyq() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.initializeDescription();
        }

    }


    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new VFXAction(new BiteEffect(m.hb.cX +
                    MathUtils.random(-50.0F, 50.0F) * Settings.scale, m.hb.cY +
                    MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.CHARTREUSE.cpy()), 0.2F));

            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }

        if(llz_shaoW.T != null){
            llz_shaoW.remove();
            if(llz_yuQ.YQ != null) llz_yuQ.SpawnMinion();
            else addToBot(new jiXieAction("llz_yuQ"));
        }else AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CARD_STRINGS.UPGRADE_DESCRIPTION, true));
        if (! m.hasPower("llz_shengNa"))addToBot(new ApplyPowerAction(m, p, new llz_shengNaPower(m,m)));
    }
    public AbstractCard makeCopy() {
        return new llz_sengnyq();
    }

}