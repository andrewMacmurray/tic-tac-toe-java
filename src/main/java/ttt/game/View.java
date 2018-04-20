package ttt.game;

import ttt.core.Player;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class View {

    private static String divider = "---*---*---";
    private final String tileTakenMessage = "Tile already taken, try another tile";

    public String renderTile(Player player, int index) {
        Integer visibleIndex = index + 1;
        switch (player) {
            case X:  return "X";
            case O:  return "O";
            default: return visibleIndex.toString();
        }
    }

    private String renderTileWithPadding(Player player, int index, int boardScale) {
        String tile        = renderTile(player, index);
        boolean isEndOfRow = (index + 1) % boardScale == 0;
        boolean isLastTile = (index + 1) == (boardScale * boardScale);

        if (isLastTile) {
            return " " + tile + "\n";
        } else if (isEndOfRow) {
            return " " + tile + "\n" + this.divider + "\n";
        } else {
            return " " + tile + " |";
        }
    }

    public String renderBoard(Player[] boardTiles, int boardScale) {
        String rendered = "";
        return IntStream
                .range(0, boardTiles.length)
                .mapToObj(i -> renderTileWithPadding(boardTiles[i], i, boardScale))
                .collect(Collectors.joining(""));
    }

    public String getTileTakenMessage() {
        return this.tileTakenMessage;
    }
}

