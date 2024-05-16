package liuLZmod.monsters;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import liuLZmod.monsters.abstracrt.abstract_llz_jixie;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * 哨卫
 */
public class llz_shaowei extends abstract_llz_jixie {
    public static final String NAME = "哨卫";
    private int attackDmg = 5;
    private int attackBase = 5;
    /**
     * 哨卫数量
     */
    public static int shaoweiAmount = 9;
    /**
     * 充能
     */
    public static int energy;
    /**
     * 哨卫存活列表
     */
    public static boolean[] shaoweiAlive = new boolean[shaoweiAmount];
    /**
     * 实例在存活列表中的位置
     */
    public int index;

    public static llz_shaowei ll;

    public llz_shaowei(float x,float y) {
        super(NAME, "llz_shaowei", 10, -8.0F, 10.0F, 200.0F, 200.0F, (String)null, x, y);
        // 设置图片
        this.img =new Texture(Gdx.files.internal("ModliuLZ/img/monsters/shaowei.png"));
        this.damage.add(new DamageInfo(this, this.attackDmg));
        this.setMove("攻击", (byte)0, Intent.ATTACK, this.attackDmg);
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
        this.setMove("攻击",(byte)0, Intent.ATTACK,attackDmg);
    }

}
