package liuLZmod.action.abstracts;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import liuLZmod.monster.*;

import static liuLZmod.monster.llz_shaoW.shaoweiList;

/**
 * 随机移除机械
 */
public class removeJiXieAction extends AbstractGameAction {
    boolean xy =false;
    public removeJiXieAction(){
        this.xy = false;
    }
    public removeJiXieAction(boolean xy){
        this.xy =xy;
    }
    @Override
    public void update() {
        if(xy){
            llz_xuYing.remove();
        }
        else {
            int i = shaoweiList.size() + 4;
            int roll = AbstractDungeon.cardRandomRng.random(i);
            jx(roll);
        }
        isDone =true;
    }

    public void jx(int roll){
        if(llz_zhengQJ.ZQJ != null && roll == 1){
            addToTop(new VFXAction(new ExplosionSmallEffect(llz_zhengQJ.ZQJ.hb.cX, llz_zhengQJ.ZQJ.hb.cY)));
            llz_zhengQJ.remove();
            return;
        }
        if(llz_yuQ.YQ != null && roll > 0 && roll <=2){
            addToTop(new VFXAction(new ExplosionSmallEffect(llz_yuQ.YQ.hb.cX, llz_yuQ.YQ.hb.cY)));
            llz_yuQ.remove();
            return;
        }
        if(llz_dianD.DD != null && roll > 0 && roll <=3){
            addToTop(new VFXAction(new ExplosionSmallEffect(llz_dianD.DD.hb.cX, llz_dianD.DD.hb.cY)));
            llz_dianD.remove();
            return;
        }
        if(llz_ZZWZ.ZZWZ != null && roll > 0 && roll <=4){
            addToTop(new VFXAction(new ExplosionSmallEffect(llz_ZZWZ.ZZWZ.hb.cX, llz_ZZWZ.ZZWZ.hb.cY)));
            if(!llz_ZZWZ.isSecondPhase){
                llz_ZZWZ.remove();
            }
            return;
        }
        if(llz_shaoW.T != null && roll > 0){
            llz_shaoW.remove();
            return;
        }

        if(roll == 0){
            if(llz_xuYing.XY != null){
                addToTop(new VFXAction(new ExplosionSmallEffect(llz_xuYing.XY.hb.cX, llz_xuYing.XY.hb.cY)));
                llz_xuYing.remove();
            }
        }else {
            roll--;
            jx(roll);
        }
    }
}
