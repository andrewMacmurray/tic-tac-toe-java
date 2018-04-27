package tictactoe.game;

import tictactoe.core.Player;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class View {

    public static String divider = "---*---*---";

    public static String renderTiles(HashMap<Integer, Player> tiles, int boardSize) {
        return tiles
                .entrySet()
                .stream()
                .map(v -> renderTileWithPadding(v.getValue(), v.getKey(), boardSize))
                .collect(Collectors.joining(""));
    }

    private static String renderTileWithPadding(Player player, int index, int boardSize) {
        String tile = renderTile(player, index);
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

    private static String renderTile(Player player, int index) {
        switch (player) {
            case X:
                return "X";
            case O:
                return "O";
            default:
                return String.valueOf(index);
        }
    }
}
