package liuLZmod.monster;

import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.action.saowDamageAction;
import liuLZmod.modcore.liuLZMod;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.JiXieGroupPatch;
import liuLZmod.util.Point;
import liuLZmod.vfx.SuEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 机械：哨卫
 */
public class llz_shaoW extends abstract_llz_jiXie {
    public static final String ID = "llz_shaoW";

    private static final MonsterStrings jiXieStrings;

    public static final String NAME;
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
    public static shaoWT T = null;
    /**
     * 充能条相对于角色位置
     */
    public static Point TTarget = new Point(240, 70);
    /**
     * 攻击伤害
     */
    private static final int baseAttackDmg = 4;
    private static int attackDmg = baseAttackDmg;
    /**
     * 哨卫最大数量
     */
    public static int shaoweiAmount = 2;

    /**
     * 哨卫存活列表
     */
    public static List<llz_shaoW> shaoweiList = new ArrayList<>();

    /**
     * 实例在存活列表中的位置
     */
    public int index;

    public boolean isDeath = false;

//    public static boolean isFirst = true;

    /**
     * 哨卫的位置
     * 相对于角色
     */
    public final static List<Point> positions = new ArrayList<Point>() {{
        add(new Point(240, 70));
        add(new Point(-185, 260));
        add(new Point(190, 280));
        add(new Point(-200, 30));
        add(new Point(180, 100));
        add(new Point(-50, 320));
        add(new Point(-210, 190));
        add(new Point(-150, 120));
        add(new Point(50, 250));
        add(new Point(-100, 280));
    }};


    public llz_shaoW(float x, float y) {
        super(NAME, ID, 10, -8.0F, 10.0F, 0.01F, 0.01F, null, x, y);
        // 设置图片
        //this.img = new Texture(Gdx.files.internal("ModliuLZ/img/monsters/shaowei.png"));
        this.loadAnimation("ModliuLZ/img/jix/shaow/skeleton.atlas", "ModliuLZ/img/jix/shaow/skeleton37.json", 0.8F);

        AnimationState.TrackEntry e;
        e = this.state.setAnimation(0, "sc", false);
        this.state.addAnimation(0, "idle", true, 0.0F);
        e.setTimeScale(0.8F);

        this.stateData.setMix("gj", "idle", 0.3f);
        this.stateData.setMix("idle", "gj", 0.2f);

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("llz_jih")){
            attackDmg = baseAttackDmg +(AbstractDungeon.player.getPower("llz_jih")).amount;
        }
        this.damage.add(new DamageInfo(this, attackDmg));

        this.setMove("", (byte) 0, Intent.NONE);

    }

    @Override
    public void update() {
        super.update();
        if (T != null) {
            SuEffect.play(T.drawX, T.drawY - 40, attackDmg, 1, false);
        }
    }

    /**
     * 充能
     */
    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        llz_shaoW.energy = energy;

        // 设置充能条动画
        if (T == null) {
            return;
        }
        String L = 'l' + String.valueOf(llz_shaoW.energy);
        T.state.setAnimation(0, L, false);
    }


    /**
     * 行动回合
     */
    @Override
    public void takeTurn() {
    }

    /**
     * 获取行动
     */
    @Override
    protected void getMove(int i) {
    }

    /**
     * 召唤哨卫
     */
    public static void SpawnMinion() {
        int ans = (int) shaoweiList.stream().filter(sw -> !sw.isDeath).count();
        int i =shaoweiAmount;

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if(c.hasTag(liuLZMod.SAOWEI) && i <10)
                i++;
        }
        // 哨卫数量等于最大数量
        if (ans >= i) {
            return;
        }

        MonsterGroup minions = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);

        // 哨卫数量为零，召唤哨卫生成充能条
        if (ans == 0 && T == null) {
            T = new shaoWT();
            T.drawX = AbstractDungeon.player.drawX + TTarget.x;
            T.drawY = AbstractDungeon.player.drawY + TTarget.y;
            T.init();
            minions.add(T);
        }

        // 实例化并设置位置
        llz_shaoW sw = new llz_shaoW(0f, 0f);
        Point point = positions.get(ans);
        sw.drawX = AbstractDungeon.player.drawX + point.x;
        sw.drawY = AbstractDungeon.player.drawY + point.y;
        shaoweiList.add(sw);
        sw.index = shaoweiList.indexOf(sw);
        // 初始化
        sw.init();
        //sw.showHealthBar();
        sw.usePreBattleAction();

        minions.add(sw);
    }

    /**
     * 能量增加
     */
    public static void addEnergy(int num) {
        int ans = 0;
        for (llz_shaoW m : shaoweiList) {
            if (!m.isDeath) {
                ans++;
            }
        }
        if (ans == 0) {

            return;
        }
        if (isFirst) {
            isFirst = false;
            return;
        }

        int energy = getEnergy() + num;
        setEnergy(getEnergy() + num);
        if (energy >= maxEnergy) {
            act();
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

    @Override
    public void lossEnergy(int num) {
        int energy = getEnergy()-num;
        setEnergy(Math.max(0,energy));
    }

    /**
     * 机械行动方法。
     * num: 哨卫的个数（攻击次数）。
     */
    public static void act() {
        List<llz_shaoW> monsters = shaoweiList.stream().filter(sw -> !sw.isDeath).collect(Collectors.toList());
        int i = 0;
        if (monsters.size() == 0) {
            return;
        }
        for (llz_shaoW sw : monsters) {
            AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(sw, "gj"));
            //AbstractDungeon.actionManager.addToBottom(new WaitAction(1F));
            AbstractDungeon.actionManager.addToBottom(new saowDamageAction(AbstractDungeon.player, attackDmg));
            //AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F * i));
            i++;
        }
    }

    /**
     * 移除一个哨卫。
     */
    public static void remove() {
        List<llz_shaoW> monsters = shaoweiList.stream().filter(sw -> !sw.isDeath).collect(Collectors.toList());
        int ans = monsters.size();
        if (ans == 0) {
            return;
        }
        llz_shaoW m = monsters.get(ans - 1);


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
        attackDmg = baseAttackDmg;
        T = null;
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

    static {
        jiXieStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = jiXieStrings.NAME;
        abstract_llz_jiXie.addJiXie(new llz_shaoW(0, 0));
    }

    /**
     * 根据集火修改伤害，在集火层数变动时调用
     */
    public static void aDmg() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("llz_jih")) {
            attackDmg = baseAttackDmg + (AbstractDungeon.player.getPower("llz_jih")).amount;
        }
    }
}



class shaoWT extends abstract_llz_jiXie {
    public static final String ID = "llz_shaoWT";

    private static final MonsterStrings jiXieStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);

    static String NAME = jiXieStrings.NAME;

    public shaoWT() {
        super(NAME, ID, 10, -8.0F, 10.0F, 20F, 20F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/shaowt/skeleton.atlas", "ModliuLZ/img/jix/shaowt/skeleton37.json", 1F);
        this.state.addAnimation(0, "l0", true, 0.0F);
    }

    public void remove() {
        MonsterGroup minions = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
        minions.monsters.remove(this);
    }

    @Override
    public void takeTurn() {

    }

    @Override
    protected void getMove(int i) {

    }

    @Override
    public void lossEnergy(int num) {

    }
}
