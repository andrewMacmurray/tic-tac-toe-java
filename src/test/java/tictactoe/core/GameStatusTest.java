package tictactoe.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameStatusTest {

    @Test
    public void winGameOver() {
        assertTrue("win should signal game over", GameStatus.Win.isGameOver());
    }

    @Test
    public void drawGameOver() {
        assertTrue("draw should signal game over", GameStatus.Draw.isGameOver());
    }

    @Test
    public void nonTerminalGameOver() {
        assertFalse("non terminal state should not be game over", GameStatus.NonTerminal.isGameOver());
    }
}
