package tictactoe.core;

import tictactoe.core.Board;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;

public interface UI {

    Players requestPlayers();

    int requestMove(Board board, PlayerSymbol playerSymbol);

    boolean requestPlayAgain();

    int requestBoardSize();

    void showBoard(Board board);

    void showWin(PlayerSymbol playerSymbol);

    void showDraw();

    void showMoveSummary(Integer move, Board board, PlayerSymbol playerSymbol);

    void showMoveInstructions(Integer boardSize, PlayerSymbol playerSymbol);

    void greetUser();

    void goodbye();

    void showInstructions();

    void clear();

}
