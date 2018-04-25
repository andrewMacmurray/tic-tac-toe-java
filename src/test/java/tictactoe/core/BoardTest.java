package tictactoe.core;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void initializeBoard() {
        Board board = new Board(3);
        HashMap<Integer, Player> tiles = board.getTiles();

        for (Map.Entry<Integer, Player> tile : tiles.entrySet()) {
            assertEquals("tile should be empty", Player.Empty, tile.getValue());
        }
    }

    @Test
    public void makeFirstMove() {
        Player tile = new Board(3)
                .makeMove(1, Player.X)
                .getTile(1);

        assertEquals("Tile at 1 should be X", Player.X, tile);
    }

    @Test
    public void makeMultipleMoves() {
        Board board = new Board(3)
                .makeMove(1, Player.X)
                .makeMove(2, Player.O);

        assertEquals("Tile at 1 should be X", Player.X, board.getTile(1));
        assertEquals("Tile at 2 should be O", Player.O, board.getTile(2));
    }

    @Test
    public void immutableBoard() {
        Board board1 = new Board(3).makeMove(0, Player.X);
        Board board2 = board1.makeMove(0, Player.O);

        assertEquals("board 1 is not modified by board2", Player.X, board1.getTile(0));
        assertEquals("board 2 is updated correctly", Player.O, board2.getTile(0));
    }

    @Test
    public void notFullBoard() {
        Board notFullBoard = new Board(3).makeMove(0, Player.X).makeMove(1, Player.O);
        assertFalse("should return false when not all tiles are filled", notFullBoard.isFull());
    }

    @Test
    public void fullBoard() {
        Board fullBoard = new Board(3);
        for (int i = 0; i < fullBoard.getTiles().size(); i++) {
            fullBoard = fullBoard.makeMove(i, Player.X);
        }
        assertTrue("should return true when all tiles are filled", fullBoard.isFull());
    }

    @Test
    public void isMoveAvailable() {
        Board board = new Board(3)
                .makeMove(1, Player.X)
                .makeMove(2, Player.O);

        assertFalse(board.isMoveAvailable(1));
        assertFalse(board.isMoveAvailable(2));
        assertTrue(board.isMoveAvailable(3));
    }

    @Test
    public void noWinner() {
        Board board = new Board(3)
                .makeMove(0, Player.X)
                .makeMove(1, Player.O)
                .makeMove(2, Player.X);
        assertEquals("No player should have won", Player.Empty, board.winner());
    }

    @Test
    public void xWinner() {
        Board board = new Board(3)
                .makeMove(1, Player.X)
                .makeMove(2, Player.X)
                .makeMove(3, Player.X);
        assertEquals("X should have won", Player.X, board.winner());
    }

    @Test
    public void oWinner() {
        Board board = new Board(3)
                .makeMove(1, Player.O)
                .makeMove(5, Player.O)
                .makeMove(9, Player.O);
        assertEquals("O should have won", Player.O, board.winner());

    }
}
