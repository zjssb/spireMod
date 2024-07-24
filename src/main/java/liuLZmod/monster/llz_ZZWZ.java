package liuLZmod.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.JiXieGroupPatch;
import liuLZmod.powers.llz_jih;
import liuLZmod.util.Point;
import liuLZmod.vfx.SuEffect;

/**
 * 机械：战争
 */
public class llz_ZZWZ extends abstract_llz_jiXie {

    public final static String ID = "llz_ZZWZ";

    private static final MonsterStrings jiXieStrings;
    public final static String NAME;

    private static int energy = 0;
    private static final int initialMaxEnergy = 10;
    private static int maxEnergy = initialMaxEnergy;

    public static llz_ZZWZ ZZWZ = null;
    public static Point position = new Point(150, 100);

    public static int count = 5;
    private static final int baseAttackDmg = 5;
    public static int attackDmg = baseAttackDmg;

    private static boolean isSecondPhase = false;

    public llz_ZZWZ() {
        super(NAME, ID, 10, -8.0F, 10.0F, 20F, 20F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/zanz/skeleton.atlas", "ModliuLZ/img/jix/zanz/skeleton37.json", 1F);
        this.state.setAnimation(0, "new", false);
        this.stateData.setMix("att", "l0", 1F);

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("llz_jih")){
            attackDmg = baseAttackDmg + (AbstractDungeon.player.getPower("llz_jih")).amount;
        }
        this.setMove("", (byte) 0, Intent.NONE);
    }

    @Override
    public void update() {
        super.update();
        if (ZZWZ != null && isSecondPhase) {
            SuEffect.play(ZZWZ.drawX, ZZWZ.drawY - 50, attackDmg, count, false);
        }else if(ZZWZ != null) SuEffect.play(ZZWZ.drawX, ZZWZ.drawY - 50, energy, 1, true);
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        llz_ZZWZ.energy = energy;
        if (ZZWZ != null) {
            String l = "l" + getEnergy();
            AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(ZZWZ, l));
        }
    }

    public static void SpawnMinion() {
        if (ZZWZ == null) {
            ZZWZ = new llz_ZZWZ();
            ZZWZ.drawX = AbstractDungeon.player.drawX + position.x;
            ZZWZ.drawY = AbstractDungeon.player.drawY + position.y;
            ZZWZ.init();
            MonsterGroup monsters = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            monsters.monsters.add(ZZWZ);
            ZZWZ.addToBot(new ChangeStateAction(ZZWZ, "new"));
        }else if(isSecondPhase){
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p ,p ,new llz_jih(p,1),1));
        }
    }

    public static void addEnergy(int num) {
        if (ZZWZ == null) {
            return;
        }
        if (isFirst) {
            isFirst = false;
            return;
        }

        if(num >0 && isSecondPhase){
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 2));
        }

        int energy = getEnergy() + num;
        if (energy >= maxEnergy) {
            if (!isSecondPhase) {
                enterSecondPhase();
                energy = 0;
                maxEnergy = 5;
            } else {
                act();
                energy = 0;
            }
            setEnergy(energy);
            return;
        }
        setEnergy(Math.max(0, energy));
    }

    private static void enterSecondPhase() {
        isSecondPhase = true;
        ZZWZ.state.setAnimation(0, "new_2", false);
    }

    @Override
    public void lossEnergy(int num) {
        int energy = getEnergy() - num;
        setEnergy(Math.max(0, energy));
    }

    public static void act() {
        ZZWZ.addToBot(new ChangeStateAction(ZZWZ, "att"));
        for (int i = 1; i <= count; i++) {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(attackDmg, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
        }
    }

    public static void clear() {
        setEnergy(0);
        ZZWZ = null;
        maxEnergy = initialMaxEnergy;
        isSecondPhase = false;
    }

    @Override
    public void changeState(String stateName) {
        if(!isSecondPhase)this.state.setAnimation(1, "idle", true);
        else switch (stateName) {
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
        abstract_llz_jiXie.addJiXie(new llz_ZZWZ());
    }

    public static void aDmg() {
        attackDmg = baseAttackDmg + (AbstractDungeon.player.getPower("llz_jih")).amount;
    }

    @Override
    public void takeTurn() {
    }

    @Override
    protected void getMove(int i) {
    }
}
