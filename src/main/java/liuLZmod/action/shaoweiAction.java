//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import liuLZmod.action.abstracts.abstract_jiXieAction;

public class shaoweiAction extends abstract_jiXieAction {
    private int magicNumber;
    private DamageInfo info;

    public shaoweiAction(DamageInfo info, int magicNumber) {
        this.magicNumber = magicNumber;
        this.info = info;
    }

    public void update() {
        super.update();

        for(int i = 1; i <= this.magicNumber; ++i) {
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            this.addToTop(new DamageAction(m, this.info,AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }

        this.isDone = true;
    }
}
