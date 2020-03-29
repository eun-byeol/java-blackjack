package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.result.BettingMoney;

public class Player extends User {
    private static final int PLAYER_DRAWABLE_MAX_SCORE = 21;
    private BettingMoney bettingMoney = BettingMoney.ZERO;

    Player(String name, Hand hand) {
        super(name, hand);
    }

    public Player(String name, BettingMoney bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    public static Player valueOf(String name, List<Card> cards) {
        Hand hand = new Hand();
        hand.add(cards);

        return new Player(name, hand);
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public void setBettingMoney(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean canDraw() {
        return hand.calculateScore().isLowerThan(PLAYER_DRAWABLE_MAX_SCORE);
    }

    @Override
    public List<Card> getInitialDealtHand() {
        return getHand();
    }
}
