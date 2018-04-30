package tictactoe.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerSymbolTest {

    @Test
    public void alternatePlayer() {
        assertEquals("returns alternate player for X", PlayerSymbol.O, PlayerSymbol.X.getAlternate());
        assertEquals( "returns alternate player for O", PlayerSymbol.X, PlayerSymbol.O.getAlternate());
    }
}
