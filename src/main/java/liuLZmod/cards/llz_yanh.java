package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.Characters.MyCharacter;
import liuLZmod.action.abstracts.jiXieAction;
import liuLZmod.modcore.liuLZMod;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;

import java.util.Objects;

import static liuLZmod.monster.abstracrt.abstract_llz_jiXie.jiXie_list;
import static liuLZmod.monster.llz_shaoW.shaoweiList;

/**
 * 掩护
 */
public class llz_yanh extends CustomCard {
    public static final String ID = "llz_yanh";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/yanh.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = MyCharacter.Enums.EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_yanh() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 4;
        this.tags.add(liuLZMod.SAOWEI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        int i =(int) shaoweiList.stream().filter(sw -> !sw.isDeath).count();
        for (abstract_llz_jiXie jiXie : jiXie_list){
            if(!Objects.equals(jiXie.id, "llz_shaoW") && !Objects.equals(jiXie.id, "llz_shaoWT")) {
                i++;
            }
        }
        addToBot(new jiXieAction("llz_shaoW"));
        addToBot(new GainBlockAction(p, p, this.block +i));
    }
    public AbstractCard makeCopy() {
        return new llz_yanh();
    }


}

