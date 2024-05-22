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
public class testPatch {
    private static Integer maxMinions = Integer.MAX_VALUE;
    private static MonsterGroup minions = new MonsterGroup(new AbstractMonster[0]);
    public static SpireField<Integer> f_maxMinions = new SpireField(() -> {
        return maxMinions;
    });
    public static SpireField<MonsterGroup> f_minions = new SpireField(() -> {
        return minions;
    });

    public testPatch() {
    }
}
