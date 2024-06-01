package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YinqhmAction extends AbstractGameAction {
    private final AbstractPlayer player;
    private boolean gz;
    private int magicNumber;

    public YinqhmAction(AbstractPlayer player, int magicNumber ,boolean gz) {
        this.player = player;
        this.magicNumber =magicNumber;
        this.gz = gz;
    }
    public void update() {
        if(!gz && !(AbstractDungeon.player.hand.size() >= 10 || (AbstractDungeon.player.discardPile.isEmpty() && AbstractDungeon.player.drawPile.isEmpty()))){
            magicNumber--;
            if(magicNumber >0){
                addToTop(new YinqhmAction(player,magicNumber,gz));
            }
            addToTop(new DrawCardAction(player, 1));
        }
        else  if (magicNumber >0) {
            magicNumber--;
            if(magicNumber >0){
                addToTop(new YinqhmAction(player,magicNumber, true));
            }
            addToTop(new benyAction());
        }
        isDone = true;
    }
}
