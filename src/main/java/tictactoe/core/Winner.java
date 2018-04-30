package tictactoe.core;

import java.util.Arrays;
import java.util.Optional;

public class Winner {

    public static Optional<PlayerSymbol> getWinner(Board board) {
        if (hasWon(PlayerSymbol.X, board)) {
            return Optional.of(PlayerSymbol.X);
        } else if (hasWon(PlayerSymbol.O, board)) {
            return Optional.of(PlayerSymbol.O);
        } else {
            return Optional.empty();
        }
    }

    private static boolean hasWon(PlayerSymbol player, Board board) {
        for (int[] st: winningStates()) {
            boolean matchesWinningState = Arrays
                    .stream(st)
                    .allMatch(i -> board.getTile(i).isTakenBy(player));
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
