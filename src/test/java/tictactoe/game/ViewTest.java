package tictactoe.game;

import org.junit.Test;
import tictactoe.core.Player;
import tictactoe.core.Board;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ViewTest {

    View view = new View();

    @Test
    public void renderAllEmptyTiles() {
        String[] lines = {
          " 1 | 2 | 3",
          "---*---*---",
          " 4 | 5 | 6",
          "---*---*---",
          " 7 | 8 | 9"
        };

        String expectedRenderedTiles = String.join("\n", lines);
        int boardSize = 3;
        HashMap<Integer, Player> actualTiles = new Board(boardSize).getTiles();

        assertEquals("renders all empty tiles correctly", expectedRenderedTiles, view.renderTiles(actualTiles, boardSize));
    }

    @Test
    public void renderTilesWithPlayers() {
        String[] lines = {
                " X | 2 | O",
                "---*---*---",
                " 4 | 5 | 6",
                "---*---*---",
                " 7 | 8 | 9"
        };
        String expectedRenderedTiles = String.join("\n", lines);

        int boardSize = 3;
        HashMap<Integer, Player> actualTiles = new Board(boardSize)
                .makeMove(1, Player.X)
                .makeMove(3, Player.O)
                .getTiles();

        assertEquals("renders tiles with player moves correctly", expectedRenderedTiles, view.renderTiles(actualTiles, boardSize));
    }
}
