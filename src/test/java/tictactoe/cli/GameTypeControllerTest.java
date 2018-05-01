package tictactoe.cli;

import org.junit.Test;
import tictactoe.core.types.GameType;

import static org.junit.Assert.*;

public class GameTypeControllerTest {

    @Test
    public void gameTypeInstructions() {
        IOHelper io = new IOHelper();
        GameTypeController controller = new GameTypeController(io.in, io.print);

        controller.gameTypeInstructions();
        String[] lines = {
                "Select a cli to play:",
                "-----------------------",
                "1. Human vs Human",
                "2. Human vs Computer",
                "3. Computer vs Computer",
                "-----------------------",
                "Enter 1, 2 or 3:"
        };

        assertEquals(io.joinLines(lines), io.output());
    }

    @Test
    public void gameOption1() {
        IOHelper io = new IOHelper("1");
        GameTypeController controller = new GameTypeController(io.in, io.print);

        controller.promptGameType();
        GameType gameType = controller.getGameType();
        assertEquals(gameType, GameType.HumanVsHuman);
    }

    @Test
    public void gameOption2() {
        IOHelper io = new IOHelper("2");
        GameTypeController controller = new GameTypeController(io.in, io.print);

        controller.promptGameType();
        GameType gameType = controller.getGameType();
        assertEquals(gameType, GameType.HumanVsComputer);
    }

    @Test
    public void gameOption3() {
        IOHelper io = new IOHelper("3");
        GameTypeController controller = new GameTypeController(io.in, io.print);

        controller.promptGameType();
        GameType gameType = controller.getGameType();
        assertEquals(gameType, GameType.ComputerVsComputer);
    }

    @Test
    public void run() {
        IOHelper io =  new IOHelper("3");
        GameTypeController controller = new GameTypeController(io.in, io.print);

        GameType gameType = controller.run();
        assertEquals(gameType, GameType.ComputerVsComputer);
    }
}
