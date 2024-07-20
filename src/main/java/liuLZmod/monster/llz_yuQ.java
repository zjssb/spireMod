package liuLZmod.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.action.YuQDamageAction;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.JiXieGroupPatch;
import liuLZmod.powers.llz_shengNaPower;
import liuLZmod.util.Point;
import liuLZmod.vfx.SuEffect;

/**
 * 机械：鱼群
 */
public class llz_yuQ extends abstract_llz_jiXie {

    public final static String ID = "llz_yuQ";

    private static final MonsterStrings jiXieStrings;
    ;
    public final static String NAME;


    /**
     * 充能
     */
    private static int energy = 0;

    private static final int maxEnergy = 3;
    public static llz_yuQ YQ = null;
    public static Point position = new Point(150, 100);
    /**
     * 攻击次数
     */
    public static int count = 3;

    public static int attackDmg = 3;

    public llz_yuQ() {
        super(NAME, ID, 10, -8.0F, 10.0F, 20F, 20F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/yv/skeleton.atlas", "ModliuLZ/img/jix/yv/skeleton37.json", 1F);
        this.state.setAnimation(0, "new", false);
        this.stateData.setMix("att", "l0", 1F);
        this.setMove("", (byte) 0, Intent.NONE);
    }

    @Override
    public void update() {
        super.update();
        if (YQ != null) {
            SuEffect.play(YQ.drawX, YQ.drawY - 50, attackDmg, count, false);
        }
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        llz_yuQ.energy = energy;
        if (YQ != null) {
            String l = "l" + getEnergy();
            AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(YQ, l));
        }
    }

    public static void SpawnMinion() {
        if (YQ == null) {
            YQ = new llz_yuQ();
            YQ.drawX = AbstractDungeon.player.drawX + position.x;
            YQ.drawY = AbstractDungeon.player.drawY + position.y;
            YQ.init();
            MonsterGroup monsters = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            monsters.monsters.add(YQ);
            YQ.addToBot(new ChangeStateAction(YQ, "new"));

        } else {
            count += 3;
        }
    }


    public static void addEnergy(int num) {
        if (YQ == null) {
            return;
        }
        if (isFirst) {
            isFirst = false;
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
        int energy = getEnergy() - num;
        setEnergy(Math.max(0, energy));
    }

    public static void act() {
        YQ.addToBot(new ChangeStateAction(YQ, "att"));
        for (int i = 1; i <= count; i++) {
            AbstractDungeon.actionManager.addToBottom(new YuQDamageAction(YQ, attackDmg));
        }
        if(count >1)count--;
    }


    public static void clear() {
        setEnergy(0);
        YQ = null;
    }

    @Override
    public void changeState(String stateName) {
        switch (stateName) {
            case "l0":
                this.state.setAnimation(1, "l0", true);
                break;
            case "l1":
                this.state.setAnimation(1, "l1", true);
                break;
            case "l2":
                this.state.setAnimation(1, "l2", true);
                break;
            case "att":
//                System.out.println(this.animationTimer);
                this.state.setAnimation(0, "att", false);
                break;
        }
    }

    static {
        jiXieStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = jiXieStrings.NAME;

        abstract_llz_jiXie.addJiXie(new llz_yuQ());
    }


    @Override
    public void takeTurn() {

    }

    @Override
    protected void getMove(int i) {

    }
}
