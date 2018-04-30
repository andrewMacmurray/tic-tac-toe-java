package tictactoe.game;

import org.junit.Test;
import tictactoe.core.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void currentPlayer() {
       Model model = new Model(3, PlayerSymbol.X);
       assertEquals("model should contain a currentPlayer", PlayerSymbol.X, model.getCurrentPlayer());
    }

    @Test
    public void boardSize() {
        Model model = new Model(3, PlayerSymbol.X);
        assertEquals("model should contain the board size", 3, model.getBoardSize());
    }

    @Test
    public void evalMove() {
        Model model = new Model(3, PlayerSymbol.X);
        Model newModel = model.evalMove(1);
        Map<Integer, Tile> tiles = newModel.getTiles();
        assertTrue("PlayerSymbol X should have made first move", tiles.get(1).isTakenBy(PlayerSymbol.X));
        assertEquals("PlayerSymbol O should be the next player", PlayerSymbol.O, newModel.getCurrentPlayer());
    }

    @Test
    public void immutableModel() {
        Model model1 = new Model(3, PlayerSymbol.X)
                .evalMove(1)
                .evalMove(2);

        Model model2 = model1
                .evalMove(3)
                .evalMove(4);

        assertFalse("model1 should not be affected by changes to model2",  model1.getTiles().get(3).isTakenBy(PlayerSymbol.X));
        assertTrue("model2 should be updated correctly",  model2.getTiles().get(3).isTakenBy(PlayerSymbol.X));
    }

    @Test
    public void validMoveStatus() {
        Model model = new Model(3, PlayerSymbol.X).evalMove(1);

        assertEquals("valid guess should result in a valid guess status", GuessStatus.Valid, model.getGuessStatus());
        assertTrue("board should be updated correctly", model.getTiles().get(1).isTakenBy(PlayerSymbol.X));
    }

    @Test
    public void outOfBoundsMoveStatus() {
        Model beforeModel = new Model(3, PlayerSymbol.X);
        Model afterModel = beforeModel.evalMove(10);

        assertEquals("out of bounds guess should result in OutOfBounds guess status", GuessStatus.OutOfBounds, afterModel.getGuessStatus());
        // assertArrayEquals("board should remain unmodified", beforeModel.getTiles(), afterModel.getTiles());
    }

    @Test
    public void alreadyTakenStatus() {
        Model beforeModel = new Model(3, PlayerSymbol.X).evalMove(1);
        Model afterModel = beforeModel.evalMove(1);

        assertEquals("attempting to take a previously taken tile should result in AlreadyTaken guess status", GuessStatus.AlreadyTaken, afterModel.getGuessStatus());
        // assertArrayEquals("board should remain unmodified", beforeModel.getTiles(), afterModel.getTiles());
    }

    @Test
    public void getGameStatus() {
        Model model = new Model(3, PlayerSymbol.X)
                .evalMove(1)
                .evalMove(2)
                .evalMove(3);
        assertEquals("game should be in NonTerminal State", GameStatus.NonTerminal, model.getGameStatus());
    }

    @Test
    public void winStatus() {
        Model model = new Model(3, PlayerSymbol.X)
                .evalMove(1)
                .evalMove(4)
                .evalMove(2)
                .evalMove(5)
                .evalMove(3);
        assertEquals("game should be in Win state", GameStatus.Win, model.getGameStatus());
    }

    @Test
    public void drawStatus() {
        int boardSize = 3;
        int[] drawMoves = {1, 2, 3, 5, 4, 6, 8, 7, 9};
        Model model = new Model(boardSize, PlayerSymbol.X);
        for (int move: drawMoves) {
            model = model.evalMove(move);
        }
        assertEquals("board should be in draw state", GameStatus.Draw, model.getGameStatus());
    }
}