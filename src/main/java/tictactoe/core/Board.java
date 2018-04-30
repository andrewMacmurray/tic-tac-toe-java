package tictactoe.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private final Map<Integer, Tile> tiles;
    private final int boardSize;

    public Board(int boardSize) {
        this.tiles = emptyTiles(boardSize);
        this.boardSize = boardSize;
    }

    public Board makeMove(int tile, PlayerSymbol player) {
        Map<Integer, Tile> nextTiles = insertPlayerAt(tile, player);
        return new Board(nextTiles, boardSize);
    }

    public Tile getTile(int tile) {
        return tiles.get(tile);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Map<Integer, Tile> getTiles() {
        return tiles;
    }

    public boolean isMoveAvailable(int tileIndex) {
        return tiles.get(tileIndex).isEmpty();
    }

    public boolean isOutOfBounds(int tileIndex) {
        int upperBound = boardSize * boardSize;
        return tileIndex < 1 || tileIndex > upperBound;
    }

    public Optional<PlayerSymbol> winner() {
        return Winner.getWinner(this);
    }

    public boolean isFull() {
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

    private Map<Integer, Tile> emptyTiles(int boardSize) {
        int lastTile = (boardSize * boardSize) + 1;
        return IntStream
                .range(1, lastTile)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), x -> new Tile()));
    }
}
