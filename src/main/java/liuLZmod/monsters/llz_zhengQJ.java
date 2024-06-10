package liuLZmod.monsters;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
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

    public static Point position = new Point(100, 80);
    private static final int maxEnergy = 5;
    public static llz_zhengQJ ZQJ = null;

    public llz_zhengQJ() {
        super(NAME, "llz_diand", 10, -8.0F, 10.0F, 200F, 200F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/zengqj/skeleton.atlas", "ModliuLZ/img/jix/zengqj/skeleton37.json", 1F);
        this.state.setAnimation(0, "new", false);
        this.state.addAnimation(0, "l0", true, 0F);

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
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(DD, AbstractDungeon.player, new dianDaoPower(AbstractDungeon.player)));
//            isFirst = true;
        }
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        llz_zhengQJ.energy = energy;
        String l = "l" + getEnergy();
        ZQJ.state.setAnimation(0, l, true);
    }

    public static void addEnergy(int num) {
        if (ZQJ == null) {
            return;
        }
        int energy = getEnergy() + num;
        if (energy >= maxEnergy) {
            energy = 0;
            setEnergy(energy);
            act();
            return;
        }
        setEnergy(Math.max(0, energy));
    }

    public static void act() {
        ZQJ.state.setAnimation(0, "att", false);
        ZQJ.state.addAnimation(0, "l0", true,0F);
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, ZQJ, new VulnerablePower(m, 1, false)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, ZQJ, new WeakPower(m, 1, false)));
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
