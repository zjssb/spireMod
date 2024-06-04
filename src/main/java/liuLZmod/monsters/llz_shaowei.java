package liuLZmod.monsters;

import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.action.shaoweiAction;
import liuLZmod.monsters.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.testPatch;
import liuLZmod.util.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 哨卫
 */
public class llz_shaowei extends abstract_llz_jiXie {
    public static final String NAME = "哨卫";
    /**
     * 充能
     */
    private static int energy = 0;
    /**
     * 触发能量
     */
    public static int maxEnergy = 6;
    /**
     * 哨卫充能条
     */
    public static shaoweiT T = null;
    /**
     * 充能条相对于角色位置
     */
    public static Point TTarget = new Point(150, 0);
    /**
     * 攻击伤害
     */
    private static final int attackDmg = 1;
    /**
     * 哨卫最大数量
     */
    public static int shaoweiAmount = 10;

    /**
     * 哨卫存活列表
     */
    public static List<llz_shaowei> shaoweiList = new ArrayList<>();

    /**
     * 实例在存活列表中的位置
     */
    public int index;

    public boolean isDeath = false;

    /**
     * 哨卫的位置
     * 相对于角色
     */
    public final static List<Point> positions = new ArrayList<Point>() {{
        add(new Point(240, 70));
        add(new Point(-185, 260));
        add(new Point(190, 280));
        add(new Point(-200, 30));
        add(new Point(190, 130));
        add(new Point(-50, 320));
        add(new Point(-210, 190));
        add(new Point(-150, 120));
        add(new Point(50, 250));
        add(new Point(-100, 280));
    }};


    public llz_shaowei(float x, float y) {
        super(NAME, "llz_shaowei", 10, -8.0F, 10.0F, 200F, 200F, (String) null, x, y);
        // 设置图片
        //this.img = new Texture(Gdx.files.internal("ModliuLZ/img/monsters/shaowei.png"));
        this.loadAnimation("ModliuLZ/img/jix/shaow/skeleton.atlas", "ModliuLZ/img/jix/shaow/skeleton37.json", 0.8F);

        AnimationState.TrackEntry e;
        e = this.state.setAnimation(0, "sc", false);
        this.state.addAnimation(0, "idle", true, 0.0F);
        e.setTimeScale(0.8F);

        this.stateData.setMix("gj", "idle", 0.3f);
        this.stateData.setMix("idle", "gj", 0.2f);


        this.damage.add(new DamageInfo(this, attackDmg));

        this.setMove("测试", (byte) 4, Intent.NONE);

    }

    /**
     * 充能
     */
    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        llz_shaowei.energy = energy;

        // 设置充能条动画
        if (T == null) {
            return;
        }
        String L = 'l' + String.valueOf(llz_shaowei.energy);
        T.state.setAnimation(0, L, false);
        System.out.println(L);
    }


    /**
     * 行动回合
     */
    @Override
    public void takeTurn() {
    }

    public void update() {
        super.update();
    }

    @Override
    public void die() {
        super.die();

    }

    @Override
    public void die(boolean triggerRelics) {
        super.die(false);

    }

    /**
     * 使用开局动作
     */
    @Override
    public void usePreBattleAction() {

    }

    /**
     * 获取行动
     */
    @Override
    protected void getMove(int i) {
//        this.setMove("测试", (byte) 0, Intent.DEBUG);
    }

    /**
     * 召唤哨卫
     */
    public static void SpawnMinion() {
        int ans = (int) shaoweiList.stream().filter(sw -> !sw.isDeath).count();
        // 哨卫数量等于最大数量
        if (ans == shaoweiAmount) {
            return;
        }

        MonsterGroup minions = (MonsterGroup) testPatch.f_minions.get(AbstractDungeon.player);

        // 哨卫数量为零，召唤哨卫生成充能条
        if (ans == 0 && T == null) {
            T = new shaoweiT();
            T.drawX = AbstractDungeon.player.drawX + TTarget.x;
            T.drawY = AbstractDungeon.player.drawY + TTarget.y;
            T.init();
            minions.add(T);
            isFirst = true;
        }

        // 实例化并设置位置
        llz_shaowei sw = new llz_shaowei(0f, 0f);
        Point point = positions.get(ans);
        sw.drawX = AbstractDungeon.player.drawX + point.x;
        sw.drawY = AbstractDungeon.player.drawY + point.y;
        shaoweiList.add(sw);
        sw.index = shaoweiList.indexOf(sw);
        // 初始化
        sw.init();
//        sw.showHealthBar();
        sw.usePreBattleAction();

        minions.add(sw);
    }

    /**
     * 能量增加
     */
    public static void addEnergy(int num) {
        if (isFirst) {
            isFirst = false;
            return;
        }
        int ans = 0;
        for (llz_shaowei m : shaoweiList) {
            if (!m.isDeath) {
                ans++;
            }
        }
        if (ans == 0) {

            return;
        }
        int energy = getEnergy() + num;
        setEnergy(getEnergy() + num);
        if (energy >= maxEnergy) {
            act(ans);
            /*
            while (getEnergy() >= maxEnergy) {
                setEnergy(getEnergy() - maxEnergy);
                act(ans);
            }
             */
            setEnergy(0);
        } else if (energy <= 0) {
            setEnergy(0);
        }
    }

    /**
     * 机械行动方法。
     * num: 哨卫的个数（攻击次数）。
     */
    public static void act(int num) {
        actAnimation();
        AbstractDungeon.actionManager.addToBottom(new shaoweiAction(new DamageInfo(AbstractDungeon.player, attackDmg, DamageInfo.DamageType.THORNS), num));
    }

    /**
     * 移除一个哨卫。
     */
    public static void remove() {
        List<llz_shaowei> monsters = shaoweiList.stream().filter(sw -> !sw.isDeath).collect(Collectors.toList());
        int ans = monsters.size();
        if (ans == 0) {
            return;
        }
        llz_shaowei m = monsters.get(ans - 1);


        m.addToBot(new ChangeStateAction(m, "sw"));
        m.isDeath = true;
        if (ans == 1) {
            setEnergy(0);
            T.remove();
            T = null;
        }
    }

    /**
     * 重置哨卫列表
     */
    public static void clear() {
        shaoweiList.clear();
        setEnergy(0);
        T = null;
    }

    /**
     * 行动动画方法
     */
    public static void actAnimation() {
        List<llz_shaowei> monsters = shaoweiList.stream().filter(sw -> !sw.isDeath).collect(Collectors.toList());
        int i = 0;
        for (llz_shaowei m : monsters) {
            i++;
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F * i));
            AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(m, "gj"));

        }
    }

    @Override
    public void changeState(String stateName) {
        switch (stateName) {
            case "gj":
                this.state.setAnimation(0, "gj", false);
                this.state.addAnimation(0, "idle", true, 0F);
                break;
            case "sw":
                float time = this.state.setAnimation(0, "sw", false).getTime();
                AbstractDungeon.actionManager.addToBottom(new WaitAction(time));
        }
    }

    @Override
    public AbstractMonster getTarget() {
        if (AbstractDungeon.getMonsters() == null) {
            return null;
        } else {
            List<AbstractMonster> monsters = new ArrayList<>();
            Iterator<AbstractMonster> var2 = AbstractDungeon.getMonsters().monsters.iterator();

            while (var2.hasNext()) {
                AbstractMonster m = (AbstractMonster) var2.next();
                if (!m.escaped && !m.isDying && !m.isDead && m.currentHealth > 0 && !(m instanceof abstract_llz_jiXie)) {
                    monsters.add(m);
                }
            }
            for (AbstractMonster m : monsters)
                System.out.println(m);
            if (monsters.size() == 0) {
                return null;
            } else {
                int rnd = AbstractDungeon.monsterRng.random(0, monsters.size() - 1);
                return (AbstractMonster) monsters.get(rnd);
            }
        }
    }

    static {
        abstract_llz_jiXie.addJiXie(new llz_shaowei(0, 0));
    }
}

class shaoweiT extends abstract_llz_jiXie {

    static String NAME = "哨卫充能条";

    public shaoweiT() {
        super(NAME, "llz_shaoweiT", 10, -8.0F, 10.0F, 200F, 200F, (String) null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/shaowt/skeleton.atlas", "ModliuLZ/img/jix/shaowt/skeleton37.json", 1F);
        this.state.addAnimation(0, "l0", true, 0.0F);
    }

    public void remove() {
        MonsterGroup minions = (MonsterGroup) testPatch.f_minions.get(AbstractDungeon.player);
        minions.monsters.remove(this);
    }

    @Override
    public void takeTurn() {

    }

    @Override
    protected void getMove(int i) {

    }
}
