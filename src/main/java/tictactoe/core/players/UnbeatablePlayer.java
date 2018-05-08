package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.util.ThreadControl;

public class UnbeatablePlayer extends Computer implements Player {

    private PlayerSymbol playerSymbol;

    public UnbeatablePlayer(PlayerSymbol playerSymbol, ThreadControl time) {
        super(time);
        this.playerSymbol = playerSymbol;
    }

    @Override
    public int chooseNextMove(Board board) {
        super.waitOneSecond();
        return strategicMove(board);
    }

    @Override
    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }

    private int strategicMove(Board board) {
        if (board.allAvailableMoves().size() > 12) {
            return super.randomMove(board.allAvailableMoves());
        } else {
            return new MiniMax(playerSymbol).execute(board);
        }
    }
    
}
