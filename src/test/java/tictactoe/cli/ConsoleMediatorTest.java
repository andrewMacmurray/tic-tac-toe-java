package tictactoe.cli;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConsoleMediatorTest {

    IOHelper ioHelper;
    ConsoleMediator consoleMediator;

    @Test
    public void playOneGame() {
        setIoHelperInput("1 3 1 4 2 5 3 N");
        consoleMediator.runGame();
        String output = ioHelper.output();

        assertTrue(output.contains("Welcome to Tic Tac Toe"));
        assertTrue(output.contains("Enter 1, 2 or 3"));
    }

    @Test
    public void playTwoGames() {
        setIoHelperInput("1 3 1 4 2 5 3 Y 1 3 4 1 5 2 7 3 N");
        consoleMediator.runGame();
        String output = ioHelper.output();

        assertTrue("Player X wins the first game", output.contains("Player X won!"));
        assertTrue("Player O wins the second game", output.contains("Player O won!"));
    }

    @Test
    public void handleDraw() {
        setIoHelperInput("1 3 1 2 3 5 8 4 6 9 7 N");
        consoleMediator.runGame();

        assertTrue(ioHelper.output().contains("It's a draw!"));
    }

    private void setIoHelperInput(String input) {
        ioHelper = new IOHelper(input);
        Console console = new Console(ioHelper.in, ioHelper.print);
        consoleMediator = new ConsoleMediator(console);
    }
}
