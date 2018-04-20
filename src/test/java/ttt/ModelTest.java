package ttt;

import org.junit.Test;
import ttt.core.Player;
import ttt.game.Model;
import ttt.game.MoveStatus;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void initializeModelBoardScale() {
        int expectedBoardScale = 3;
        Model model = new Model(expectedBoardScale, Player.X);
        assertEquals("board scale initialized correctly", expectedBoardScale  , model.getBoardScale());

    }

    @Test
    public void initalizeModelBoard() {
        Player[] emptyBoard = new Player[9];
        Arrays.fill(emptyBoard, Player.Empty);

        Model model = new Model(3, Player.X);
        assertEquals("empty board initialized correctly", emptyBoard, model.getTiles());
    }

    @Test
    public void initializeFirstPlayer() {
        Player firstPlayer = Player.O;
        Model model = new Model(3, firstPlayer);
        assertEquals("Initializes with correct player", firstPlayer, model.getCurrentPlayer());
    }


    @Test
    public void updateBoard() {
        Model model = new Model(3, Player.X);
        model.update(0);
        Player tileZero = model.getTiles()[0];
        assertEquals("Player X can make the first move", Player.X, tileZero);
    }

    @Test
    public void makeSecondMove() {
        Model model = new Model(3, Player.X);
        model.update(0);
        model.update(1);
        Player tileZero = model.getTiles()[0];
        Player tileOne = model.getTiles()[1];

        assertEquals("Player X has made first move", Player.X, tileZero);
        assertEquals("Player O has made second move", Player.O, tileOne);
    }

    @Test
    public void handleInvalidMove() {
        Model model = new Model(3, Player.X);

        model.update(0);
        Player[] tilesAfterValidMove = model.getTiles();
        MoveStatus statusAfterValidMove = model.getMoveStatus();

        model.update(0);
        Player[] tilesAfterInvalidMove = model.getTiles();
        MoveStatus statusAfterInvalidMove = model.getMoveStatus();

        assertEquals("move status valid after valid move", MoveStatus.Valid, statusAfterValidMove);
        assertEquals("move status alreadyTaken after player tries to take same move", MoveStatus.TileTaken, statusAfterInvalidMove);

        assertTrue("tiles are not updated after invalid move", Arrays.equals(tilesAfterValidMove, tilesAfterValidMove));
    }
}
