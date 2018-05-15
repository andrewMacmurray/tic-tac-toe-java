package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.util.Time;

public class UnbeatablePlayer extends Computer implements Player {

    private final PlayerSymbol playerSymbol;
    private final Mediator mediator;

    public UnbeatablePlayer(PlayerSymbol playerSymbol, Time time, Mediator mediator) {
        super(time);
        this.playerSymbol = playerSymbol;
        this.mediator = mediator;
    }

    @Override
    public void requestMove(Board board) {
        super.waitOneSecond();
        mediator.receiveMove(strategicMove(board));
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
