package tictactoe.core.players;

import tictactoe.core.Board;

public interface Player {

    int chooseNextMove(Board board);

    PlayerSymbol getSymbol();

}
