package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.ui.UIRequest;

public class HumanPlayer implements Player {

    private final PlayerSymbol playerSymbol;
    private final UIRequest ui;

    public HumanPlayer(PlayerSymbol playerSymbol, UIRequest ui) {
        this.playerSymbol = playerSymbol;
        this.ui = ui;
    }

    @Override
    public Integer chooseNextMove(Board board) {
        return ui.requestMove(board, playerSymbol);
    }

    @Override
    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }

}
