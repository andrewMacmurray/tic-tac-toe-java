package tictactoe.core;

import org.junit.Before;
import org.junit.Test;
import tictactoe.core.players.HumanPlayer;
import tictactoe.core.players.Player;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.mocks.MockMediator;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    private MockMediator mockMediator;

    @Before
    public void setup() {
        mockMediator = new MockMediator();
    }

    @Test
    public void playerSymbol() {
        Player human = new HumanPlayer(PlayerSymbol.O, mockMediator::requestMoveFromUI);

        assertEquals(
                "can retrieve the player symbol from human player",
                PlayerSymbol.O,
                human.getSymbol()
        );
    }

    @Test
    public void chooseMove() {
        Player human = new HumanPlayer(PlayerSymbol.X, mockMediator::requestMoveFromUI);
        Board board = new Board(3);

        human.requestMove(board);
        assertEquals(
                "human can request a move via the mockMediator",
                "request move",
                mockMediator.getLog()
        );
    }

}

