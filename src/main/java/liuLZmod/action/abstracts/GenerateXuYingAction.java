package liuLZmod.action.abstracts;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import liuLZmod.monster.llz_xuYing;

import java.lang.reflect.Field;

public class GenerateXuYingAction extends AbstractGameAction {
    private AbstractMonster monster;

    public GenerateXuYingAction(AbstractMonster monster) {
        this.monster = monster;
    }

    @Override
    public void update() {

        int attackDmg =0;
        int attackCount =0;

        if (monster.intent == Intent.ATTACK ||
                monster.intent == Intent.ATTACK_BUFF ||
                monster.intent == Intent.ATTACK_DEBUFF ||
                monster.intent == Intent.ATTACK_DEFEND) {
            attackDmg = monster.getIntentDmg();
            attackCount = 1;

            try {
                // Accessing isMultiDmg field
                Field isMultiDmgField = AbstractMonster.class.getDeclaredField("isMultiDmg");
                isMultiDmgField.setAccessible(true);
                boolean isMultiDmg = isMultiDmgField.getBoolean(monster);

                // Accessing intentMultiAmt field
                Field intentMultiAmtField = AbstractMonster.class.getDeclaredField("intentMultiAmt");
                intentMultiAmtField.setAccessible(true);
                int intentMultiAmt = intentMultiAmtField.getInt(monster);

                if (isMultiDmg) {
                    attackCount = intentMultiAmt;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        llz_xuYing.SpawnMinion(monster.intent, attackDmg, attackCount);
        this.isDone = true;
    }
}
