package tictactoe.core;

import org.junit.Test;
import tictactoe.cli.Console;
import tictactoe.cli.IOHelper;
import tictactoe.core.players.HumanPlayer;
import tictactoe.core.players.Player;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;
import tictactoe.mocks.MockRequestUI;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void runGame() {
        IOHelper io = new IOHelper("1 2 3 4 5 6 7");
        Game game = setupGame(io);

        game.run();
        assertTrue(
                "player X should have won the game",
                io.output().contains("Player X won!")
        );
    }

    @Test
    public void handleInvalidInputs() {
        IOHelper io = new IOHelper("1 1 2 blah 3 4 5 6 7");
        Game game = setupGame(io);

        game.run();
        assertTrue(
                "prints unrecognised inputs",
                io.output().contains("I didn't recognise that")
        );

        assertTrue(
                "prints already taken moves",
                io.output().contains("1 is already taken!")
        );

        assertTrue(
                "player X should have won the game",
                io.output().contains("Player X won!")
        );
    }

    @Test
    public void oWin() {
        IOHelper io = new IOHelper("1 2 3 5 4 8");
        Game game = setupGame(io);

        game.run();
        assertTrue(
                "player O should have won",
                io.output().contains("Player O won!")
        );
    }

    @Test
    public void draw() {
        IOHelper io = new IOHelper("1 2 3 5 4 6 8 7 9");
        Game game = setupGame(io);

        game.run();
        assertTrue(
                "should be a draw",
                io.output().contains("It's a draw!")
        );
    }

    private Game setupGame(IOHelper io) {
        Console console = new Console(io.in, io.print);
        Players players = PlayersFactory.createPlayers(1, console);

        return new Game(3, console, players);
    }
}
