package tictactoe;

import java.util.Arrays;

public class Board {

    private final Player[] tiles;
    private final int boardSize;

    public Board(int boardSize) {
        this.tiles     = emptyTiles(boardSize);
        this.boardSize = boardSize;
    }

    public Board makeMove(int tile, Player player) {
        Player[] nextTiles = insertPlayerAt(tile, player);
        return new Board(nextTiles, this.boardSize);
    }

    public Player getTile(int tile) {
       return this.tiles[tile];
    }

    public Player[] tiles() {
       return this.tiles;
    }

    public boolean isFull() {
        boolean tilesFull = false;
        for (Player tile: this.tiles)
            if (!tile.isEmpty())
                tilesFull = true;
        return tilesFull;
    }

    private Board(Player[] currentTiles, int boardSize) {
        this.tiles     = currentTiles;
        this.boardSize = boardSize;
    }

    private Player[] insertPlayerAt(int tile, Player player) {
        Player[] newTiles = Arrays.copyOf(this.tiles, this.tiles.length);
        newTiles[tile]    = player;
        return newTiles;
    }

    private Player[] emptyTiles(int boardSize) {
       Player[] tiles = new Player[(boardSize * boardSize)];
       Arrays.fill(tiles, Player.Empty);
       return tiles;
    }
}
