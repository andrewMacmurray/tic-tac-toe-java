package tictactoe.cli;

import tictactoe.core.Board;
import tictactoe.core.Tile;

import java.util.stream.Collectors;

class BoardSerializer {

    private int boardSize;
    private Board board;

    public BoardSerializer(Board board) {
        this.board = board;
        this.boardSize = board.getBoardSize();
    }

    public String render() {
        return board
                .tilesStream()
                .map(v -> renderTileWithPadding(v.getValue(), v.getKey()))
                .collect(Collectors.joining(""));
    }

    private String renderTileWithPadding(Tile tile, int index) {
        String tileString = colorize(tile.toString(index));
        boolean isEndOfRow = index % boardSize == 0;
        boolean isLastTile = index == (boardSize * boardSize);

        if (isLastTile) {
            return lastTile(tileString);
        } else if (isEndOfRow) {
            return endOfRow(tileString);
        } else {
            return midRow(tileString);
        }
    }

    private String divider() {
        int n = boardSize * boardSize + (boardSize - 1);
        return repeat("-", n);
    }

    private String lastTile(String tileString) {
        return String.format(" %s", tileString);
    }

    private String endOfRow(String tileString) {
        return String.format(" " + digitTileSpacing() + "\n%s\n", tileString, divider());
    }

    private String midRow(String tileString) {
        return String.format(" " + digitTileSpacing() + " |", tileString);
    }

    private String digitTileSpacing() {
        if (boardSize > 3) {
            return "%-2s";
        } else {
            return "%s";
        }
    }

    private String colorize(String tile) {
        switch (tile) {
            case "X":
                return coloredTileSpacing(Colors.toLightBlue(tile));
            case "O":
                return coloredTileSpacing(Colors.toGreen(tile));
            default:
                return tile;
        }
    }

    private String coloredTileSpacing(String tileString) {
        if (boardSize > 3) {
            return String.format("%s ", tileString);
        } else {
            return tileString;
        }
    }

    private String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
}
