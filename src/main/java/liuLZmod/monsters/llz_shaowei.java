package liuLZmod.monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
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


/**
 * 哨卫
 */
public class llz_shaowei extends abstract_llz_jiXie {
    public static final String NAME = "哨卫";
    /**
     * 充能
     */
    public static int energy = 0;
    /**
     * 触发能量
     */
    public static int maxEnergy = 6;
    /**
     * 攻击伤害
     */
    private static final int attackDmg = 5;
    /**
     * 哨卫最大数量
     */
    public static int shaoweiAmount = 9;

    /**
     * 哨卫存活列表
     */
    public static boolean[] shaoweiList = new boolean[shaoweiAmount];

    /**
     * 哨卫的位置
     * 相对于角色
     */
    public final static List<Point> positions = new ArrayList<Point>() {{
        add(new Point(200, 0));
        add(new Point(300, 100));
        add(new Point(400, 200));
        add(new Point(500, 300));
        add(new Point(200, 400));
        add(new Point(300, 400));
        add(new Point(400, 300));
        add(new Point(500, 200));
        add(new Point(600, 100));
    }};

    /**
     * 实例在存活列表中的位置
     */
    public int index;


    public llz_shaowei(float x, float y) {
        super(NAME, "llz_shaowei", 10, -8.0F, 10.0F, 200F, 200F, (String) null, x, y);
        // 设置图片
        this.img = new Texture(Gdx.files.internal("ModliuLZ/img/monsters/shaowei.png"));
        this.damage.add(new DamageInfo(this, this.attackDmg));

        this.setMove("测试", (byte) 4, Intent.NONE);
    }


    /**
     * 行动回合
     */
    @Override
    public void takeTurn() {}

    public void update() {
        super.update();
    }

    @Override
    public void die() {
        shaoweiList[this.index] = false;
        super.die();

    }

    @Override
    public void die(boolean triggerRelics) {
        shaoweiList[this.index] = false;
        super.die(false);

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
        int ans = 0;
        // 遍历列表，计算剩余哨卫数量
        int length = shaoweiList.length;
        for (int i = 0; i < length; i++) {
            if (shaoweiList[i]) {
                ans++;
            }
        }
        // 哨卫数量等于最大数量
        if (ans == shaoweiAmount) {
            return;
        }


        // 实例化并设置位置
        llz_shaowei sw = new llz_shaowei(0f, 0f);
        Point point = positions.get(ans);
        sw.drawX = AbstractDungeon.player.drawX + point.x;
        sw.drawY = AbstractDungeon.player.drawY + point.y;
        shaoweiList[ans] = true;
        sw.index = ans;
        // 初始化
        sw.init();
//        sw.showHealthBar();
        sw.usePreBattleAction();

        MonsterGroup minions = (MonsterGroup) testPatch.f_minions.get(AbstractDungeon.player);
        minions.add(sw);
    }

    /**
     * 能量增加
     */
    public static void addEnergy(int num) {
        energy += num;
        if (energy >= maxEnergy) {
            int ans = 0;
            int var = shaoweiList.length;
            for (int i = 0; i < var; i++) {
                if (shaoweiList[i]) {
                    ans++;
                }
            }
            while (energy >= maxEnergy) {
                energy -= maxEnergy;
                AbstractDungeon.actionManager.addToBottom(new shaoweiAction(new DamageInfo(AbstractDungeon.player,attackDmg,DamageInfo.DamageType.THORNS),ans));
//                this.addToBot();
            }
        }
    }


    public static void clear() {
        int var = shaoweiList.length;
        for (int i = 0; i < var; i++) {
            shaoweiList[i] = false;
        }
    }

    @Override
    public AbstractMonster getTarget() {
        if (AbstractDungeon.getMonsters() == null) {
            return null;
        } else {
            List<AbstractMonster> monsters = new ArrayList();
            Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();

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
