package tictactoe.core.players;

import tictactoe.core.Board;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer implements Player {

    private final PlayerSymbol playerSymbol;

    public ComputerPlayer(PlayerSymbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    @Override
    public Integer chooseNextMove(Board board) {
        ArrayList<Integer> moves = board.allAvailableMoves();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        return randomMove(moves);
    }

    @Override
    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }

    private Integer randomMove(ArrayList<Integer> moves) {
        Random random = new Random();
        int i = random.nextInt(moves.size());
        return moves.get(i);
    }
}
