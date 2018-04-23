package tictactoe.core;

import java.util.Arrays;

public class Board {

    private final Player[] tiles;
    private final int boardSize;
    private final int[][] winningStates = generateWinningStates();

    public Board(int boardSize) {
        this.tiles = emptyTiles(boardSize);
        this.boardSize = boardSize;
    }

    public Board makeMove(int tile, Player player) {
        Player[] nextTiles = insertPlayerAt(tile, player);
        return new Board(nextTiles, this.boardSize);
    }

    public Player getTile(int tile) {
        return this.tiles[tile];
    }

    public Player[] getTiles() {
        return this.tiles;
    }

    public Player winner() {
        if (hasWon(Player.X)) {
            return Player.X;
        } else if (hasWon(Player.O)) {
            return Player.O;
        } else {
            return Player.Empty;
        }
    }

    public boolean isFull() {
        boolean tilesFull = false;
        for (Player tile : this.tiles)
            if (!tile.isEmpty())
                tilesFull = true;
        return tilesFull;
    }

    // Creates a new Board based on the existing board
    private Board(Player[] currentTiles, int boardSize) {
        this.tiles = currentTiles;
        this.boardSize = boardSize;
    }

    private Player[] insertPlayerAt(int tile, Player player) {
        Player[] newTiles = Arrays.copyOf(this.tiles, this.tiles.length);
        newTiles[tile] = player;
        return newTiles;
    }

    private Player[] emptyTiles(int boardSize) {
        Player[] tiles = new Player[(boardSize * boardSize)];
        Arrays.fill(tiles, Player.Empty);
        return tiles;
    }

    private boolean hasWon(Player player) {
        for (int[] st: this.winningStates) {
            boolean matchesWinningState = Arrays
                    .stream(st)
                    .allMatch(i -> getTile(i) == player);
            if (matchesWinningState) return true;
        }
        return false;
    }

    private int[][] generateWinningStates () {
        int[][] winStates = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };
        return winStates;
    }
}
