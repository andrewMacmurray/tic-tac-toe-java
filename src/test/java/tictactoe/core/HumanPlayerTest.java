package tictactoe.core;

import org.junit.Test;
import tictactoe.core.players.HumanPlayer;
import tictactoe.core.players.Player;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;
import tictactoe.core.ui.UIRequest;

import static org.junit.Assert.*;

public class HumanPlayerTest {

    @Test
    public void playerSymbol() {
        Player human = new HumanPlayer(PlayerSymbol.O, new MockUI());

        assertEquals(
                "can retrieve the player symbol from human player",
                PlayerSymbol.O,
                human.getSymbol()
        );
    }

    @Test
    public void chooseMove() {
        Player human = new HumanPlayer(PlayerSymbol.X, new MockUI());
        Board board = new Board(3);

        assertEquals(
                "human can request a move from the UI",
                3,
                human.chooseNextMove(board)
        );
    }
}


class MockUI implements UIRequest {

    @Override
    public Integer requestMove(Board board, PlayerSymbol playerSymbol) {
        return 3;
    }

    @Override
    public Players requestPlayers() {
        return null;
    }
}
