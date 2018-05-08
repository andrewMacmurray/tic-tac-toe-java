package tictactoe.core;

import tictactoe.core.players.PlayerSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private final Map<Integer, Tile> tiles;
    private final int boardSize;
    private final List<List<Integer>> winStates;

    public Board(int boardSize) {
        this.tiles = createEmptyTiles(boardSize);
        this.boardSize = boardSize;
        this.winStates = new WinStates(boardSize).generate();
    }

    public Board makeMove(int tile, PlayerSymbol player) {
        Map<Integer, Tile> nextTiles = insertPlayerAt(tile, player);
        return new Board(nextTiles, this);
    }

    Tile getTile(int tile) {
        return tiles.get(tile);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public ArrayList<Integer> allAvailableMoves() {
        return tilesStream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isMoveAvailable(int tileIndex) {
        return tiles.get(tileIndex).isEmpty();
    }

    public boolean isMoveOutOfBounds(int tileIndex) {
        int upperBound = boardSize * boardSize;
        return tileIndex < 1 || tileIndex > upperBound;
    }

    public boolean isValidMove(int tileIndex) {
        return !isMoveOutOfBounds(tileIndex) && isMoveAvailable(tileIndex);
    }

    public boolean isTerminal() {
        return xWin() || oWin() || isFull();
    }

    boolean isFull() {
        return tilesStream().noneMatch(t -> t.getValue().isEmpty());
    }

    // Creates a new Board based on the existing board
    private Board(Map<Integer, Tile> newTiles, Board currentBoard) {
        this.tiles = newTiles;
        this.boardSize = currentBoard.boardSize;
        this.winStates = currentBoard.winStates;
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

    public Stream<Map.Entry<Integer, Tile>> tilesStream() {
        return tiles.entrySet().stream();
    }

    boolean xWin() {
        return hasWon(PlayerSymbol.X);
    }

    boolean oWin() {
        return hasWon(PlayerSymbol.O);
    }

    public boolean hasWon(PlayerSymbol player) {
        Predicate<List<Integer>> isWinningState =
                winState -> winState
                        .stream()
                        .allMatch(i -> getTile(i).isTakenBy(player));

        return winStates
                .stream()
                .anyMatch(isWinningState);
    }

}
