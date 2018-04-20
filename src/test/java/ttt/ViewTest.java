package ttt;

import org.junit.Test;
import ttt.core.Player;
import ttt.game.View;

import java.util.Arrays;
import static org.junit.Assert.*;

public class ViewTest {

    View view = new View();

    @Test
    public void renderTile() {
        String xTile = view.renderTile(Player.X, 0);
        assertEquals("renders X player correctly", "X", xTile);

        String oTile = view.renderTile(Player.O, 0);
        assertEquals("renders O player correctly", "O", oTile);

        String emptyTile = view.renderTile(Player.Empty, 0);
        assertEquals("renders empty tile correctly", "1", emptyTile);
    }

    @Test
    public void renderEmptyBoard() {
        Player[] emptyBoard = createEmptyBoard();
        String renderedBoard = view.renderBoard(emptyBoard, 3);
        String expectedBoard = " 1 | 2 | 3\n---*---*---\n 4 | 5 | 6\n---*---*---\n 7 | 8 | 9\n";
        assertEquals(expectedBoard, renderedBoard);
    }

    @Test
    public void renderBoardWithPlayers() {
        Player[] board = createEmptyBoard();
        board[0] = Player.X;
        board[1] = Player.X;
        String renderedBoard = view.renderBoard(board, 3);
        String expectedBoard = " X | X | 3\n---*---*---\n 4 | 5 | 6\n---*---*---\n 7 | 8 | 9\n";
    }

    public Player[] createEmptyBoard() {
        Player[] board = new Player[9];
        Arrays.fill(board, Player.Empty);
        return board;
    }
}
