package tictactoe.core;

import org.junit.Before;
import org.junit.Test;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.UnbeatablePlayer;
import tictactoe.mocks.MockMediator;
import tictactoe.mocks.MockTime;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UnbeatablePlayerTest {

    private MockMediator mockMediator;
    private UnbeatablePlayer unbeatablePlayer;

    @Before
    public void setup() {
        mockMediator = new MockMediator();
        unbeatablePlayer = new UnbeatablePlayer(PlayerSymbol.O, new MockTime(), mockMediator);
    }

    @Test
    public void playerSymbol() {
        assertEquals(
                "can retrieve player symbol",
                PlayerSymbol.O,
                unbeatablePlayer.getSymbol()
        );
    }


    @Test
    public void takeCenter() {
        Board board = sequence(
                PlayerSymbol.X,
                Arrays.asList(1)
        );

        unbeatablePlayer.requestMove(board);
        assertEquals(
                "unbeatable player should take center if first player hasn't taken it",
                5,
                mockMediator.getCurrentMove()
        );
    }

    @Test
    public void blockWin() {
        Board board = sequence(
                PlayerSymbol.X,
                Arrays.asList(1, 2, 4)
        );

        unbeatablePlayer.requestMove(board);
        assertEquals(
                "Player O blocks player X's potential winning move",
                7,
                mockMediator.getCurrentMove()
        );
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
