package liuLZmod.monster;

import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
import liuLZmod.action.zengQjAction;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.JiXieGroupPatch;
import liuLZmod.util.Point;
import liuLZmod.vfx.SuEffect;

/**
 * 机械：蒸汽机
 */
public class llz_zhengQJ extends abstract_llz_jiXie {

    public final static String ID = "llz_zhengQJ";

    private static final MonsterStrings jiXieStrings;
    public final static String NAME;
    public static final String[] DIALOG = CardCrawlGame.languagePack.getMonsterStrings(ID).DIALOG;
    public static String description = DIALOG[0];

    /**
     * 充能
     */
    private static int energy = 0;

    private static final int maxEnergy = 5;
    public static llz_zhengQJ ZQJ = null;
    public static Point position = new Point(-90, 200);
    public static boolean isFirst = false;

    public llz_zhengQJ() {
        super(NAME, ID, 10, -8.0F, 10.0F, 80F, 60F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/zengqj/skeleton.atlas", "ModliuLZ/img/jix/zengqj/skeleton37.json", 1F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "new", false);
        e.setTimeScale(1F);
        this.setMove("", (byte) 0, Intent.NONE);
    }

    @Override
    public void update() {
        super.update();
        if (ZQJ != null) {
            if (this.hb.hovered) {
                TipHelper.renderGenericTip(ZQJ.drawX, ZQJ.drawY, this.name, description);
            }
        }
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
            MonsterGroup monsters = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            monsters.monsters.add(ZQJ);
        } else {
            act();
            setEnergy(0);
        }
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        llz_zhengQJ.energy = energy;
        if (ZQJ != null) {
            String l = "l" + getEnergy();
            ZQJ.addToBot(new ChangeStateAction(ZQJ, l));
        }
    }

    public static void addEnergy(int num) {
        if (ZQJ == null) {
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
        if (ZQJ == null) {
            return;
        }
        ZQJ.addToBot(new SFXAction("INTIMIDATE"));
        ZQJ.addToBot(new VFXAction(ZQJ, new IntimidateEffect(ZQJ.hb.cX, ZQJ.hb.cY - 55), 0F));
        ZQJ.addToBot(new ChangeStateAction(ZQJ, "att"));
        AbstractDungeon.actionManager.addToBottom(new zengQjAction());
    }


    public static void remove() {
        if (ZQJ != null) {
            MonsterGroup monsters = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            monsters.monsters.remove(ZQJ);
            setEnergy(0);
            ZQJ = null;
        }
    }

    public static void clear() {
        setEnergy(0);
        ZQJ = null;
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
            case "l3":
                this.state.setAnimation(1, "l3", true);
                break;
            case "l4":
                this.state.setAnimation(1, "l4", true);
                break;
            case "att":
                this.state.setAnimation(0, "att", false);
                break;
        }
    }

    static {
        jiXieStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = jiXieStrings.NAME;

        abstract_llz_jiXie.addJiXie(new llz_zhengQJ());
    }

    @Override
    public void takeTurn() {

    }

    @Override
    protected void getMove(int i) {

    }
}
