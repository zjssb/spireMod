package liuLZmod.monster.abstracrt;

import basemod.abstracts.CustomMonster;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.patches.JiXieGroupPatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 机械的虚类
 */
public abstract class abstract_llz_jiXie extends CustomMonster {
    /**
     * 是否被销毁
     */
    public boolean isSuicide = false;

    /**
     * 攻击目标
     */
    public AbstractCreature target = null;
    /**
     * 是否拥有机械标志
     */
    public static boolean isHasJiXie = false;
    /**
     * 判断生成第一个机械的行动，生效一次
     */
    public static boolean isFirst = false;
    /**
     * 充能
     */
    public static int energy = 0;
    /**
     * 触发能量
     */
    public static int maxEnergy = 1;

    /**
     * 每个派生的机械类，都需要向其注册
     */
    public static List<abstract_llz_jiXie> jiXie_list = new ArrayList<>();

    public abstract_llz_jiXie(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY);
    }

    public abstract_llz_jiXie(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY, boolean ignoreBlights) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, ignoreBlights);
    }

    public abstract_llz_jiXie(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl);
    }

    /**
     * 返回攻击目标
     */
    public AbstractMonster getTarget() {
        return null;
    }

    public void update() {
        super.update();
    }

    /**
     * 机械注册函数
     */
    public static void addJiXie(abstract_llz_jiXie jiXie) {
        if (!isHasJiXie) {
            isHasJiXie = true;
        }
        jiXie_list.add(jiXie);
    }

    /**
     * 继承类必须重写这个方法。
     * （派生类）增加充能，并检测充能是否触发条件。
     * （基类） 调用注册类的 addEnergy 方法。
     * num：充能层数
     */
    public static void addEnergy(int num) {
        Class<?> cls;
        Method method;
        for (abstract_llz_jiXie jiXie : jiXie_list) {
            cls = jiXie.getClass();
            try {
                method = cls.getMethod("addEnergy", int.class);
                method.invoke(jiXie, num);
            } catch (NoSuchMethodException e) {
                String name = cls.getName();
                System.out.println("充能方法不存在，检测 " + name + " 是否重写充能方法");
            } catch (InvocationTargetException e) {
                String name = cls.getName();
                System.out.println("充能方法反射异常，检测 " + name + " 充能方法");
            } catch (IllegalAccessException e) {
                String name = cls.getName();
                System.out.println(name + " 充能访问级别出错");
            }
        }
    }

    /**
     * 损失充能
     */
    public abstract void lossEnergy(int num);


    /**
     * 所有机械损失充能
     */
    public static void lossAllEnergy(int num) {
        Class<?> cls;
        Method method;
        for (abstract_llz_jiXie jiXie : jiXie_list) {
            cls = jiXie.getClass();
            try {
                method = cls.getMethod("lossEnergy", int.class);
                method.invoke(jiXie, num);
            } catch (NoSuchMethodException e) {
                String name = cls.getName();
                System.out.println("损失充能方法不存在，检测 " + name + " 是否重写损失充能方法");
            } catch (InvocationTargetException e) {
                String name = cls.getName();
                System.out.println("损失充能方法反射异常，检测 " + name + " 损失充能方法");
            } catch (IllegalAccessException e) {
                String name = cls.getName();
                System.out.println(name + " 损失充能访问级别出错");
            }
        }
    }

    /**
     * 行动动画方法
     */
    public static void actAnimation() {

    }

    /**
     * 派生类必须重写的重置机械列表方法
     */
    public static void clear() {
    }

    /**
     * 清空机械列表
     */
    public static void clearJiXie() {
        // 清空渲染机械列表
        MonsterGroup minions = new MonsterGroup(new AbstractMonster[0]);
        minions.monsters.removeIf(Objects::isNull);
        JiXieGroupPatch.llz_jiXie.set(AbstractDungeon.player, minions);

        // 通过反射，调用注册的过的所有名为 clear 的方法
        Class<?> cls;
        Method method;
        for (abstract_llz_jiXie jiXie : jiXie_list) {
            cls = jiXie.getClass();
            try {
                method = cls.getMethod("clear");
                method.invoke(null);
            } catch (NoSuchMethodException e) {
                String name = cls.getName();
                System.out.println("close方法不存在，检测 " + name + " 是否重写close方法");
            } catch (InvocationTargetException e) {
                String name = cls.getName();
                System.out.println("close方法反射异常，检测 " + name + " close方法");
            } catch (IllegalAccessException e) {
                String name = cls.getName();
                System.out.println(name + " close访问级别出错");
            }
        }


    }

}



