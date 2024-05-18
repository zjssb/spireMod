package liuLZmod.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.rewards.RewardItem;

/**
 * 攻击特效声明
 */
public class EnumPatch {
    @SpireEnum
    public static RewardItem.RewardType HERMIT_BOUNTY;

    @SpireEnum
    public static AbstractGameAction.AttackEffect HERMIT_GUN0;

    @SpireEnum
    public static AbstractGameAction.AttackEffect HERMIT_GUN;

    @SpireEnum
    public static AbstractGameAction.AttackEffect HERMIT_GUN2;

    @SpireEnum
    public static AbstractGameAction.AttackEffect HERMIT_GUN3;

    @SpireEnum
    public static AbstractGameAction.AttackEffect HERMIT_GHOSTFIRE;
    @SpireEnum
    public static AbstractGameAction.AttackEffect CUIH_GJ;
}
