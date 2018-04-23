package tictactoe.game;

import org.junit.Test;
import tictactoe.core.Board;
import tictactoe.core.Player;
import tictactoe.core.Status;

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
    public void makeMove() {
        Model model = new Model(3, Player.X);
        Model newModel = model.makeMove(0);
        Player[] tiles = newModel.getTiles();
        assertEquals("Player X should have made first move", Player.X, tiles[0]);
        assertEquals("Player O should be the next player", Player.O, newModel.getCurrentPlayer());
    }

    @Test
    public void immutableModel() {
        Model model1 = new Model(3, Player.X)
                .makeMove(0)
                .makeMove(1);

        Model model2 = model1
                .makeMove(2)
                .makeMove(3);

        assertEquals("model1 should not be affected by changes to model2", Player.Empty, model1.getTiles()[2]);
        assertEquals("model2 should be updated correctly", Player.X, model2.getTiles()[2]);
    }

    @Test
    public void getStatus() {
        Model model = new Model(3, Player.X)
                .makeMove(0)
                .makeMove(1)
                .makeMove(2);
        assertEquals("game should be in NonTerminal State", Status.NonTerminal, model.getStatus());
    }

    @Test
    public void winStatus() {
        Model model = new Model(3, Player.X)
                .makeMove(0)
                .makeMove(3)
                .makeMove(1)
                .makeMove(4)
                .makeMove(2);
        assertEquals("game should be in Win state", Status.Win, model.getStatus());
    }

    @Test
    public void drawStatus() {
        int boardSize = 3;
        int[] drawMoves = {0, 1, 2, 4, 3, 5, 7, 6, 8};
        Model model = new Model(boardSize, Player.X);
        for (int move: drawMoves) {
            model = model.makeMove(move);
        }
        assertEquals("board should be in draw state", Status.Draw, model.getStatus());
    }
}