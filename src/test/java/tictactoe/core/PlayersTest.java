package tictactoe.core;

import org.junit.Before;
import org.junit.Test;
import tictactoe.core.players.HumanPlayer;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;
import tictactoe.mocks.MockMediator;

import static org.junit.Assert.assertEquals;

public class PlayersTest {

    private Players players;
    private MockMediator mockMediator;

    @Before
    public void setup() {
        mockMediator = new MockMediator();
        HumanPlayer player1 = new HumanPlayer(PlayerSymbol.X, mockMediator);
        HumanPlayer player2 = new HumanPlayer(PlayerSymbol.O, mockMediator);
        players = new Players(player1, player2);
    }

    @Test
    public void currentPlayerSymbol() {
        assertEquals(
                "current player should be X",
                PlayerSymbol.X,
                players.currentPlayerSymbol()
        );

    }

    @Test
    public void nextPlayerSymbol() {
        players.switchPlayers();

        assertEquals(
                "current player should be O after switching",
                PlayerSymbol.O,
                players.currentPlayerSymbol()
        );
    }

    @Test
    public void switchBack() {
        players.switchPlayers();
        players.switchPlayers();

        assertEquals(
                "Players switch back correctly",
                PlayerSymbol.X,
                players.currentPlayerSymbol()
        );
    }

    @Test
    public void chooseHumanMove() {
        Board board = new Board(3);

        players.chooseNextMove(board);
        assertEquals(
                "current player can request a move",
                "request move",
                mockMediator.getLog()
        );
    }

}
