package liuLZmod.monster;

import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.action.YuQDamageAction;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.JiXieGroupPatch;
import liuLZmod.util.Point;
import liuLZmod.vfx.SuEffect;

/**
 * 机械：鱼群
 */
public class llz_yuQ extends abstract_llz_jiXie {

    public final static String ID = "llz_yuQ";

    private static final MonsterStrings jiXieStrings;
    public final static String NAME;
    public static String description = "";
    public static final String[] DIALOG = CardCrawlGame.languagePack.getMonsterStrings(ID).DIALOG;


    /**
     * 充能
     */
    private static int energy = 0;

    private static final int maxEnergy = 3;
    public static llz_yuQ YQ = null;
    public static Point position = new Point(120, 50);
    /**
     * 攻击次数
     */
    public static int count = 3;

    private static final int baseAttackDmg = 3;
    public static int attackDmg = baseAttackDmg;
    public static boolean isFirst = false;

    public llz_yuQ() {
        super(NAME, ID, 10, -8.0F, 10.0F, 80F, 50F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/yv/skeleton.atlas", "ModliuLZ/img/jix/yv/skeleton37.json", 1F);
        this.state.setAnimation(0, "new", false);
        this.stateData.setMix("att", "l0", 1F);

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("llz_jih")){
            attackDmg = baseAttackDmg +(AbstractDungeon.player.getPower("llz_jih")).amount;
        }
        count = 3;
        this.setMove("", (byte) 0, Intent.NONE);
    }

    @Override
    public void update() {
        super.update();
        if (YQ != null) {
            if (this.hb.hovered) {
                TipHelper.renderGenericTip(YQ.drawX, YQ.drawY, this.name, description);
            }
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
            updateDescription();
        } else {
            count += 3;
            updateDescription();
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
        if (YQ == null) {
            return;
        }
        YQ.addToBot(new ChangeStateAction(YQ, "att"));

        boolean m = false;
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (monster.currentHealth > 0) {
                m = true;
                break;
            }
        }
        if(m){
            for (int i = 1; i <= count; i++) {
                AbstractDungeon.actionManager.addToBottom(new YuQDamageAction(YQ, attackDmg));
            }
            if (count > 1) count--;
            updateDescription();
        }
    }


    public static void remove() {
        if(YQ != null){
            MonsterGroup monsters = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            monsters.monsters.remove(YQ);
            setEnergy(0);
            YQ = null;
        }
    }

    public static void clear() {
        setEnergy(0);
        attackDmg = baseAttackDmg;
        count = 3;
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

    /**
     * 根据集火修改伤害，在集火层数变动时调用
     */
    public static void aDmg() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("llz_jih")) {
            attackDmg = baseAttackDmg + (AbstractDungeon.player.getPower("llz_jih")).amount;
            updateDescription();
        }
    }

    /**
     * 刷新描述
     */
    public static void updateDescription(){
        llz_yuQ.description = llz_yuQ.DIALOG[0] + attackDmg + llz_yuQ.DIALOG[1] + count + llz_yuQ.DIALOG[2];
    }


    @Override
    public void takeTurn() {

    }

    @Override
    protected void getMove(int i) {

    }
}
