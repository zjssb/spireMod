package liuLZmod.monsters;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
import liuLZmod.monsters.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.JiXieGroupPatch;
import liuLZmod.util.Point;

/**
 * llz_zhengQJ:
 */
public class llz_zhengQJ extends abstract_llz_jiXie {

    public final static String NAME = "zhengQiJi";

    /**
     * 充能
     */
    private static int energy = 0;

    private static final int maxEnergy = 5;
    public static llz_zhengQJ ZQJ = null;
    public static Point position = new Point(100, 80);
    public static boolean isFirst = true;

    public llz_zhengQJ() {
        super(NAME, "llz_zhengQiJi", 10, -8.0F, 10.0F, 200F, 200F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/zengqj/skeleton.atlas", "ModliuLZ/img/jix/zengqj/skeleton37.json", 1F);
        this.addToBot(new ChangeStateAction(this, "new"));
        this.setMove("", (byte) 0, Intent.NONE);
    }

    /**
     * 召唤蒸汽机
     */
    public static void SpawnMinion() {
        if (ZQJ == null) {
            ZQJ = new llz_zhengQJ();
            ZQJ.drawX = AbstractDungeon.player.drawX + position.x;
            ZQJ.drawY = AbstractDungeon.player.drawY + position.y;
            ZQJ.init();
            MonsterGroup monsters = JiXieGroupPatch.f_minions.get(AbstractDungeon.player);
            monsters.monsters.add(ZQJ);
            ZQJ.addToBot(new ChangeStateAction(ZQJ, "new"));
            isFirst = true;
        }
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        llz_zhengQJ.energy = energy;
        String l = "l" + getEnergy();
        ZQJ.addToBot(new ChangeStateAction(ZQJ, l));
    }

    public static void addEnergy(int num) {
        if(isFirst){
            isFirst= false;
            return;
        }
        if (ZQJ == null) {
            return;
        }
        int energy = getEnergy() + num;
        if (energy >= maxEnergy) {
            act();
            energy = 0;
            setEnergy(energy);
            return;
        }
        setEnergy(Math.max(0, energy));
    }

    @Override
    public void lossEnergy(int num) {
        int energy = getEnergy()-num;
        setEnergy(Math.max(0,energy));
    }

    public static void act() {
        if (ZQJ == null) {
            return;
        }
        ZQJ.addToBot(new SFXAction("INTIMIDATE"));
        ZQJ.addToBot(new VFXAction(ZQJ, new IntimidateEffect(ZQJ.hb.cX, ZQJ.hb.cY), 1.0F));
        ZQJ.addToBot(new ChangeStateAction(ZQJ, "att"));
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, ZQJ, new VulnerablePower(m, 1, false)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, ZQJ, new WeakPower(m, 1, false)));
    }

    public static void clear() {
        setEnergy(0);
        ZQJ = null;
    }

    @Override
    public void changeState(String stateName) {
        float time;
        switch (stateName) {
            case "new":
                time = this.state.setAnimation(0, "new", false).getTime();
                addToBot(new WaitAction(time));
                break;
            case "l0":
                this.state.setAnimation(0, "l0", true);
                break;
            case "l1":
                this.state.setAnimation(0, "l1", true);
                break;
            case "l2":
                this.state.setAnimation(0, "l2", true);
                break;
            case "l3":
                this.state.setAnimation(0, "l3", true);
                break;
            case "l4":
                this.state.setAnimation(0, "l4", true);
                break;
            case "att":
                time = this.state.setAnimation(0, "att", false).getTime();
                addToBot(new WaitAction(time));
                break;
        }
    }

    static {
        abstract_llz_jiXie.addJiXie(new llz_zhengQJ());
    }

    @Override
    public void takeTurn() {

    }

    @Override
    protected void getMove(int i) {

    }
}
