package liuLZmod.patches.Card;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 卡牌执行动作补丁，有戒备时不消耗状态牌
 */
@SpirePatch(clz = UseCardAction.class, method = "update")
public class UseCardActionPatch {
    public static SpireReturn<Void> Prefix(UseCardAction __instance) {
        AbstractCard targetCard = (AbstractCard) ReflectionHacks.getPrivate(__instance, UseCardAction.class, "targetCard");

        if (targetCard.type == AbstractCard.CardType.STATUS) {
            AbstractPlayer player = AbstractDungeon.player;
            for (AbstractPower power : player.powers) {
                if (power.ID.equals("llz_jieb")) {
                    targetCard.exhaustOnUseOnce = false;
                    targetCard.dontTriggerOnUseCard = false;

                    // 检查卡牌是否已经在弃牌堆中
                    if (!player.discardPile.contains(targetCard)) {
                        // 将卡牌移到弃牌堆
                        player.hand.moveToDiscardPile(targetCard);

                        // 触发弃牌事件
                        targetCard.triggerOnManualDiscard();

                        player.onCardDrawOrDiscard();
                    }

                    // 设置动作完成标志
                    __instance.isDone = true;

                    // 跳过原方法逻辑
                    return SpireReturn.Return(null);
                }
            }
        }
        return SpireReturn.Continue();
    }
}
