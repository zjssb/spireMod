package liuLZmod.Variable;
import basemod.abstracts.DynamicVariable;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
import liuLZmod.Abstract.AbstractLlzCard;

/**
 * 自定义变量引用
 */
/*    */ public class DefaultSecondMagicNumber
        /*    */   extends DynamicVariable
        /*    */ {
    /*    */   public String key() {
        /* 15 */     return "LlzMagic";
        /*    */   }
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */   public boolean isModified(AbstractCard card) {
        /* 23 */     return ((AbstractLlzCard)card).isDefaultSecondMagicNumberModified;
        /*    */   }
    /*    */
    /*    */
    /*    */
    /*    */   public int value(AbstractCard card) {
        /* 29 */     return ((AbstractLlzCard)card).defaultSecondMagicNumber;
        /*    */   }
    /*    */
    /*    */
    /*    */   public int baseValue(AbstractCard card) {
        /* 34 */     return ((AbstractLlzCard)card).defaultBaseSecondMagicNumber;
        /*    */   }
    /*    */
    /*    */
    /*    */   public boolean upgraded(AbstractCard card) {
        /* 39 */     return ((AbstractLlzCard)card).upgradedDefaultSecondMagicNumber;
        /*    */   }
    /*    */ }