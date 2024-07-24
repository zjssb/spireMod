package liuLZmod.monster;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.JiXieGroupPatch;
import liuLZmod.util.Point;

/**
 * 机械：虚影
 */
public class llz_xuYing extends abstract_llz_jiXie {
    public static final String ID = "llz_xuYing";
    /**
     * 本地化
     */
    private static final MonsterStrings jiXieStrings;

    public final static String NAME;

    /**
     * 充能
     */
    private static int energy = 0;

    public static int maxEnergy = 1;
    public static llz_xuYing XY = null;

    public static Point position = new Point(100, 80);

    //public static boolean isFirst = true;

    public llz_xuYing() {
        super(NAME, ID, 10, 0.0F, 10.0F, 20F, 20F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/xvy/skeleton.atlas", "ModliuLZ/img/jix/xvy/skeleton37.json", 1F);
        this.state.setAnimation(0, "new", false);
        this.setMove((byte) 0, Intent.ATTACK);
    }

    public static void SpawnMinion() {
        if (XY == null) {
            XY = new llz_xuYing();
            XY.drawX = AbstractDungeon.player.drawX + position.x;
            XY.drawY = AbstractDungeon.player.drawY + position.y;
//            XY.x
            XY.init();
            XY.showHealthBar();
            XY.rollMove();
            MonsterGroup monsters = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            monsters.monsters.add(XY);
        }
    }

    public static void addEnergy(int num) {

    }

    public static void clear() {
        XY = null;
    }

    @Override
    public void lossEnergy(int num) {

    }

    @Override
    public void takeTurn() {
        if(XY.nextMove == 0){
            addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player,10));
        }
    }

    @Override
    protected void getMove(int i) {
        this.setMove((byte)0, Intent.BUFF);
    }

    static {
        jiXieStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = jiXieStrings.NAME;

        abstract_llz_jiXie.addJiXie(new llz_xuYing());
    }
}
