package tictactoe.cli;

import org.junit.Test;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.Board;
import tictactoe.core.Tile;

import java.util.Map;

import static org.junit.Assert.*;

public class BoardSerializerTest {

    @Test
    public void renderAllEmptyTiles() {
        String[] lines = {
          " 1 | 2 | 3",
          BoardSerializer.divider,
          " 4 | 5 | 6",
          BoardSerializer.divider,
          " 7 | 8 | 9"
        };

        String expectedRenderedTiles = String.join("\n", lines);
        int boardSize = 3;
        Board board = new Board(boardSize);

        String actualRendered = stripAnsi(BoardSerializer.render(board));

        assertEquals("renders all empty tiles correctly", expectedRenderedTiles, actualRendered);
    }

    @Test
    public void renderTilesWithPlayers() {
        String[] lines = {
                " X | 2 | O",
                BoardSerializer.divider,
                " 4 | 5 | 6",
                BoardSerializer.divider,
                " 7 | 8 | 9"
        };
        String expectedRenderedTiles = String.join("\n", lines);

        int boardSize = 3;
       Board board= new Board(boardSize)
                .makeMove(1, PlayerSymbol.X)
                .makeMove(3, PlayerSymbol.O);

       String actualRendered = stripAnsi(BoardSerializer.render(board));

        assertEquals("renders tiles with player moves correctly", expectedRenderedTiles, actualRendered);
    }

    private String stripAnsi(String input) {
        return input.replaceAll("\u001B\\[[\\d;]*[^\\d;]","");
    }
}
