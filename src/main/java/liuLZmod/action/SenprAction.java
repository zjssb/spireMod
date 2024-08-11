package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import liuLZmod.monster.llz_dianD;

public class SenprAction extends AbstractGameAction {

    @Override
    public void update() {
        triggerEndOfTurnEffects();

        GameActionManager.turn++;

        triggerStartOfTurnEffects();

        this.isDone = true;
    }

    private void triggerEndOfTurnEffects() {
        // 触发能力回合结束效果
        for (AbstractPower power : AbstractDungeon.player.powers) {
            power.atEndOfTurnPreEndTurnCards(true);
        }
        for (AbstractPower power : AbstractDungeon.player.powers) {
            power.atEndOfTurn(true);
        }
        for (AbstractPower power : AbstractDungeon.player.powers) {
            power.atEndOfRound();
        }


        // 触发遗物的回合结束效果
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            relic.onPlayerEndTurn();
        }

        //卡牌
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            card.triggerOnEndOfTurnForPlayingCard();
            card.triggerOnEndOfPlayerTurn();
        }

        // 触发充能球的回合结束效果
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            orb.onEndOfTurn();
        }
    }

    private void triggerStartOfTurnEffects() {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            power.atStartOfTurn();
        }
        for (AbstractPower power : AbstractDungeon.player.powers) {
            power.atStartOfTurnPostDraw();
        }

        //遗物
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            relic.atTurnStart();
        }
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            relic.atTurnStartPostDraw();
        }

        //卡牌
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            card.atTurnStart();
        }

        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            card.atTurnStart();
        }

        for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
            card.atTurnStart();
        }

        // 触发所有充能球的回合开始效果
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            orb.onStartOfTurn();
        }

        //电刀
        llz_dianD.act();
    }
}
