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
                "-----------",
                " 4 | 5 | 6",
                "-----------",
                " 7 | 8 | 9"
        };

        String expectedRenderedTiles = String.join("\n", lines);
        int boardSize = 3;
        Board board = new Board(boardSize);

        String actualRendered = stripAnsi(new BoardSerializer(board).render());

        assertEquals("renders all empty tiles correctly", expectedRenderedTiles, actualRendered);
    }

    @Test
    public void renderTilesWithPlayers() {
        String[] lines = {
                " X | 2 | O",
                "-----------",
                " 4 | 5 | 6",
                "-----------",
                " 7 | 8 | 9"
        };
        String expectedRenderedTiles = String.join("\n", lines);

        int boardSize = 3;
        Board board = new Board(boardSize)
                .makeMove(1, PlayerSymbol.X)
                .makeMove(3, PlayerSymbol.O);

        String actualRendered = stripAnsi(new BoardSerializer(board).render());

        assertEquals("renders tiles with player moves correctly", expectedRenderedTiles, actualRendered);
    }

    @Test
    public void fourByFour() {
        String[] lines = {
                " 1  | 2  | 3  | 4 ",
                "-------------------",
                " 5  | 6  | 7  | 8 ",
                "-------------------",
                " 9  | 10 | 11 | 12",
                "-------------------",
                " 13 | 14 | 15 | 16"
        };
        String expectedRenderedTiles = String.join("\n", lines);
        Board board = new Board(4);

        String actualRendered = stripAnsi(new BoardSerializer(board).render());

        assertEquals("renders tiles correctly for 4x4 board", expectedRenderedTiles, actualRendered);
    }

    @Test
    public void fourByFourWithMoves() {
        String[] lines = {
                " X  | 2  | 3  | 4 ",
                "-------------------",
                " 5  | 6  | 7  | 8 ",
                "-------------------",
                " 9  | 10 | 11 | 12",
                "-------------------",
                " 13 | 14 | O  | 16"
        };
        String expectedRenderedTiles = String.join("\n", lines);
        Board board = new Board(4)
                .makeMove(1, PlayerSymbol.X)
                .makeMove(15, PlayerSymbol.O);

        String actualRendered = stripAnsi(new BoardSerializer(board).render());

        assertEquals("renders tiles correctly for 4x4 board", expectedRenderedTiles, actualRendered);
    }

    private String stripAnsi(String input) {
        return input.replaceAll("\u001B\\[[\\d;]*[^\\d;]", "");
    }
}
