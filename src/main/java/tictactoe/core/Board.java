package tictactoe.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Board {

    private final HashMap<Integer, Player> tiles;
    private final int boardSize;
    private final int[][] winningStates = generateWinningStates();

    public Board(int boardSize) {
        this.tiles = emptyTiles(boardSize);
        this.boardSize = boardSize;
    }

    public Board makeMove(int tile, Player player) {
        HashMap<Integer, Player> nextTiles = insertPlayerAt(tile, player);
        return new Board(nextTiles, this.boardSize);
    }

    public Player getTile(int tile) {
        return this.tiles.get(tile);
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public HashMap<Integer, Player> getTiles() {
        return this.tiles;
    }

    public boolean isMoveAvailable(int tileIndex) {
        return this.tiles.get(tileIndex).isEmpty();
    }

    public boolean isOutOfBounds(int tileIndex) {
        int upperBound = this.boardSize * this.boardSize;
        return tileIndex < 1 || tileIndex > upperBound;
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
        boolean tilesFull = true;
        for (Map.Entry<Integer, Player> tile : this.tiles.entrySet())
            if (tile.getValue().isEmpty())
                tilesFull = false;
        return tilesFull;
    }

    // Creates a new Board based on the existing board
    private Board(HashMap<Integer, Player> currentTiles, int boardSize) {
        this.tiles = currentTiles;
        this.boardSize = boardSize;
    }

    private HashMap<Integer, Player> insertPlayerAt(int tile, Player player) {
        HashMap<Integer, Player> newTiles = new HashMap<>(this.tiles);
        newTiles.put(tile, player);
        return newTiles;
    }

    private HashMap<Integer, Player> emptyTiles(int boardSize) {
        Integer lastTile = (Integer) (boardSize * boardSize);
        HashMap<Integer, Player> tiles = new HashMap<>();
        for (Integer i = 1; i <= lastTile; i++) {
            tiles.put(i, Player.Empty);
        }
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
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                {1, 5, 9}, {3, 5, 7}
        };
        return winStates;
    }
}
