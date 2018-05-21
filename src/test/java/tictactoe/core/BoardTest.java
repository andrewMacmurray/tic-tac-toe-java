package tictactoe.core;

import org.junit.Test;
import tictactoe.core.players.PlayerSymbol;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void initializeBoard() {
        Board board = new Board(3);


        for (int i = 1; i <= 9; i++) {
            assertTrue("tile should be empty", board.getTile(i).isEmpty());
        }
    }

    @Test
    public void makeFirstMove() {
        Tile tile = new Board(3)
                .makeMove(1, PlayerSymbol.X)
                .getTile(1);

        assertTrue("PlayerTile at 1 should be X", tile.isTakenBy(PlayerSymbol.X));
    }

    @Test
    public void makeMultipleMoves() {
        Board board = new Board(3)
                .makeMove(1, PlayerSymbol.X)
                .makeMove(2, PlayerSymbol.O);

        assertTrue("PlayerTile at 1 should be X", board.getTile(1).isTakenBy(PlayerSymbol.X));
        assertTrue("PlayerTile at 2 should be O", board.getTile(2).isTakenBy(PlayerSymbol.O));
    }

    @Test
    public void immutableBoard() {
        Board board1 = new Board(3).makeMove(1, PlayerSymbol.X);
        Board board2 = board1.makeMove(1, PlayerSymbol.O);

        assertTrue("board 1 is not modified by board2",  board1.getTile(1).isTakenBy(PlayerSymbol.X));
        assertTrue("board 2 is updated correctly", board2.getTile(1).isTakenBy(PlayerSymbol.O));
    }

    @Test
    public void notFullBoard() {
        Board notFullBoard = new Board(3)
                .makeMove(0, PlayerSymbol.X)
                .makeMove(1, PlayerSymbol.O);
        assertFalse("should return false when not all tiles are filled", notFullBoard.isFull());
    }

    @Test
    public void fullBoard() {
        Board fullBoard = new Board(3);
        for (int i = 1; i <= 9; i++) {
            fullBoard = fullBoard.makeMove(i, PlayerSymbol.X);
        }
        assertTrue("should return true when all tiles are filled", fullBoard.isFull());
    }

    @Test
    public void isMoveAvailable() {
        Board board = new Board(3)
                .makeMove(1, PlayerSymbol.X)
                .makeMove(2, PlayerSymbol.O);

        assertFalse(board.isMoveAvailable(1));
        assertFalse(board.isMoveAvailable(2));
        assertTrue(board.isMoveAvailable(3));
    }

    @Test
    public void nonTerminal() {
        Board board = new Board(3)
                .makeMove(0, PlayerSymbol.X)
                .makeMove(1, PlayerSymbol.O)
                .makeMove(2, PlayerSymbol.X);
        assertFalse("No player should have won or drawn", board.isTerminal());
    }

    @Test
    public void xWinner() {
        Board board = new Board(3)
                .makeMove(1, PlayerSymbol.X)
                .makeMove(2, PlayerSymbol.X)
                .makeMove(3, PlayerSymbol.X);
        assertTrue("X should have won", board.xWin());
        assertFalse("O should not have won", board.oWin());
    }

    @Test
    public void oWinner() {
        Board board = new Board(3)
                .makeMove(1, PlayerSymbol.O)
                .makeMove(5, PlayerSymbol.O)
                .makeMove(9, PlayerSymbol.O);
        assertTrue("O should have won", board.oWin());
        assertFalse("X should not have won", board.xWin());

    }
}
