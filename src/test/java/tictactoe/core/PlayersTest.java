package tictactoe.core;

import org.junit.Test;
import tictactoe.core.players.*;
import tictactoe.mocks.MockTime;
import tictactoe.mocks.MockUI;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class PlayersTest {

    @Test
    public void currentPlayerSymbol() {
        Players players = setupPlayers();

        assertEquals(
                "current player should be X",
                PlayerSymbol.X,
                players.currentPlayerSymbol()
        );

    }

    @Test
    public void nextPlayerSymbol() {
        Players players = setupPlayers();
        players.switchPlayers();

        assertEquals(
                "current player should be O after switching",
                PlayerSymbol.O,
                players.currentPlayerSymbol()
        );
    }

    @Test
    public void chooseHumanMove() {
        Players players = setupPlayers();
        Board board = new Board(3);

        assertEquals(
                "human player should choose the first move",
                1,
                players.chooseNextMove(board)
        );
    }

    @Test
    public void chooseComputerMove() {
        Players players = setupPlayers();
        Board board = almostFullBoard();

        players.switchPlayers();

        int computerChoice = players.chooseNextMove(board);
        assertTrue(
                "computer should make one of two moves",
                computerChoice == 8 || computerChoice == 9
        );

    }

    private Players setupPlayers() {
        Player human = new HumanPlayer(PlayerSymbol.X, new MockUI(1));
        Player computer = new ComputerPlayer(PlayerSymbol.O, new MockTime());
        return new Players(human, computer);
    }

    private Board almostFullBoard() {
        Board board = new Board(3);
        for (int i = 1; i <= 7; i++) {
            board = board.makeMove(i, PlayerSymbol.X);
        }
        return board;
    }
}
