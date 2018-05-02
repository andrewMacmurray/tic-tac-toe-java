package tictactoe.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameRunnerTest {

    private String player1WinSequence = "1 1 2 5 4 9";

    @Test
    public void greetUser() {
        IOHelper io = new IOHelper(player1WinSequence);
        GameRunner gameRunner = new GameRunner(io.in, io.print);

        gameRunner.run();
        assertTrue(
                "greets the user",
                io.output().contains("Welcome to Tic Tac Toe!")
        );
    }

    @Test
    public void selectOptions() {
        IOHelper io = new IOHelper(player1WinSequence);
        GameRunner gameRunner = new GameRunner(io.in, io.print);

        gameRunner.run();
        assertTrue(
                "Prints game options to user",
                io.output().contains("Select a game to play")
        );
    }

    @Test
    public void runGame() {
        IOHelper io = new IOHelper(player1WinSequence);
        GameRunner gameRunner = new GameRunner(io.in, io.print);

        gameRunner.run();
        assertTrue(
                "game runs to it's conclusion",
                io.output().contains("Player X won!")
        );
    }
}
