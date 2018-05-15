package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.Mediator;

public class HumanPlayer implements Player {

    private final PlayerSymbol playerSymbol;
    private final Mediator mediator;

    public HumanPlayer(PlayerSymbol playerSymbol, Mediator mediator) {
        this.playerSymbol = playerSymbol;
        this.mediator = mediator;
    }

    @Override
    public void requestMove(Board board) {
        mediator.requestMove(board, playerSymbol);
    }

    @Override
    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }

}
