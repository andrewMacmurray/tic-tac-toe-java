package tictactoe.core.players;

import tictactoe.core.Board;

public interface Player {

    Integer chooseNextMove(Board board);

    PlayerSymbol getSymbol();

}
