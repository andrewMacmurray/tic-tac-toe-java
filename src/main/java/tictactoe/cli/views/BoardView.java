package tictactoe.cli.views;

import tictactoe.core.Tile;

import java.util.Map;
import java.util.stream.Collectors;

public class BoardView {

    public static String divider = "---*---*---";

    public static String renderTiles(Map<Integer, Tile> tiles, int boardSize) {
        return tiles
                .entrySet()
                .stream()
                .map(v -> renderTileWithPadding(v.getValue(), v.getKey(), boardSize))
                .collect(Collectors.joining(""));
    }

    private static String renderTileWithPadding(Tile myTile, int index, int boardSize) {
        String tile = myTile.toString(index);
        boolean isEndOfRow = index % boardSize == 0;
        boolean isLastTile = index == (boardSize * boardSize);

        if (isLastTile) {
            return " " + tile;
        } else if (isEndOfRow) {
            return " " + tile + "\n" + divider + "\n";
        } else {
            return " " + tile + " |";
        }
    }
}
