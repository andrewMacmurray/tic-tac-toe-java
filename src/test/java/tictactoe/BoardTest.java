package tictactoe;

import org.junit.Test;
import tictactoe.core.Board;
import tictactoe.core.Player;

import static org.junit.Assert.*;


public class BoardTest {

    @Test
    public void initializeBoard() {
        Board board = new Board(3);
        Player[] tiles = board.getTiles();

        for (Player tile : tiles) {
            assertEquals("tile should be empty", Player.Empty, tile);
        }
    }

    @Test
    public void makeFirstMove() {
        Player tile = new Board(3)
                .makeMove(0, Player.X)
                .getTile(0);

        assertEquals("Tile at 0 should be X", Player.X, tile);
    }

    @Test
    public void makeMultipleMoves() {
        Board board = new Board(3)
                .makeMove(0, Player.X)
                .makeMove(1, Player.O);

        assertEquals("Tile at 0 should be X", Player.X, board.getTile(0));
        assertEquals("Tile at 1 should be O", Player.O, board.getTile(1));
    }

    @Test
    public void immutableBoard() {
        Board board1 = new Board(3).makeMove(0, Player.X);
        Board board2 = board1.makeMove(0, Player.O);

        assertEquals("board 1 is not modified by board2", Player.X, board1.getTile(0));
        assertEquals("board 2 is updated correctly", Player.O, board2.getTile(0));
    }

    @Test
    public void fullBoard() {
        Board board = new Board(3);
        assertFalse("should return false when board is not full", board.isFull());

        Board fullBoard = board;
        for (int i = 0; i < board.getTiles().length; i++) {
            fullBoard = fullBoard.makeMove(i, Player.X);
        }
        assertTrue("should return true when all getTiles are filled", fullBoard.isFull());
    }
}
