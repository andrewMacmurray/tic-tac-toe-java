package tictactoe.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatusTest {

    @Test
    public void winGameOver() {
        assertTrue("win should signal game over", Status.Win.isGameOver());
    }

    @Test
    public void drawGameOver() {
        assertTrue("draw should signal game over", Status.Draw.isGameOver());
    }

    @Test
    public void nonTerminalGameOver() {
        assertFalse("non terminal state should not be game over", Status.NonTerminal.isGameOver());
    }
}
