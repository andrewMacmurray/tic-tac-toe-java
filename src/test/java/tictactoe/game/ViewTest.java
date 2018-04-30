package tictactoe.game;

import org.junit.Test;
import tictactoe.core.PlayerSymbol;
import tictactoe.core.Board;
import tictactoe.core.Tile;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ViewTest {

    @Test
    public void renderAllEmptyTiles() {
        String[] lines = {
          " 1 | 2 | 3",
          View.divider,
          " 4 | 5 | 6",
          View.divider,
          " 7 | 8 | 9"
        };

        String expectedRenderedTiles = String.join("\n", lines);
        int boardSize = 3;
        Map<Integer, Tile> actualTiles = new Board(boardSize).getTiles();

        assertEquals("renders all empty tiles correctly", expectedRenderedTiles, View.renderTiles(actualTiles, boardSize));
    }

    @Test
    public void renderTilesWithPlayers() {
        String[] lines = {
                " X | 2 | O",
                View.divider,
                " 4 | 5 | 6",
                View.divider,
                " 7 | 8 | 9"
        };
        String expectedRenderedTiles = String.join("\n", lines);

        int boardSize = 3;
        Map<Integer, Tile> actualTiles = new Board(boardSize)
                .makeMove(1, PlayerSymbol.X)
                .makeMove(3, PlayerSymbol.O)
                .getTiles();

        assertEquals("renders tiles with player moves correctly", expectedRenderedTiles, View.renderTiles(actualTiles, boardSize));
    }
}
