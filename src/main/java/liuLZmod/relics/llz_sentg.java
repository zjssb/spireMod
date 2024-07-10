package liuLZmod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import liuLZmod.action.gaizAction;
import liuLZmod.vfx.FineTuningEffect;

/**
 * 生态缸
 */
public class llz_sentg extends CustomRelic {
    public static final String ID = "llz_sentg";
    private static final String IMG_PATH = "ModliuLZ/img/relics/sentg.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.SOLID;

    public llz_sentg() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }


    @Override
    public void onCardDraw(final AbstractCard drawnCard) {
        if(drawnCard.type == AbstractCard.CardType.STATUS){
            AbstractDungeon.effectList.add(new FineTuningEffect(drawnCard));
            addToBot(new gaizAction(AbstractDungeon.player,drawnCard,"hand",1));
        }
    }

    public void obtain() {
        if (AbstractDungeon.player.hasRelic("llz_zallc")) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals("llz_zallc")) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        }
    }

    public boolean canSpawn() {
             return AbstractDungeon.player.hasRelic("llz_zallc");
    }
    // 获取遗物描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new llz_sentg();
    }

}
