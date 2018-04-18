package ttt;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void initializeBoard() {
        // assert all tiles are empty
        Board emptyBoard = new Board();
        for (Integer tile = 0; tile <= 8; tile++)
            assertNull("no players at any position in empty board", emptyBoard.getTile(tile));
    }

    @Test
    public void makeFirstMove() {
       // assert that player can make first move
       Board board = new Board();
       board.takeTile(0, Player.X);
       assertEquals("Player can take a tile", Player.X, board.getTile(0));
    }

    @Test
    public void makeSecondMove() {
       Board board = new Board();
       board.takeTile(0, Player.X);
       board.takeTile(1, Player.O);
       assertEquals("Next player can take a tile", Player.O, board.getTile(1));
    }

    @Test
    public void checkNoWin() {
        Board board = new Board();
        assertNull("should return null if no player has won", board.winner());
    }

    @Test
    public void checkXWin() {
        Board board = new Board();
        board.takeTile(0, Player.X);
        board.takeTile(1, Player.X);
        board.takeTile(2, Player.X);
        assertEquals("checks X has won", Player.X, board.winner());
    }

    @Test
    public void checkOWin() {
        Board board = new Board();
        board.takeTile(1, Player.O);
        board.takeTile(4, Player.O);
        board.takeTile(7, Player.O);
        assertEquals("checks O has won", Player.O, board.winner());
    }
}