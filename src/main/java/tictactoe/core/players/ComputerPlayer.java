package tictactoe.core.players;

import tictactoe.core.Board;
import tictactoe.core.util.ThreadControl;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer implements Player {

    private final PlayerSymbol playerSymbol;
    private final ThreadControl time;

    public ComputerPlayer(PlayerSymbol playerSymbol, ThreadControl time) {
        this.playerSymbol = playerSymbol;
        this.time = time;
    }

    @Override
    public int chooseNextMove(Board board) {
        time.pause(1000);
        ArrayList<Integer> moves = board.allAvailableMoves();
        return randomMove(moves);
    }

    @Override
    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }

    private int randomMove(ArrayList<Integer> moves) {
        Random random = new Random();
        int i = random.nextInt(moves.size());
        return moves.get(i);
    }
}
