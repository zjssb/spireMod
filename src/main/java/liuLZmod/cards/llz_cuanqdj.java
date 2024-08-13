package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import liuLZmod.action.CuanQDJAction;
import liuLZmod.action.miaossjAction;

/**
 * 传奇打击
 */

public class llz_cuanqdj extends CustomCard {
    public static final String ID = "llz_cuanqdj";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/cuanqdj.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = MyCharacter.Enums.LIULANGZE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    boolean Boss;
    boolean ELITE;
    int su;

    public llz_cuanqdj() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.misc = 3001;
        su = (int) (misc*0.001);
        if (!this.upgraded){
            this.baseDamage = su;
        }else this.baseDamage = (su+3);
        this.magicNumber = this.baseMagicNumber = (misc - su*1000);
        Boss = false;
        ELITE = false;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }

    }


    @Override

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i =0;i <this.magicNumber;i++){
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }
    public void atTurnStart() {
        if(!Boss && !ELITE){
            for (AbstractMonster M : (AbstractDungeon.getMonsters()).monsters) {
                if (M.type == AbstractMonster.EnemyType.BOSS) {
                    Boss = true;
                    break;
                }
                if (M.type == AbstractMonster.EnemyType.ELITE) {
                    ELITE = true;
                    break;
                }
            }
            addToBot(new CuanQDJAction(this.uuid,this.misc,ELITE,Boss));
        }
    }

    public void applyPowers() {
        su = (int) (misc*0.001);
        if (!this.upgraded){
            this.baseDamage = su;
        }else this.baseDamage = (su+3);
        super.applyPowers();
        initializeDescription();
    }
    public AbstractCard makeCopy() {
        return new llz_cuanqdj();
    }

}