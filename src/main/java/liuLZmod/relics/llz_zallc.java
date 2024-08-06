package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

/**
 * 杂乱鸟巢
 */
public class llz_zallc extends CustomRelic {
    public static final String ID = "llz_zallc";
    private static final String IMG_PATH = "ModliuLZ/img/relics/zallc.png";
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public llz_zallc() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractCard randomCard = generateRandomCard();
        if (randomCard != null) {
            this.flash();
            addToBot(new MakeTempCardInHandAction(randomCard, 1, false));
        }
    }

    // 获取遗物描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_zallc();
    }

    private AbstractCard generateRandomCard() {
        ArrayList<AbstractCard> allStatusCards = new ArrayList<>();
        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (card.type == AbstractCard.CardType.STATUS && card.cardID.startsWith("llz") && !card.cardID.equals("llz_jinj")) {
                allStatusCards.add(card);
            }
        }

        if (allStatusCards.isEmpty()) {
            return null;
        }

        ArrayList<AbstractCard> choices = new ArrayList<>();
        while (choices.size() < 1 && !allStatusCards.isEmpty()) {
            int index = AbstractDungeon.cardRandomRng.random(allStatusCards.size() - 1);
            AbstractCard card = allStatusCards.remove(index).makeCopy();
            choices.add(card);
        }

        return choices.get(0);
    }
}
