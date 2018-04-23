package tictactoe;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void alternatePlayer() {
        assertEquals("returns alternate player for X", Player.O, Player.X.getAlternate());
        assertEquals( "returns alternate player for O", Player.X, Player.O.getAlternate());
    }

    @Test
    public void alternateEmpty() {
        assertEquals("returns empty if alternate called on empty", Player.Empty, Player.Empty.getAlternate());
    }

    @Test
    public void isEmpty() {
        assertTrue("returns true if empty", Player.Empty.isEmpty());
        assertFalse("returns false if not empty", Player.X.isEmpty());
        assertFalse("returns false if not empty", Player.O.isEmpty());
    }
}
