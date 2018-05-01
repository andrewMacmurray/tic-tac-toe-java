package tictactoe.core.ui;

import tictactoe.core.Board;
import tictactoe.core.players.PlayerSymbol;

public interface UIShow {

    void showBoard(Board board);

    void showWin(PlayerSymbol playerSymbol);

    void showDraw();

    void showMoveSummary(Integer move, Board board, PlayerSymbol playerSymbol);

    void showMoveInstructions(Integer boardSize, PlayerSymbol playerSymbol);

}
