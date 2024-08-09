package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.abstracts.jiXieAction;
import liuLZmod.monster.llz_ZZWZ;
import liuLZmod.monster.llz_shaoW;

import static liuLZmod.monster.llz_shaoW.shaoweiList;

/**
 * 战争武装
 */
public class llz_zanzwz extends CustomCard {
    public static final String ID = "llz_zanzwz";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/zanzwz.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_zanzwz() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(-1);
            this.initializeDescription();
        }

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        int i;
        i = shaoweiList.size();
        if(llz_ZZWZ.ZZWZ == null || llz_ZZWZ.isSecondPhase){
            if (i >= this.magicNumber) {
                for (int j = 0; j < this.magicNumber; j++) llz_shaoW.remove();
                addToBot(new jiXieAction("llz_ZZWZ"));
            } else
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CARD_STRINGS.UPGRADE_DESCRIPTION, true));
        }else AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CARD_STRINGS.UPGRADE_DESCRIPTION, true));
    }

    public AbstractCard makeCopy() {
        return new llz_zanzwz();
    }


}

