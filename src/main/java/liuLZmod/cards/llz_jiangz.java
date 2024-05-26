package liuLZmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 降噪
 */
public class llz_jiangz extends CustomCard {
    public static final String ID = "llz_jiangz";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ModliuLZ/img/cards_2/jiangz.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.STATUS;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public llz_jiangz() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
             int statusCount = 0;
             int i;
             for (i = 0; i < p.drawPile.size(); ) {
                   AbstractCard c = p.drawPile.group.get(i);
                   if (c.type == AbstractCard.CardType.STATUS) {
                         statusCount++;
                         p.drawPile.removeCard(c);
                         p.limbo.addToTop(c);
                         c.targetDrawScale = 0.5F;
                         c.setAngle(0.0F, true);
                         c.target_x = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_WIDTH, Settings.WIDTH - AbstractCard.IMG_WIDTH);
                         c.target_y = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_HEIGHT, Settings.HEIGHT - AbstractCard.IMG_HEIGHT);
                         addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c, p.limbo));
                         addToBot((AbstractGameAction)new WaitAction(0.1F)); continue;
                       }
                   i++;
                 }


             for (i = 0; i < p.discardPile.size(); ) {
                   AbstractCard c = p.discardPile.group.get(i);
                   if (c.type == AbstractCard.CardType.STATUS) {
                         statusCount++;
                         p.discardPile.removeCard(c);
                         p.limbo.addToTop(c);
                         c.targetDrawScale = 0.5F;
                         c.setAngle(0.0F, true);
                         c.target_x = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_WIDTH, Settings.WIDTH - AbstractCard.IMG_WIDTH);
                         c.target_y = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_HEIGHT, Settings.HEIGHT - AbstractCard.IMG_HEIGHT);
                         addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c, p.limbo));
                         addToBot((AbstractGameAction)new WaitAction(0.1F)); continue;
                       }
                   i++;
                 }


             for (i = 0; i < p.hand.size(); ) {
                   AbstractCard c = p.hand.group.get(i);
                   if (c.type == AbstractCard.CardType.STATUS) {
                         statusCount++;
                         p.hand.removeCard(c);
                         p.limbo.addToTop(c);
                         c.targetDrawScale = 0.5F;
                         c.setAngle(0.0F, true);
                         c.target_x = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_WIDTH, Settings.WIDTH - AbstractCard.IMG_WIDTH);
                         c.target_y = AbstractDungeon.cardRandomRng.random(AbstractCard.IMG_HEIGHT, Settings.HEIGHT - AbstractCard.IMG_HEIGHT);
                         addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c, p.limbo));
                         addToBot((AbstractGameAction)new WaitAction(0.1F)); continue;
                       }
                   i++;
                 }


             if (statusCount > 0) {
                   i =statusCount *this.magicNumber;
                   addToBot(new GainBlockAction(p, p,i));
                 }
           }
    public AbstractCard makeCopy() {
        /* 44 */     return new llz_jiangz();
        /*    */   }


}

