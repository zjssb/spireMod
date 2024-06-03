package liuLZmod.monsters;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.monsters.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.testPatch;
import liuLZmod.powers.dianDaoPower;
import liuLZmod.vfx.SuEffect;
import liuLZmod.vfx.morEffect;

/**
 * llz_diand:机械-电刀
 */
public class llz_dianD extends abstract_llz_jiXie {

    public final static String NAME = "电刀";

    /**
     * 充能
     */
    private static int energy = 0;

    public static llz_dianD DD = null;

    public llz_dianD() {
        super(NAME, "llz_diand", 10, -8.0F, 10.0F, 200F, 200F, (String) null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/diand/skeleton.atlas", "ModliuLZ/img/jix/diand/skeleton37.json", 1F);
        this.state.setAnimation(0, "new", false);
        this.state.addAnimation(0, "idle", true, 0F);

    }

    @Override
    public void update() {
        super.update();
        SuEffect.play(DD.drawX, DD.drawY,energy,1,true);
    }

    /**
     * 召唤电刀
     */
    public static void SpawnMinion() {
        if (DD == null) {
            DD = new llz_dianD();
            DD.drawX = AbstractDungeon.player.drawX - 50;
            DD.drawY = AbstractDungeon.player.drawY;
            DD.init();
            MonsterGroup monsters = testPatch.f_minions.get(AbstractDungeon.player);
            monsters.monsters.add(DD);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(DD, AbstractDungeon.player, new dianDaoPower(DD)));
            isFirst = true;
        }
    }

    /**
     * 移除充能
     */
    public static void clearEnergy() {
        energy = 0;
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        llz_dianD.energy = energy;
    }

    public static void addEnergy(int num) {
        if (isFirst) {
            isFirst = false;
            return;
        }
        if (DD == null) {
            return;
        }
        int energy = getEnergy() + num;
        setEnergy(Math.max(0,energy));
    }

    public static void clear() {
        setEnergy(0);
        DD = null;
    }

    @Override
    public void takeTurn() {
    }

    @Override
    protected void getMove(int i) {

    }

    static {
        abstract_llz_jiXie.addJiXie(new llz_dianD());
    }
}
