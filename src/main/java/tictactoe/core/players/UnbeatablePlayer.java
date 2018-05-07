package tictactoe.core.players;

import tictactoe.core.Board;

public class UnbeatablePlayer implements Player {

    private PlayerSymbol playerSymbol;

    public UnbeatablePlayer(PlayerSymbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    @Override
    public int chooseNextMove(Board board) {
        return new MiniMax(playerSymbol).execute(board);
    }

    @Override
    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }
}
