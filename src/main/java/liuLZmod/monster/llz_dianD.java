package liuLZmod.monster;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.cards.llz_dianHQG;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.JiXieGroupPatch;
import liuLZmod.util.Point;
import liuLZmod.vfx.SuEffect;

/**
 * 机械：电刀
 */
public class llz_dianD extends abstract_llz_jiXie {

    public static final String ID = "llz_dianD";
    private static final MonsterStrings jiXieStrings;

    public final static String NAME;

    /**
     * 充能
     */
    private static int energy = 0;

    public static int maxEnergy = 1;
    public static llz_dianD DD = null;

    public static Point position = new Point(-60, 80);

    public static boolean isFirst = true;

    public llz_dianD() {
        super(NAME, ID, 10, -8.0F, 10.0F, 20F, 20F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/diand/skeleton.atlas", "ModliuLZ/img/jix/diand/skeleton37.json", 1F);
        this.state.setAnimation(0, "new", false);
        this.state.addAnimation(0, "idle", true, 0F);
        this.setMove("", (byte) 0, Intent.NONE);
    }

    @Override
    public void update() {
        super.update();
        if (DD != null) {
            SuEffect.play(DD.drawX, DD.drawY - 30, energy, 1, true);
        }
    }

    /**
     * 召唤电刀
     */
    public static void SpawnMinion() {
        if (DD == null) {
            DD = new llz_dianD();
            DD.drawX = AbstractDungeon.player.drawX + position.x;
            DD.drawY = AbstractDungeon.player.drawY + position.y;
            DD.init();
            MonsterGroup monsters = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            monsters.monsters.add(DD);
            isFirst = true;
        }
    }

    /**
     * 移除充能
     */
    public static void lossEnergy() {
        energy = 0;
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        llz_dianD.energy = energy;
    }

    public static void addEnergy(int num) {
        if (DD == null) {
            return;
        }
        if (isFirst) {
            isFirst = false;
            return;
        }

        int energy = getEnergy() + num;
        setEnergy(Math.max(0, energy));
    }

    @Override
    public void lossEnergy(int num) {
        int energy = getEnergy() - num;
        setEnergy(Math.max(0, energy));
    }

    public static void clear() {
        setEnergy(0);
        DD = null;
    }

    public static void act() {
        if (DD == null) {
            return;
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new llz_dianHQG(llz_dianD.getEnergy() * 10)));
    }


    @Override
    public void takeTurn() {
    }

    @Override
    protected void getMove(int i) {

    }

    static {
        jiXieStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = jiXieStrings.NAME;
        abstract_llz_jiXie.addJiXie(new llz_dianD());
    }
}
