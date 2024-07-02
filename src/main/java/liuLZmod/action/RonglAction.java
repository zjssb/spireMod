package liuLZmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

/**
 * 熔炼行动
 * 此行动会让玩家选择一张攻击牌来消耗，并基于其基础伤害值获得格挡。
 */
public class RonglAction extends AbstractGameAction {
    // 获取本地化字符串
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;

    // 玩家实例
    private final AbstractPlayer p;

    // 存储不符合条件的卡牌
    private final ArrayList<AbstractCard> cannotExhaust = new ArrayList<>();

    // 构造方法，初始化行动类型和持续时间
    public RonglAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    // 更新方法，处理行动逻辑
    @Override
    public void update() {
        // 当行动开始时（持续时间为快速行动的默认值）
        if (this.duration == Settings.ACTION_DUR_FAST) {
            // 遍历手牌，将所有非攻击牌添加到cannotExhaust列表中
            for (AbstractCard c : this.p.hand.group) {
                if (c.type != AbstractCard.CardType.ATTACK) {
                    this.cannotExhaust.add(c);
                }
            }

            // 如果手牌中没有可消耗的攻击牌，结束行动
            if (this.cannotExhaust.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            // 从手牌中移除所有不符合条件的卡牌
            this.p.hand.group.removeAll(this.cannotExhaust);

            // 如果手牌中有超过一张符合条件的卡牌，打开选择界面
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                tickDuration();
                return;
            }

            // 如果手牌中只有一张符合条件的卡牌，直接执行消耗和增加格挡的动作
            if (this.p.hand.group.size() == 1) {
                AbstractCard card = this.p.hand.getTopCard();
                addToBot(new ExhaustSpecificCardAction(card, p.hand));
                addToBot(new GainBlockAction(p, p, card.baseDamage));
                returnCards();
                this.isDone = true;
                return;
            }
        }

        // 处理玩家选择的卡牌
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToExhaustPile(c);
                addToBot(new GainBlockAction(p, p, c.baseDamage));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            returnCards();
            this.isDone = true;
        }

        tickDuration();
    }

    // 将不符合条件的卡牌返回到手牌
    private void returnCards() {
        for (AbstractCard c : cannotExhaust) {
            p.hand.addToTop(c);
        }
        p.hand.refreshHandLayout();
    }
}
