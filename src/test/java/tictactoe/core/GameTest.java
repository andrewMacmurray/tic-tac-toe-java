package tictactoe.core;

import org.junit.Before;
import org.junit.Test;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;
import tictactoe.mocks.MockMediator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GameTest {

    MockMediator mockMediator;
    Players players;
    Game game;

    @Before
    public void setup() {
        mockMediator = new MockMediator();
        players = new PlayersFactory(mockMediator).createPlayers(1);
        game = new Game(mockMediator);
    }

    @Test
    public void receivePlayers() {
        game.receivePlayers(players);
        assertEquals(
                "setting players prompts the game to request boardSize",
                "request board size",
                mockMediator.getLog()
        );
    }

    @Test
    public void receiveBoardSize() {
        game.receivePlayers(players);
        game.receiveBoardSize(3);
        assertEquals(
                "after setting the board size the mediator will ask for a move",
                "request move",
                mockMediator.getLog()
        );
    }

    @Test
    public void recieveMove() {
        setupGameOptions();
        game.receiveMove(1);
        assertEquals("move 1 has been played", 1, mockMediator.getCurrentMove());
        assertFalse("board has been updated correctly", mockMediator.getCurrentBoard().isMoveAvailable(1));
    }

    @Test
    public void xWin() {
        setupGameOptions();
        playMoves(1, 4, 2, 5, 3);
        assertEquals("X won", mockMediator.getLog());
    }

    @Test
    public void oWin() {
        setupGameOptions();
        playMoves(4, 1, 7, 2, 9, 3);
        assertEquals("O won", mockMediator.getLog());
    }

    @Test
    public void draw() {
        setupGameOptions();
        playMoves(1, 2, 3, 5, 8, 4, 6, 9, 7);
        assertEquals("it's a draw", mockMediator.getLog());
    }

    private void setupGameOptions() {
        game.receivePlayers(players);
        game.receiveBoardSize(3);
    }

    private void playMoves(int... moves) {
        for (int move : moves) {
            game.receiveMove(move);
        }
    }
}
