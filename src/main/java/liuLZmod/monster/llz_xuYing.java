package liuLZmod.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.combat.BuffParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.StunStarEffect;
import com.megacrit.cardcrawl.vfx.combat.UnknownParticleEffect;
import liuLZmod.action.abstracts.removeJiXieAction;
import liuLZmod.monster.abstracrt.abstract_llz_jiXie;
import liuLZmod.patches.JiXieGroupPatch;
import liuLZmod.powers.llz_jih;
import liuLZmod.util.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * 机械：虚影
 */
public class llz_xuYing extends abstract_llz_jiXie {
    public static final String ID = "llz_xuYing";
    private static final MonsterStrings jiXieStrings;
    public final static String NAME;
    private static int energy = 0;
    public static int maxEnergy = 1;
    public static llz_xuYing XY = null;
    public static int count = 1;
    public static int attackDmg = 0;
    public static Point position = new Point(250, 200);
    /**
     * 意图渲染
     */
    private ArrayList<AbstractGameEffect> intentVfx = new ArrayList<>();
    private static Texture intentImg = ImageMaster.INTENT_UNKNOWN_L;
    public static Intent intent = Intent.UNKNOWN;
    private float intentParticleTimer = 0.0F;
    private BobEffect bobEffect = new BobEffect();
    private Color intentColor = Color.WHITE.cpy();
    private float intentAngle = 0.0F;
    private static final Random random = new Random();

    public llz_xuYing() {
        super(NAME, ID, 10, 0.0F, 10.0F, 20F, 50F, null, 0, 0);
        this.loadAnimation("ModliuLZ/img/jix/xvy/skeleton.atlas", "ModliuLZ/img/jix/xvy/skeleton37.json", 1F);
        this.state.setAnimation(0, "new", false);
        this.state.addAnimation(0, "idle", true, 0F);
        this.setMove((byte) 0, Intent.ATTACK);
    }

    // 枚举类型和两个int参数构造函数
    public llz_xuYing(Intent intent, int attackDmg, int count) {
        this();
        setIntent(intent, attackDmg, count);
    }

    // 设置意图和攻击参数函数
    public static void setIntent(Intent intent, int attackDmg, int count) {
        llz_xuYing.intent = intent;
        llz_xuYing.attackDmg = attackDmg;
        llz_xuYing.count = count;
        if(count > 1){
            llz_xuYing.intentImg = getIntentImg(intent, attackDmg * count);
        }else llz_xuYing.intentImg = getIntentImg(intent, attackDmg);
    }

    /**
     *处理意图
     */
    private static Texture getIntentImg(Intent intent, int dmg) {
        switch (intent) {
            case ATTACK:
            case ATTACK_BUFF:
            case ATTACK_DEBUFF:
            case ATTACK_DEFEND:
                return NewgetAttackIntent(dmg);
            case BUFF:
                return ImageMaster.INTENT_BUFF_L;
            case DEBUFF:
                return ImageMaster.INTENT_DEBUFF_L;
            case STRONG_DEBUFF:
                return ImageMaster.INTENT_DEBUFF2_L;
            case DEFEND:
            case DEFEND_DEBUFF:
                return ImageMaster.INTENT_DEFEND_L;
            case DEFEND_BUFF:
                return ImageMaster.INTENT_DEFEND_BUFF_L;
            case ESCAPE:
                return ImageMaster.INTENT_ESCAPE_L;
            case MAGIC:
                return ImageMaster.INTENT_MAGIC_L;
            case SLEEP:
                return ImageMaster.INTENT_SLEEP_L;
            case STUN:
                return null;
            case UNKNOWN:
            default:
                return ImageMaster.INTENT_UNKNOWN_L;
        }
    }

    private static Texture NewgetAttackIntent(int dmg) {
        if (dmg < 5)
            return ImageMaster.INTENT_ATK_1;
        if (dmg < 10)
            return ImageMaster.INTENT_ATK_2;
        if (dmg < 15)
            return ImageMaster.INTENT_ATK_3;
        if (dmg < 20)
            return ImageMaster.INTENT_ATK_4;
        if (dmg < 25)
            return ImageMaster.INTENT_ATK_5;
        if (dmg < 30)
            return ImageMaster.INTENT_ATK_6;
        return ImageMaster.INTENT_ATK_7;
    }

    @Override
    public void update() {
        super.update();
        if (XY != null) {
            XYIntentVFX();
        }
        for (AbstractGameEffect effect : this.intentVfx) {
            effect.update();
        }
    }

    /**
     * 粒子特效
     */
    private void XYIntentVFX() {
            if (this.intent == Intent.ATTACK_DEBUFF || this.intent == Intent.DEBUFF || this.intent == Intent.STRONG_DEBUFF || this.intent == Intent.DEFEND_DEBUFF) {
                this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
                if (this.intentParticleTimer < 0.0F) {
                    this.intentParticleTimer = 1.0F;
                    this.intentVfx.add(new DebuffParticleEffect(this.intentHb.cX, this.intentHb.cY));
                }
            } else if (this.intent == Intent.ATTACK_BUFF || this.intent == Intent.BUFF || this.intent == Intent.DEFEND_BUFF) {
                this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
                if (this.intentParticleTimer < 0.0F) {
                    this.intentParticleTimer = 0.1F;
                    this.intentVfx.add(new BuffParticleEffect(this.intentHb.cX, this.intentHb.cY));
                }
            } else if (this.intent == Intent.ATTACK_DEFEND) {
                this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
                if (this.intentParticleTimer < 0.0F) {
                    this.intentParticleTimer = 0.5F;
                    this.intentVfx.add(new ShieldParticleEffect(this.intentHb.cX, this.intentHb.cY));
                }
            } else if (this.intent == Intent.UNKNOWN) {
                this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
                if (this.intentParticleTimer < 0.0F) {
                    this.intentParticleTimer = 0.5F;
                    this.intentVfx.add(new UnknownParticleEffect(this.intentHb.cX, this.intentHb.cY));
                }
            } else if (this.intent == Intent.STUN) {
                this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
                if (this.intentParticleTimer < 0.0F) {
                    this.intentParticleTimer = 0.67F;
                    this.intentVfx.add(new StunStarEffect(this.intentHb.cX, this.intentHb.cY));
                }
            }
    }


    /**
     *渲染意图
     */
    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        renderIntentIcon(sb);
        renderCustomDamageRange(sb);
        for (AbstractGameEffect effect : this.intentVfx) {
            effect.render(sb);
        }
    }

    private void renderIntentIcon(SpriteBatch sb) {
        if (this.intentImg == null) {
            return; // 如果 intentImg 为空，直接返回，不渲染
        }

        if (this.intent == Intent.DEBUFF || this.intent == Intent.STRONG_DEBUFF) {
            this.intentAngle += Gdx.graphics.getDeltaTime() * 150.0F;  // 控制旋转速度
        } else {
            this.intentAngle = 0.0F;
        }

        sb.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        sb.draw(this.intentImg, this.intentHb.cX - 64.0F, this.intentHb.cY - 64.0F + this.bobEffect.y,
                64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, this.intentAngle, 0, 0, 128, 128, false, false);
    }

    private void renderCustomDamageRange(SpriteBatch sb) {
        if (this.intent == Intent.ATTACK || this.intent == Intent.ATTACK_BUFF || this.intent == Intent.ATTACK_DEFEND || this.intent == Intent.ATTACK_DEBUFF) {
            String damageText = count > 1 ? attackDmg + "x" + count : Integer.toString(attackDmg);
            FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, damageText,
                    this.intentHb.cX - 30.0F * Settings.scale, this.intentHb.cY + this.bobEffect.y - 12.0F * Settings.scale, this.intentColor);
        }
    }

    public static void SpawnMinion(Intent intent, int attackDmg, int count) {
        if (XY == null) {
            XY = new llz_xuYing(intent,attackDmg,count);
            XY.drawX = AbstractDungeon.player.drawX + position.x;
            XY.drawY = AbstractDungeon.player.drawY + position.y;
            XY.init();
            XY.rollMove();
            MonsterGroup monsters = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            monsters.monsters.add(XY);
        }else setIntent(intent, attackDmg, count);
    }

    public static void addEnergy(int num) {
        if (XY == null) {
            return;
        }
        if (isFirst) {
            isFirst = false;
            return;
        }


        if (num >0) {
            act();
        }
    }

    public static void act() {
        if (XY == null) {
            return;
        }
        if(llz_xuYing.intent == Intent.ESCAPE) {
            llz_xuYing.XYescape();
            return;
        }
        XY.state.setAnimation(0, "att", false);
        XY.state.addAnimation(0, "idle", true, 0F);

        AbstractPlayer p = AbstractDungeon.player;
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if(m == null || m.currentHealth == 0) {
            return;
        }

        if (llz_xuYing.intent == Intent.ATTACK_DEFEND || llz_xuYing.intent == Intent.DEFEND || llz_xuYing.intent == Intent.DEFEND_BUFF || llz_xuYing.intent == Intent.DEFEND_DEBUFF) {
            int i = 5;
            if(llz_xuYing.intent == Intent.DEFEND)i = 10;
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, i));
        }
        if (llz_xuYing.intent == Intent.ATTACK || llz_xuYing.intent == Intent.ATTACK_BUFF || llz_xuYing.intent == Intent.ATTACK_DEFEND || llz_xuYing.intent == Intent.ATTACK_DEBUFF){
            for (int i =0;i <llz_xuYing.count; i++){
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(XY, attackDmg, DamageInfo.DamageType.THORNS), getRandomAttackEffect()));
            }
        }
        if(llz_xuYing.intent == Intent.ATTACK_DEBUFF || llz_xuYing.intent == Intent.DEBUFF || llz_xuYing.intent == Intent.DEFEND_DEBUFF){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, XY, new WeakPower(m, 1, false)));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, XY, new VulnerablePower(m, 1, false)));
        }
        if (llz_xuYing.intent == Intent.ATTACK_BUFF || llz_xuYing.intent == Intent.BUFF || llz_xuYing.intent == Intent.DEFEND_BUFF) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, XY, new StrengthPower(p, 1),1));
            if(llz_xuYing.intent == Intent.BUFF)AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, XY, new llz_jih(p, 1),1));
        }
        if(llz_xuYing.intent == Intent.STRONG_DEBUFF){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, XY, new PoisonPower(m,XY, 5),5));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, XY, new ConstrictedPower(m,XY, 5),5));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, XY, new StrengthPower(m, -1),-1));
        }
        AbstractDungeon.actionManager.addToBottom(new removeJiXieAction());
    }

    /**
     *
     * 随机的攻击图效
     */
    public static AbstractGameAction.AttackEffect getRandomAttackEffect() {
        AbstractGameAction.AttackEffect[] effects = new AbstractGameAction.AttackEffect[] {
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,
                AbstractGameAction.AttackEffect.SLASH_VERTICAL,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.BLUNT_LIGHT,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.SLASH_HEAVY
        };

        return effects[random.nextInt(effects.length)];
    }

    private static void XYescape() {
        // 加入烟雾效果
        AbstractDungeon.effectList.add(new SmokePuffEffect(XY.hb.cX, XY.hb.cY));

        // 使虚影向左移动
        XY.hb.move(XY.hb.cX - 200 * Settings.scale, XY.hb.cY);
        XY.intentHb.move(XY.hb.cX, XY.hb.cY);
        XY.drawX = XY.hb.cX - XY.hb.width / 2.0F;
        XY.drawY = XY.hb.cY - XY.hb.height / 2.0F;

        // 检查虚影是否完全移出屏幕，如果移出则删除
        if (XY.hb.cX < -XY.hb.width) {
            llz_xuYing.remove();
        }
    }

    public static void remove() {
        if(XY != null){
            MonsterGroup monsters = JiXieGroupPatch.llz_jiXie.get(AbstractDungeon.player);
            monsters.monsters.remove(XY);
            XY = null;
        }
    }
    public static void clear() {
        XY = null;
    }

    @Override
    public void lossEnergy(int num) {}

    @Override
    public void takeTurn() {}

    @Override
    protected void getMove(int i) {
        this.setMove((byte) 0, Intent.BUFF);
    }

    static {
        jiXieStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = jiXieStrings.NAME;
        abstract_llz_jiXie.addJiXie(new llz_xuYing());
    }
}
