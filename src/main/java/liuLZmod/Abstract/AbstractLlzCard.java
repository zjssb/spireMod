package liuLZmod.Abstract;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

/**
 * 自定义变量
 */
/*     */ public abstract class AbstractLlzCard
        /*     */   extends CustomCard {
    /*  26 */   static ArrayList<AbstractCard> allCards = new ArrayList<>();
    /*     */   static boolean doneInit = false;
    /*     */   public boolean trig_deadon = false;
    /*     */   public int defaultSecondMagicNumber;
    /*     */
    /*     */   public static void init() {
        /*  32 */     doneInit = true;
        /*     */   }
    /*     */
    /*     */
    /*     */   public int defaultBaseSecondMagicNumber;
    /*     */   public boolean upgradedDefaultSecondMagicNumber;
    /*     */   public boolean isDefaultSecondMagicNumberModified;
    /*     */   public static boolean lastCardDeadOn = false;
    /*  40 */   public static ArrayList<Boolean> deadOnThisTurn = new ArrayList<>();
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public String betaArtPath;
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public AbstractLlzCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        /*  52 */     super(id, name, img, cost, rawDescription, type, color, rarity, target);
        /*     */
        /*     */
        /*  55 */     this.isCostModified = false;
        /*  56 */     this.isCostModifiedForTurn = false;
        /*  57 */     this.isDamageModified = false;
        /*  58 */     this.isBlockModified = false;
        /*  59 */     this.isMagicNumberModified = false;
        /*  60 */     this.isDefaultSecondMagicNumberModified = false;
        /*     */   }
    /*     */
    /*     */   public void displayUpgrades() {
        /*  64 */     super.displayUpgrades();
        /*  65 */     if (this.upgradedDefaultSecondMagicNumber) {
            /*  66 */       this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;
            /*  67 */       this.isDefaultSecondMagicNumberModified = true;
            /*     */     }
        /*     */   }
    /*     */
    /*     */
    /*     */   public void upgradeDefaultSecondMagicNumber(int amount) {
        /*  73 */     this.defaultBaseSecondMagicNumber += amount;
        /*  74 */     this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;
        /*  75 */     this.upgradedDefaultSecondMagicNumber = true;
        /*     */   }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public AbstractCard makeStatEquivalentCopy() {
        /*  88 */     AbstractCard original = super.makeStatEquivalentCopy();
        /*     */
        /*  90 */     ((AbstractLlzCard)original).trig_deadon = this.trig_deadon;
        /*     */
        /*  92 */     return original;
        /*     */   }
    /*     */ }