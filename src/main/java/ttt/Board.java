package ttt;

import java.util.HashMap;
import java.util.Arrays;

public class Board {
    private final Integer boardScale = 3;
    private HashMap<Integer, Player> board = new HashMap();
    private final Integer[][] winningStates;

    public Board () {
        this.winningStates = generateWinningStates();
    }

    public Player getTile(Integer tile) {
        return this.board.get(tile);
    }

    public void takeTile(Integer tile, Player player) {
        board.put(tile, player);
    }

    public Player winner() {
        if (hasWon(Player.X)) {
            return Player.X;
        } else if (hasWon(Player.O)) {
            return Player.O;
        } else {
            return null;
        }
    }

    private boolean hasWon(Player player) {
       for (Integer[] st: this.winningStates) {
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

    private Integer[][] generateWinningStates () {
        Integer[][] winStates = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };
        return winStates;
    }
}
