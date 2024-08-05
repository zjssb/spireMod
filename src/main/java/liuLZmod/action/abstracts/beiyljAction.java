package liuLZmod.action.abstracts;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.monster.llz_shaoW;
import liuLZmod.relics.llz_beiylj;

import static liuLZmod.monster.llz_shaoW.shaoweiList;

/**
 * 备用零件行动
 */
public class beiyljAction extends AbstractGameAction {
    public beiyljAction() {
    }


    @Override
    public void update() {
        int i =(int) shaoweiList.stream().filter(sw -> !sw.isDeath).count();
        if(i < 1) {
            llz_shaoW.SpawnMinion();
            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, new llz_beiylj()));
        }
        this.isDone = true;
    }
}
