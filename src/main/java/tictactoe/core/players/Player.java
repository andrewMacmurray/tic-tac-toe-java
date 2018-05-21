package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.Mediator;

public abstract class Player {

    PlayerSymbol playerSymbol;

    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }

    public abstract void requestMove(Board board);

}
