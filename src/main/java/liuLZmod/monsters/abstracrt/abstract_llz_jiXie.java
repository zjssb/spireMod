package liuLZmod.monsters.abstracrt;

import basemod.abstracts.CustomMonster;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 机械的虚类
 */
public abstract class abstract_llz_jixie extends CustomMonster {


    /**
     * 是否被销毁
     */
    public boolean isSuicide = false;

    /**
     * 攻击目标
     */
    public AbstractCreature target = null;


    /**
     * 充能
     */
    public static int energy = 0;
    /**
     * 触发能量
     */
    public static int maxEnergy = 6;

    public abstract_llz_jixie(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY);
    }

    public abstract_llz_jixie(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY, boolean ignoreBlights) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, ignoreBlights);
    }

    public abstract_llz_jixie(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl);
    }

    /**
     * 返回一个随机的敌方目标
     */
    public AbstractMonster getTarget() {
        if (AbstractDungeon.getMonsters() == null) {
            return null;
        } else {
            List<AbstractMonster> monsters = new ArrayList();
            Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();

            while (var2.hasNext()) {
                AbstractMonster m = (AbstractMonster) var2.next();
                if (!m.escaped && !m.isDying && !m.isDead && m.currentHealth > 0 && !(m instanceof abstract_llz_jixie)) {
                    monsters.add(m);
                }
            }

            if (monsters.size() == 0) {
                return null;
            } else {
                int rnd = AbstractDungeon.monsterRng.random(0, monsters.size() - 1);
                return (AbstractMonster) monsters.get(rnd);
            }
        }
    }

    public void update() {
        super.update();
    }

    /**
     * 能量增加
     */
    public void addEnergy(int num) {
        energy += num;
        if (energy >= maxEnergy) {
            action();
        }
    }

    /**
     * 机械行动函数
     * 条件:充能达到触发条件 或 主动触发
     */
    public void action() {

    }
}



