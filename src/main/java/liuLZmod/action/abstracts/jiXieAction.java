package liuLZmod.action.abstracts;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.monster.*;

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
            for (abstract_llz_jiXie jiXie : abstract_llz_jiXie.jiXie_list) {
                if (jiXie.id.equals(groupType)) {
                    if (num > 0) {
                        jiXie.addEnergy(num);
                    } else if (num < 0) {
                        jiXie.lossEnergy(-num);
                    }
                    break;
                }
            }
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
