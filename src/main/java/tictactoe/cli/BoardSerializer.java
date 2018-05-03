package tictactoe.cli;

import tictactoe.core.Board;
import tictactoe.core.Tile;

import java.util.stream.Collectors;

class BoardSerializer {

    static String divider = "-----------";

    static String render(Board board) {
        return board
                .tilesStream()
                .map(v -> renderTileWithPadding(v.getValue(), v.getKey(), board.getBoardSize()))
                .collect(Collectors.joining(""));

    }

    private static String renderTileWithPadding(Tile tile, int index, int boardSize) {
        String tileString = colorize(tile.toString(index));
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

    private static String colorize(String tile) {
        switch (tile) {
            case "X":
                return Colors.toLightBlue(tile);
            case "O":
                return Colors.toGreen(tile);
            default:
                return tile;
        }
    }
}
