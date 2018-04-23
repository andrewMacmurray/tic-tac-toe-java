package tictactoe;

import org.junit.Test;
import tictactoe.core.Player;
import tictactoe.core.Board;
import tictactoe.game.View;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ViewTest {

    View view = new View();

    @Test
    public void renderAllEmptyTiles() {
        String renderedTiles = " 1 | 2 | 3\n---*---*---\n 4 | 5 | 6\n---*---*---\n 7 | 8 | 9\n";

        int boardSize = 3;
        Player[] actualTiles = new Board(boardSize).getTiles();

        assertEquals("renders all empty tiles correctly", renderedTiles, view.renderTiles(actualTiles, boardSize));
    }

    @Test
    public void renderTilesWithPlayers() {
        String renderedTiles = " X | 2 | O\n---*---*---\n 4 | 5 | 6\n---*---*---\n 7 | 8 | 9\n";

        int boardSize = 3;
        Player[] actualTiles = new Board(boardSize)
                .makeMove(0, Player.X)
                .makeMove(2, Player.O)
                .getTiles();

        assertEquals("renders tiles with player moves correctly", renderedTiles, view.renderTiles(actualTiles, boardSize));
    }
}
