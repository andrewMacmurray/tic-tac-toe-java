package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.UI;

public class HumanPlayer implements Player {

    private final PlayerSymbol playerSymbol;
    private final UI ui;

    public HumanPlayer(PlayerSymbol playerSymbol, UI ui) {
        this.playerSymbol = playerSymbol;
        this.ui = ui;
    }

    @Override
    public int chooseNextMove(Board board) {
        return ui.requestMove(board, playerSymbol);
    }

    @Override
    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }

}
