package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.Mediator;

public class HumanPlayer extends Player {

    public HumanPlayer(PlayerSymbol playerSymbol, Mediator mediator) {
        this.playerSymbol = playerSymbol;
        this.mediator = mediator;
    }

    @Override
    public void requestMove(Board board) {
        mediator.requestMoveFromUI(board, playerSymbol);
    }

}
