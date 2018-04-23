package tictactoe.game;

import tictactoe.core.Player;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class View {

    private static String divider = "---*---*---";
    private static int visibleIndexOffset = 1;

    public String renderTiles(Player[] tiles, int boardSize) {
        return IntStream
                .range(0, tiles.length)
                .mapToObj(i -> renderTileWithPadding(tiles[i], i, boardSize))
                .collect(Collectors.joining(""));
    }

    private String renderTileWithPadding(Player player, int index, int boardSize) {
        String tile = renderTile(player, index);
        boolean isEndOfRow = (index + visibleIndexOffset) % boardSize == 0;
        boolean isLastTile = (index + visibleIndexOffset) == (boardSize * boardSize);

        if (isLastTile) {
            return " " + tile;
        } else if (isEndOfRow) {
            return " " + tile + "\n" + divider + "\n";
        } else {
            return " " + tile + " |";
        }
    }

    private String renderTile(Player player, int index) {
        int visibleIndex = index + visibleIndexOffset;
        switch (player) {
            case X:
                return "X";
            case O:
                return "O";
            default:
                return String.valueOf(visibleIndex);
        }
    }
}
