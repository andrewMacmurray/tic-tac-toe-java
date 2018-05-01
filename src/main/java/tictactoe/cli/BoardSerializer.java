package tictactoe.cli;

import tictactoe.core.Tile;

import java.util.Map;
import java.util.stream.Collectors;

public class BoardSerializer {

    public static String divider = "---*---*---";

    public static String render(Map<Integer, Tile> tiles, int boardSize) {
        return tiles
                .entrySet()
                .stream()
                .map(v -> renderTileWithPadding(v.getValue(), v.getKey(), boardSize))
                .collect(Collectors.joining(""));

    }

    private static String renderTileWithPadding(Tile tile, int index, int boardSize) {
        String tileString = tile.toString(index);
        boolean isEndOfRow = index % boardSize == 0;
        boolean isLastTile = index == (boardSize * boardSize);

        if (isLastTile) {
            return " " + tileString;
        } else if (isEndOfRow) {
            return endOfRow(tileString);
        } else {
            return midRow(tileString);
        }
    }

    private static String endOfRow(String tileString) {
        return String.format(" %s\n%s\n", tileString, divider);
    }

    private static String midRow(String tileString) {
        return String.format(" %s |", tileString);
    }
}
