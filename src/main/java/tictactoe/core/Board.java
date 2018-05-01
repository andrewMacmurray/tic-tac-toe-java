package tictactoe.core;

import tictactoe.core.types.PlayerSymbol;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private final Map<Integer, Tile> tiles;
    private final int boardSize;

    public Board(int boardSize) {
        this.tiles = createEmptyTiles(boardSize);
        this.boardSize = boardSize;
    }

    public Board makeMove(int tile, PlayerSymbol player) {
        Map<Integer, Tile> nextTiles = insertPlayerAt(tile, player);
        return new Board(nextTiles, boardSize);
    }

    public Map<Integer, Tile> getTiles() {
        return tiles;
    }

    Tile getTile(int tile) {
        return tiles.get(tile);
    }

    int getBoardSize() {
        return boardSize;
    }

    boolean isMoveAvailable(int tileIndex) {
        return tiles.get(tileIndex).isEmpty();
    }

    boolean isMoveOutOfBounds(int tileIndex) {
        int upperBound = boardSize * boardSize;
        return tileIndex < 1 || tileIndex > upperBound;
    }

    boolean isFull() {
        return tiles
                .entrySet()
                .stream()
                .noneMatch(t -> t.getValue().isEmpty());
    }

    // Creates a new Board based on the existing board
    private Board(Map<Integer, Tile> currentTiles, int boardSize) {
        this.tiles = currentTiles;
        this.boardSize = boardSize;
    }

    private Map<Integer, Tile> insertPlayerAt(int tile, PlayerSymbol player) {
        Map<Integer, Tile> newTiles = new HashMap<>(this.tiles);
        newTiles.put(tile, new Tile(player));
        return newTiles;
    }

    private static Map<Integer, Tile> createEmptyTiles(int boardSize) {
        int lastTile = (boardSize * boardSize) + 1;
        return IntStream
                .range(1, lastTile)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), x -> new Tile()));
    }

    boolean hasWinner() {
        return hasWon(PlayerSymbol.X) || hasWon(PlayerSymbol.O);
    }

    Optional<PlayerSymbol> getWinner() {
        if (hasWon(PlayerSymbol.X)) {
            return Optional.of(PlayerSymbol.X);
        } else if (hasWon(PlayerSymbol.O)) {
            return Optional.of(PlayerSymbol.O);
        } else {
            return Optional.empty();
        }
    }

    private boolean hasWon(PlayerSymbol player) {
        Predicate<Integer[]> isWinningState =
                st -> Arrays
                        .stream(st)
                        .allMatch(i -> getTile(i).isTakenBy(player));
        return Arrays
                .stream(winningStates())
                .anyMatch(isWinningState);
    }

    private static Integer[][] winningStates() {
        return new Integer[][]  {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                {1, 5, 9}, {3, 5, 7}
        };
    }
}
