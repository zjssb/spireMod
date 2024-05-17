package liuLZmod.monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.monsters.abstracrt.abstract_llz_jixie;
import liuLZmod.util.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * 哨卫
 */
public class llz_shaowei extends abstract_llz_jixie {
    public static final String NAME = "哨卫";
    /**
     * 攻击伤害
     */
    private final int attackDmg = 5;
    /**
     * 哨卫最大数量
     */
    public static int shaoweiAmount = 9;

    /**
     * 哨卫存活列表
     */
    public static List<Boolean> shaoweiList = new ArrayList<>();

    public final static List<Point> positions = new ArrayList<Point>() {{
        add(new Point(200, 0));
        add(new Point(200, 0));
        add(new Point(200, 0));
        add(new Point(200, 0));
        add(new Point(200, 0));
        add(new Point(200, 0));
        add(new Point(200, 0));
        add(new Point(200, 0));
        add(new Point(200, 0));
    }};
    ;
    /**
     * 实例在存活列表中的位置
     */
    public int index;


    public llz_shaowei(float x, float y) {
        super(NAME, "llz_shaowei", 10, -8.0F, 10.0F, 200.0F, 200.0F, (String) null, x, y);
        // 设置图片
        this.img = new Texture(Gdx.files.internal("ModliuLZ/img/monsters/shaowei.png"));
        this.damage.add(new DamageInfo(this, this.attackDmg));
        this.setMove("攻击", (byte) 0, Intent.ATTACK, this.attackDmg);
    }

    /**
     * 行动回合
     */
    @Override
    public void takeTurn() {
//        AbstractDungeon.
    }

    public void update() {
        super.update();
    }

    @Override
    public void die() {
        super.die();
    }

    /**
     * 获取行动
     */
    @Override
    protected void getMove(int i) {
        this.setMove("攻击", (byte) 0, Intent.ATTACK, attackDmg);
    }

    /**
     * 召唤哨卫
     */
    public static void SpawnMinion() {
        int ans = 0;
        // 遍历列表，计算剩余哨卫数量
        int length = shaoweiList.size();
        for (int i = 0; i < length; i++) {
            if (shaoweiList.get(i)) {
                ans++;
            }
        }
        // 哨卫数量等于最大数量
        if (ans == shaoweiAmount) {
            return;
        }
        // 初始化
        llz_shaowei sw = new llz_shaowei(0f, 0f);
        Point point = positions.get(ans + 1);
        sw.drawX = AbstractDungeon.player.drawX + point.x;
        sw.drawY = AbstractDungeon.player.drawY + point.y;
        shaoweiList.add(true);
        sw.index = shaoweiList.size();
        // 召唤
        AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(sw, true));
    }





}
