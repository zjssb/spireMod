package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;

import java.util.Objects;

import static liuLZmod.monster.abstracrt.abstract_llz_jiXie.jiXie_list;
import static liuLZmod.monster.llz_shaoW.shaoweiList;

public class zilzhAction extends AbstractGameAction {
    private final int t;

    public zilzhAction(int t){
        this.t =t;
    }
    @Override
    public void update() {
        int i =(int) shaoweiList.stream().filter(sw -> !sw.isDeath).count();
        for (abstract_llz_jiXie jiXie : jiXie_list){
            if(!Objects.equals(jiXie.id, "llz_shaoW") && !Objects.equals(jiXie.id, "llz_shaoWT")) {
                i++;
            }
        }
        if(i >= t)addToBot(new GainEnergyAction(1));
        this.isDone =true;
    }
}
