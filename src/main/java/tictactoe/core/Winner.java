package tictactoe.core;

import java.util.Arrays;

public class Winner {

    public static Player getWinner(Board board) {
        if (hasWon(Player.X, board)) {
            return Player.X;
        } else if (hasWon(Player.O, board)) {
            return Player.O;
        } else {
            return Player.Empty;
        }
    }

    private static boolean hasWon(Player player, Board board) {
        for (int[] st: winningStates()) {
            boolean matchesWinningState = Arrays
                    .stream(st)
                    .allMatch(i -> board.getTile(i) == player);
            if (matchesWinningState) return true;
        }
        return false;
    }

    private static int[][] winningStates () {
        int[][] winStates = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                {1, 5, 9}, {3, 5, 7}
        };
        return winStates;
    }
}
