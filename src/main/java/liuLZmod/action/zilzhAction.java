package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import liuLZmod.monster.*;

import static liuLZmod.monster.llz_shaoW.shaoweiList;

public class zilzhAction extends AbstractGameAction {
    private final int t;

    public zilzhAction(int t) {
        this.t = t;
    }

    @Override
    public void update() {
        int i = shaoweiList.size();
        if (llz_dianD.DD != null) i++;
        if (llz_zhengQJ.ZQJ != null) i++;
        if (llz_xuYing.XY != null) i++;
        if (llz_yuQ.YQ != null) i++;
        if (llz_ZZWZ.ZZWZ != null) i++;
        if (i >= t) addToBot(new GainEnergyAction(1));
        this.isDone = true;
    }
}
