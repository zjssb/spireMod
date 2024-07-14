package liuLZmod.patches.Card;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 卡牌能否打出的判断补丁
 */
public class CardCanUsePatch {

    @SpirePatch(
            clz = AbstractCard.class,
            method = "canUse"
    )
    public static class CanUsePatch {

        public static SpireReturn<Boolean> SpireReturn(AbstractCard __instance, AbstractPlayer p, AbstractMonster m) {
            if(m == null){
                if (__instance.type == AbstractCard.CardType.STATUS && __instance.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
                    return SpireReturn.Return(false);
                } else if (__instance.type == AbstractCard.CardType.CURSE && __instance.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
                    return SpireReturn.Return(false);
                }else{
                    return SpireReturn.Return(true);
                }
            }else{
                if (__instance.type == AbstractCard.CardType.STATUS && __instance.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
                    return SpireReturn.Return(false);
                } else if (__instance.type == AbstractCard.CardType.CURSE && __instance.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
                    return SpireReturn.Return(false);
                } else {
                    return SpireReturn.Return(__instance.cardPlayable(m) && __instance.hasEnoughEnergy());
                }
            }
        }
    }
}
