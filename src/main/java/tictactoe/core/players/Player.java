package tictactoe.core.players;

import tictactoe.core.Board;

public interface Player {

    void requestMove(Board board);

    PlayerSymbol getSymbol();

}
