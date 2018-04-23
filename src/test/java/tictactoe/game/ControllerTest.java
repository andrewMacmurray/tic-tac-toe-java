package tictactoe.game;

import org.junit.Test;
import tictactoe.core.Player;

import static org.junit.Assert.*;

public class ControllerTest {

    @Test
    public void greet() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print,3, Player.X);

        controller.greetUser();
        assertEquals("Welcome to Tic Tac Toe!\n", io.out.toString());
    }

    @Test
    public void instructions() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print, 3, Player.X);

        controller.printInstructions();
        assertEquals("Enter a number from 1-9\n", io.out.toString());
    }
}