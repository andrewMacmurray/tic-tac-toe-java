package tictactoe.cli;

import org.junit.Test;
import tictactoe.core.Board;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;
import tictactoe.core.ui.UIRequest;

import static org.junit.Assert.*;

public class ConsoleRequestTest {

    @Test
    public void requestMove() {
        IOHelper io = new IOHelper("3");
        UIRequest console = new Console(io.in, io.print);
        Board board = new Board(3);

        assertEquals(
                "console can request a move via IO",
                3,
                console.requestMove(board, PlayerSymbol.X)
        );
    }

    @Test
    public void notifyInvalidMove() {
        IOHelper io = new IOHelper("blah 3");
        UIRequest console = new Console(io.in, io.print);
        Board board = new Board(3);

        int move = console.requestMove(board, PlayerSymbol.X);
        assertTrue(
                "console notifies user of invalid move",
                io.output().contains("I didn't recognise that")
        );

        assertEquals(
                "eventually returns a valid move", 3, move
        );
    }

    @Test
    public void requestPlayers() {
        IOHelper io = new IOHelper("1");
        UIRequest console = new Console(io.in, io.print);

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
        UIRequest console = new Console(io.in, io.print);

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
        UIRequest console = new Console(io.in, io.print);

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
}
