//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package liuLZmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
        method = "<class>"
)
public class JiXieGroupPatch {
    private static Integer maxJiXie = Integer.MAX_VALUE;
    private static MonsterGroup jiXie = new MonsterGroup(new AbstractMonster[0]);
    public static SpireField<Integer> llz_maxJiXie = new SpireField(() -> {
        return maxJiXie;
    });
    public static SpireField<MonsterGroup> llz_jiXie = new SpireField(() -> {
        return jiXie;
    });

    public JiXieGroupPatch() {
    }
}
