package tictactoe.core;

import org.junit.Test;
import tictactoe.cli.Console;
import tictactoe.core.GameRunner;
import tictactoe.cli.IOHelper;
import static org.junit.Assert.*;

public class GameRunnerTest {

    private String human3by3Game = "1 3 ";

    @Test
    public void runOneGame() {
        String oneGameSequence = human3by3Game + "1 4 2 5 3 No";
        IOHelper io = new IOHelper(oneGameSequence);
        GameRunner gameRunner = setupGameRunner(io);

        gameRunner.run();
        assertTrue(
                "game runs to its terminus",
                io.output().contains("Player X won!")
        );

        assertTrue(
                "says goodbye after user enters no",
                io.output().contains("Ok bye!")
        );
    }

    @Test
    public void runTwoGames() {
        String twoGameSequence = String.join(
                " ",
                human3by3Game,
                "1 4 2 5 3 YES ",
                human3by3Game,
                "4 1 5 2 7 3 NO"
        );
        IOHelper io = new IOHelper(twoGameSequence);
        GameRunner gameRunner = setupGameRunner(io);

        gameRunner.run();
        assertTrue(
                "first win should be player X",
                io.output().contains("Player X won!")
        );

        assertTrue(
                "second win should be player O",
                io.output().contains("Player O won!")
        );
    }


    private GameRunner setupGameRunner(IOHelper io) {
        Console console = new Console(io.in, io.print);
        return new GameRunner(console);
    }
}
