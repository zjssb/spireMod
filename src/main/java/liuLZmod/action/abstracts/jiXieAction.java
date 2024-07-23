package liuLZmod.action.abstracts;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.monster.*;

import java.util.Objects;

public class jiXieAction extends AbstractGameAction {
    private String groupType;
    private int num;
    private boolean hasGroupType;
    private boolean hasNum;

    public jiXieAction(String groupType) {
        this.groupType = groupType;
        this.hasGroupType = true;
    }

    public jiXieAction(int num) {
        this.num = num;
        this.hasNum = true;
    }

    public jiXieAction(String groupType, int num) {
        this.groupType = groupType;
        this.num = num;
        this.hasGroupType = true;
        this.hasNum = true;
    }

    @Override
    public void update() {
        if (hasGroupType && hasNum) {
            if(Objects.equals(groupType, "llz_dianD"))
                llz_dianD.addEnergy(num);
        } else if (hasGroupType) {
            switch (groupType) {
                case "llz_shaoW":
                    llz_shaoW.SpawnMinion();
                    break;
                case "llz_dianD":
                    llz_dianD.SpawnMinion();
                    break;
                case "llz_zhengQJ":
                    llz_zhengQJ.SpawnMinion();
                    break;
                case "llz_yuQ":
                    llz_yuQ.SpawnMinion();
                    break;
                case "llz_xuYing":
                    llz_xuYing.SpawnMinion();
                    break;
                case "llz_ZZWZ":
                    llz_ZZWZ.SpawnMinion();
                    break;
                default:
                    break;
            }
        } else if (hasNum) {
            if (num > 0) {
                abstract_llz_jiXie.addEnergy(num);
            } else if (num < 0) {
                abstract_llz_jiXie.lossAllEnergy(-num);
            }
        }
        this.isDone = true;
    }
}
