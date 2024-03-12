package model.blackjackgame;

import java.util.List;
import model.dealer.Dealer;
import model.player.Players;

public class GameResult {

    private static final String NOT_EXIST_PLAYER = "존재하는 플레이어가 없습니다.";

    private final GameScore dealerScore;
    private final List<GameScore> playersScore;

    private GameResult(GameScore dealerScore, List<GameScore> playersScore) {
        this.dealerScore = dealerScore;
        this.playersScore = playersScore;
    }

    public static GameResult of(Dealer dealer, Players players) {
        GameScore dealerScore = GameScore.from(dealer);
        List<GameScore> playersScore = players.getGroup()
            .stream()
            .map(GameScore::from)
            .toList();
        return new GameResult(dealerScore, playersScore);
    }

    public ResultStatus decideResultStatus(GameScore self, GameScore opponent) {
        int selfScore = self.getScore();
        int opponentScore = opponent.getScore();

        if (self.isBurst() && opponent.isBurst() || selfScore == opponentScore) {
            return ResultStatus.PUSH;
        }
        if (self.isBurst() || (opponent.isNotBurst() && opponentScore > selfScore)) {
            return ResultStatus.LOOSE;
        }
        return ResultStatus.WIN;
    }

    public int findDealerScore() {
        return dealerScore.getScore();
    }

    public int findPlayerScore(String playerName) {
        return playersScore.stream()
            .filter(playerScore -> playerScore.getName().equals(playerName))
            .map(GameScore::getScore)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PLAYER));
    }

    public GameScore getDealerScore() {
        return dealerScore;
    }

    public List<GameScore> getPlayersScore() {
        return playersScore;
    }
}
