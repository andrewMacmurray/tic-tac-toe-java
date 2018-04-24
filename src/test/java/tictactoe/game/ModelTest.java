package tictactoe.game;

import org.junit.Test;
import tictactoe.core.Board;
import tictactoe.core.GuessStatus;
import tictactoe.core.Player;
import tictactoe.core.GameStatus;

import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void emptyTiles() {
        Player[] modelTiles = new Model(3, Player.X).getTiles();
        Player [] boardTiles = new Board(3).getTiles();
        assertArrayEquals("new model should contain a collection of empty tiles", boardTiles, modelTiles);
    }

    @Test
    public void currentPlayer() {
       Model model = new Model(3, Player.X);
       assertEquals("model should contain a currentPlayer", Player.X, model.getCurrentPlayer());
    }

    @Test
    public void boardSize() {
        Model model = new Model(3, Player.X);
        assertEquals("model should contain the board size", 3, model.getBoardSize());
    }

    @Test
    public void evalMove() {
        Model model = new Model(3, Player.X);
        Model newModel = model.evalMove(0);
        Player[] tiles = newModel.getTiles();
        assertEquals("Player X should have made first move", Player.X, tiles[0]);
        assertEquals("Player O should be the next player", Player.O, newModel.getCurrentPlayer());
    }

    @Test
    public void immutableModel() {
        Model model1 = new Model(3, Player.X)
                .evalMove(0)
                .evalMove(1);

        Model model2 = model1
                .evalMove(2)
                .evalMove(3);

        assertEquals("model1 should not be affected by changes to model2", Player.Empty, model1.getTiles()[2]);
        assertEquals("model2 should be updated correctly", Player.X, model2.getTiles()[2]);
    }

    @Test
    public void validMoveStatus() {
        Model model = new Model(3, Player.X).evalMove(0);

        assertEquals("valid guess should result in a valid guess status", GuessStatus.Valid, model.getGuessStatus());
        assertEquals("board should be updated correctly", Player.X, model.getTiles()[0]);
    }

    @Test
    public void outOfBoundsMoveStatus() {
        Model beforeModel = new Model(3, Player.X);
        Model afterModel = beforeModel.evalMove(9);

        assertEquals("out of bounds guess should result in OutOfBounds guess status", GuessStatus.OutOfBounds, afterModel.getGuessStatus());
        assertArrayEquals("board should remain unmodified", beforeModel.getTiles(), afterModel.getTiles());
    }

    @Test
    public void alreadyTakenStatus() {
        Model beforeModel = new Model(3, Player.X).evalMove(1);
        Model afterModel = beforeModel.evalMove(1);

        assertEquals("attempting to take a previously taken tile should result in AlreadyTaken guess status", GuessStatus.AlreadyTaken, afterModel.getGuessStatus());
        assertArrayEquals("board should remain unmodified", beforeModel.getTiles(), afterModel.getTiles());
    }

    @Test
    public void getGameStatus() {
        Model model = new Model(3, Player.X)
                .evalMove(0)
                .evalMove(1)
                .evalMove(2);
        assertEquals("game should be in NonTerminal State", GameStatus.NonTerminal, model.getGameStatus());
    }

    @Test
    public void winStatus() {
        Model model = new Model(3, Player.X)
                .evalMove(0)
                .evalMove(3)
                .evalMove(1)
                .evalMove(4)
                .evalMove(2);
        assertEquals("game should be in Win state", GameStatus.Win, model.getGameStatus());
    }

    @Test
    public void drawStatus() {
        int boardSize = 3;
        int[] drawMoves = {0, 1, 2, 4, 3, 5, 7, 6, 8};
        Model model = new Model(boardSize, Player.X);
        for (int move: drawMoves) {
            model = model.evalMove(move);
        }
        assertEquals("board should be in draw state", GameStatus.Draw, model.getGameStatus());
    }
}