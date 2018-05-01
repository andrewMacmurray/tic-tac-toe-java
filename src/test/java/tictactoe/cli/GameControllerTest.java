package tictactoe.cli;

import org.junit.Test;
import tictactoe.core.types.PlayerSymbol;

import static org.junit.Assert.*;

public class GameControllerTest {

    @Test
    public void instructions() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);

        controller.enterNumberInstructions();
        assertEquals("Enter a number from 1-9\n", io.output());
    }

    @Test
    public void clearScreen() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);

        controller.enterNumberInstructions();
        controller.clearScreen();
        String clearSequence = "[H\u001B[2J";

        assertTrue(io.output().contains(clearSequence));
    }

    @Test
    public void printBoard() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);

        controller.printBoard();
        String[] expectedLines = {
                " 1 | 2 | 3",
                BoardSerializer.divider,
                " 4 | 5 | 6",
                BoardSerializer.divider,
                " 7 | 8 | 9"
        };
        String expectedBoard = io.joinLines(expectedLines);
        assertEquals(expectedBoard, io.output());
    }

    @Test
    public void isGameOver() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);

        assertFalse("gameOver should be false if cli unfinished", controller.isGameOver());
    }

    @Test
    public void printPlayerGuess() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);

        controller.printPlayerGuess(1, PlayerSymbol.X);

        String[] expectedLines = {
                "Player X took tile 1",
                "Your turn Player O"
        };
        String expected = io.joinLines(expectedLines);
        assertEquals(expected, io.output());
    }

    @Test
    public void handleValidGuess() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);
        controller.handleGuess("1");

        String[] expectedLines = {
                " X | 2 | 3",
                BoardSerializer.divider,
                " 4 | 5 | 6",
                BoardSerializer.divider,
                " 7 | 8 | 9",
                "Player X took tile 1",
                "Your turn Player O"
        };
        String expected = io.joinLines(expectedLines);
        assertTrue(io.output().contains(expected));
    }

    @Test
    public void handleOutOfBound() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);
        controller.handleGuess("12");

        String expected = "Enter a number from 1-9\n";
        assertTrue(io.output().contains(expected));
    }

    @Test
    public void handleAlreadyTaken() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);
        controller.handleGuess("1");
        io.reset();
        controller.handleGuess("1");

        String expected = "1 is already taken! Try another tile\n";
        assertTrue(io.output().contains(expected));
    }

    @Test
    public void handleUnrecognised() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);
        controller.handleGuess("wut?");

        String expected = "Sorry I didn't recognise that\n";
        assertEquals(expected, io.output());
    }

    @Test
    public void printPlayerXWin() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);
        String[] winMoves = {"1", "2", "3", "4", "5", "6", "7"};

        for (String move: winMoves) {
            controller.handleGuess(move);
        }

        io.reset();

        controller.printTerminus();

        String expected = "Player X won!\n";
        assertEquals(expected, io.output());
    }

    @Test
    public void printDraw() {
        IOHelper io = new IOHelper();
        GameController controller = new GameController(io.print, 3, PlayerSymbol.X);
        String[] drawMoves = {"1", "2", "3", "5", "4", "6", "8", "7", "9"};

        for (String move: drawMoves) {
            controller.handleGuess(move);
        }

        io.reset();

        controller.printTerminus();

        String expected = "It's a draw!\n";
        assertEquals(expected, io.output());
    }
}
