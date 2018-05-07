package tictactoe.core;

import org.junit.Test;
import tictactoe.core.players.Player;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.UnbeatablePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class UnbeatablePlayerTest {

    @Test
    public void playerSymbol() {
        Player unbeatablePlayer = new UnbeatablePlayer(PlayerSymbol.O);

        assertEquals(
                "can retrieve player symbol",
                PlayerSymbol.O,
                unbeatablePlayer.getSymbol()
        );
    }

    @Test
    public void takeCenter() {
        Player unbeatablePlayer = new UnbeatablePlayer(PlayerSymbol.O);
        Board board = sequence(
                PlayerSymbol.X,
                Arrays.asList(1)
        );

        assertEquals(
                "unbeatable player should take center if first player hasn't taken it",
                5,
                unbeatablePlayer.chooseNextMove(board)
        );
    }

    @Test
    public void blockWin() {
        Player unbeatablePlayer = new UnbeatablePlayer(PlayerSymbol.O);
        Board board = sequence(
                PlayerSymbol.X,
                Arrays.asList(1, 2, 4)
        );

        assertEquals(
                "Player O blocks player X's potential winning move",
                7,
                unbeatablePlayer.chooseNextMove(board)
        );
    }

    @Test
    public void bruteForcePlay() {
        for (int i = 0; i < 100; i++) {
            Board randomGame = playRandomGame();
            assertFalse(
                    "Player X should never win",
                    randomGame.xWin()
            );
        }
    }

    private Board playRandomGame() {
        Board board = new Board(3);
        Player unbeatablePlayer  = new UnbeatablePlayer(PlayerSymbol.O);
        boolean isUnbeatableTurn = false;

        while (!board.isTerminal()) {
            if (isUnbeatableTurn) {
                int nextMove = unbeatablePlayer.chooseNextMove(board);
                board = board.makeMove(nextMove, PlayerSymbol.O);
                isUnbeatableTurn = false;
            } else {
                int nextMove = randomMove(board.allAvailableMoves());
                board = board.makeMove(nextMove, PlayerSymbol.X);
                isUnbeatableTurn = true;
            }
        }
        return board;
    }

    private int randomMove(ArrayList<Integer> moves) {
        Random random = new Random();
        int i = random.nextInt(moves.size());
        return moves.get(i);
    }

    private Board sequence(PlayerSymbol p1, List<Integer> moves) {
        Board board = new Board(3);
        PlayerSymbol currentPlayer = p1;
        for (int move : moves) {
            board = board.makeMove(move, currentPlayer);
            currentPlayer = currentPlayer.getAlternate();
        }
        return board;
    }

}
