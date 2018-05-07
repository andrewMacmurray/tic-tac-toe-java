package tictactoe.cli;

import org.junit.Test;
import tictactoe.core.Board;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;
import tictactoe.core.UI;

import static org.junit.Assert.*;

public class ConsoleRequestTest {

    @Test
    public void requestMove() {
        IOHelper io = new IOHelper("3");
        UI console = new Console(io.in, io.print);
        Board board = new Board(3);

        assertEquals(
                "console can request a move via IO",
                3,
                console.requestMove(board, PlayerSymbol.X)
        );
    }

    @Test
    public void notifyUnrecognisedMove() {
        IOHelper io = new IOHelper("blah 3");
        UI console = new Console(io.in, io.print);
        Board board = new Board(3);

        int move = console.requestMove(board, PlayerSymbol.X);
        assertTrue(
                "console notifies user of an unrecognised move",
                io.output().contains("I didn't recognise that")
        );

        assertEquals(
                "eventually returns a valid move", 3, move
        );
    }

    @Test
    public void notifyInvalidMove() {
        IOHelper io = new IOHelper("1 2");
        UI console = new Console(io.in, io.print);
        Board board = new Board(3).makeMove(1, PlayerSymbol.X);

        int move = console.requestMove(board, PlayerSymbol.O);
        assertTrue(
                "console notifies user of an invalid move",
                io.output().contains("1 is already taken!")
        );
        assertEquals(
                "eventually returns a valid move", 2, move
        );
    }

    @Test
    public void requestPlayers() {
        IOHelper io = new IOHelper("1");
        UI console = new Console(io.in, io.print);

        Players players = console.requestPlayers();
        assertEquals(
                "choosing option1 should return two players",
                PlayerSymbol.X,
                players.currentPlayerSymbol()
        );
    }

    @Test
    public void requestPlayers2() {
        IOHelper io = new IOHelper("2");
        UI console = new Console(io.in, io.print);

        Players players = console.requestPlayers();
        assertEquals(
                "choosing option 2 should return two players",
                PlayerSymbol.X,
                players.currentPlayerSymbol()
        );
    }

    @Test
    public void requestInvalidOption() {
        IOHelper io = new IOHelper("8 5 blah 3");
        UI console = new Console(io.in, io.print);

        Players players = console.requestPlayers();
        assertEquals(
                "consumes invalid input until a valid option between 1 and 3 is read",
                PlayerSymbol.X,
                players.currentPlayerSymbol()
        );

        assertTrue(
                "console notifies user of invalid options",
                io.output().contains("I didn't recognise that")
        );
    }

    @Test
    public void requestBoardSize() {
        IOHelper io = new IOHelper("3");
        UI console = new Console(io.in, io.print);

        int boardSize = console.requestBoardSize();
        assertEquals(
                "player can choose a board size of 3",
                3,
                boardSize
        );
    }

    @Test
    public void requestInvalidBoardSize() {
        IOHelper io = new IOHelper("wut? 5 4");
        UI console = new Console(io.in, io.print);

        int finalBoardSize = console.requestBoardSize();
        assertTrue(
                "console notifies user of invalid input or options",
                io.output().contains("I didn't recognise that")
        );

        assertEquals(
                "eventually returns valid input",
                4,
                finalBoardSize
        );
    }
}
