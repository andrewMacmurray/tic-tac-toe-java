package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.util.ThreadControl;

public class RandomPlayer extends Computer implements Player {

    private final PlayerSymbol playerSymbol;

    public RandomPlayer(PlayerSymbol playerSymbol, ThreadControl time) {
        super(time);
        this.playerSymbol = playerSymbol;
    }

    @Override
    public int chooseNextMove(Board board) {
        super.waitOneSecond();
        return super.randomMove(board.allAvailableMoves());
    }

    @Override
    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }

}
