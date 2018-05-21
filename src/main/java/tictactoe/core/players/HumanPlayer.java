package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.Mediator;

import java.util.function.BiConsumer;

public class HumanPlayer extends Player {

    private BiConsumer<Board, PlayerSymbol> moveResponder;

    public HumanPlayer(PlayerSymbol playerSymbol, BiConsumer<Board, PlayerSymbol> moveResponder) {
        this.playerSymbol = playerSymbol;
        this.moveResponder = moveResponder;
    }

    @Override
    public void requestMove(Board board) {
        moveResponder.accept(board, playerSymbol);
    }

}
