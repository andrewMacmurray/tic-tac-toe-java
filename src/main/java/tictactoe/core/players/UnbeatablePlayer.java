package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.util.Time;

import java.util.ArrayList;
import java.util.Random;

public class UnbeatablePlayer extends Player {

    private Time time;

    public UnbeatablePlayer(PlayerSymbol playerSymbol, Time time, Mediator mediator) {
        this.time = time;
        this.playerSymbol = playerSymbol;
        this.mediator = mediator;
    }

    @Override
    public void requestMove(Board board) {
        waitOneSecond();
        mediator.receiveMove(strategicMove(board));
    }

    private int strategicMove(Board board) {
        if (board.allAvailableMoves().size() > 12) {
            return randomMove(board.allAvailableMoves());
        } else {
            return new MiniMax(playerSymbol).execute(board);
        }
    }

    private void waitOneSecond() {
        time.pause(1000);
    }

    private int randomMove(ArrayList<Integer> moves) {
        Random random = new Random();
        int i = random.nextInt(moves.size());
        return moves.get(i);
    }

}
