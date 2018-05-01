package tictactoe.core;

import org.junit.Test;
import tictactoe.core.types.GameStatus;

import static org.junit.Assert.*;

public class GameStatusTest {

    @Test
    public void winGameOver() {
        assertTrue("win should signal cli over", GameStatus.Win.isGameOver());
    }

    @Test
    public void drawGameOver() {
        assertTrue("draw should signal cli over", GameStatus.Draw.isGameOver());
    }

    @Test
    public void nonTerminalGameOver() {
        assertFalse("non terminal state should not be cli over", GameStatus.NonTerminal.isGameOver());
    }
}
