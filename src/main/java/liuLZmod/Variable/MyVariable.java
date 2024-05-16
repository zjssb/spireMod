package liuLZmod.Variable;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import liuLZmod.Abstract.AbstractMyCard;

/**
 * 自定义变量
 */
public class MyVariable extends DynamicVariable
{
    @Override
    public String key()
    {
        return "llzmod:sz";
        // 您在本地化文件中放置的内容！来展现你的价值。例如，！myKey！。
    }

    @Override
    public boolean isModified(AbstractCard card)
    {
        if (card instanceof AbstractMyCard) {
            AbstractMyCard myCard = (AbstractMyCard) card;
            return myCard.isSecondMagicNumberModified;
        }
        return false;
        // 如果该值是从基值修改的，则设置为 true。
    }

    /*@Override
    public void setIsModified(AbstractCard card, boolean v)
    {
        // 执行一些操作，使 isModified 返回值 v。
        // 仅当您希望 smith 升级预览正常运行时才需要此方法。
    }*/

    @Override
    public int value(AbstractCard card)
    {
        if (card instanceof AbstractMyCard) {
            AbstractMyCard myCard = (AbstractMyCard) card;
            return myCard.SecondMagicNumber;
        }
        return 0;
        //动态变量将在您的卡上设置为什么。通常使用存储在卡上的某种整数。
    }

    @Override
    public int baseValue(AbstractCard card)
    {
        if (card instanceof AbstractMyCard) {
            AbstractMyCard myCard = (AbstractMyCard) card;
            return myCard.BaseSecondMagicNumber;
        }
        return 0;
        // 一般应该就是上面的。
    }

    @Override
    public boolean upgraded(AbstractCard card)
    {
        if (card instanceof AbstractMyCard) {
            AbstractMyCard myCard = (AbstractMyCard) card;
            return myCard.upgradedSecondMagicNumber;
        }
        return false;
        //如果卡已升级并且值已更改，则应返回 true
    }
}