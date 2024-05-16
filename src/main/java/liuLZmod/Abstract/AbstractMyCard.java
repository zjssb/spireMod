package liuLZmod.Abstract;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.VictoryRoom;

/**
 * 自定义变量（未实现
 */
public abstract class AbstractMyCard extends CustomCard {
    public int SecondMagicNumber;
    public int BaseSecondMagicNumber;
    public static boolean upgradedSecondMagicNumber;
    public static boolean isSecondMagicNumberModified;

    public AbstractMyCard(String id, String name, String img, int cost, String rawDescription,
                          CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.SecondMagicNumber = 0;
        this.BaseSecondMagicNumber = 0;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedSecondMagicNumber) {
            this.SecondMagicNumber = this.BaseSecondMagicNumber;
            this.isSecondMagicNumberModified = true;
        }
    }

    public void upgradeSecondMagicNumber(int amount) {
        this.BaseSecondMagicNumber += amount;
        this.SecondMagicNumber = this.BaseSecondMagicNumber;
        this.upgradedSecondMagicNumber = true;
    }

    @Override
    public void update() {
        super.update();
    }

}
