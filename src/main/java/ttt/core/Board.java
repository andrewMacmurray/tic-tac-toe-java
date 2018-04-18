package ttt.core;

import java.util.Arrays;

public class Board {
    private final int boardScale = 3;
    private Player[] tiles;
    private final int[][] winningStates;

    public Board () {
        this.winningStates = generateWinningStates();
        this.tiles = fillBoard();
    }

    public Player[] getTiles() {
        return this.tiles;
    }

    public Player getTile(int tileIndex) {
        return this.tiles[tileIndex];
    }

    public void takeTile(int tileIndex, Player player) {
        this.tiles[tileIndex] = player;
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

    private boolean hasWon(Player player) {
       for (int[] st: this.winningStates) {
           boolean matchesWinningState = Arrays
                   .stream(st)
                   .allMatch(i -> getTile(i) == player);
           if (matchesWinningState) return true;
       }
       return false;
    }

    private Player[] fillBoard() {
        Player[] emptyBoard = new Player[(this.boardScale * this.boardScale)];
        Arrays.fill(emptyBoard, Player.Empty);
        return emptyBoard;
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
