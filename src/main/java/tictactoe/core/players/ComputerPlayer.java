package tictactoe.core.players;

import tictactoe.core.Board;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer implements Player {

    private final PlayerSymbol playerSymbol;

    ComputerPlayer(PlayerSymbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    @Override
    public Integer chooseNextMove(Board board) {
        pause(1000);
        ArrayList<Integer> moves = board.allAvailableMoves();
        return randomMove(moves);
    }

    @Override
    public PlayerSymbol getSymbol() {
        return playerSymbol;
    }

    private void pause(Integer ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Integer randomMove(ArrayList<Integer> moves) {
        Random random = new Random();
        int i = random.nextInt(moves.size());
        return moves.get(i);
    }
}
