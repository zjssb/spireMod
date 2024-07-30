package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.vfx.FineTuningEffect;

import java.util.ArrayList;

/**
 * 随机改造0或改造所有>0改造状态<0
 */
public class AllGaizAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;
    private int times;
    public AllGaizAction(int times) {
        this.times = times;
    }


    public void update() {
        if (true) {
            if(times ==0){
                ArrayList<AbstractCard> eligibleCards = new ArrayList<>();
                for (AbstractCard c : this.p.hand.group) {
                    if (c != null && (c.baseDamage > 0 || c.baseBlock > 0 || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.ATTACK)) {
                        eligibleCards.add(c);
                    }
                }

                if (!eligibleCards.isEmpty()) {
                    AbstractCard selectedCard = eligibleCards.get(AbstractDungeon.cardRandomRng.random(eligibleCards.size() - 1));
                    addToTop(new gaizAction(p, selectedCard, "hand", 1));
                    AbstractDungeon.effectList.add(new FineTuningEffect(selectedCard));
                }
            }else  if (times > 0) {
                for (AbstractCard c : this.p.hand.group) {
                    if (c != null && (c.baseDamage > 0 || c.baseBlock > 0 || c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.ATTACK)) {
                        addToTop(new gaizAction(p, c, "hand", 1));
                        AbstractDungeon.effectList.add(new FineTuningEffect(c));
                    }
                }
            }else if (times < 0) {
                for (AbstractCard c : this.p.hand.group) {
                    if (c.type == AbstractCard.CardType.STATUS) {
                        addToTop(new gaizAction(p, c, "hand", 1));
                        AbstractDungeon.effectList.add(new FineTuningEffect(c));
                    }
                }
            }
        }
        tickDuration();
    }
}
