package tictactoe.core;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

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
        Predicate<Integer[]> isWinningState =
                st -> Arrays
                        .stream(st)
                        .allMatch(i -> board.getTile(i).isTakenBy(player));
        return Arrays
                .stream(winningStates())
                .anyMatch(isWinningState);
    }

    private static Integer[][] winningStates () {
        Integer[][] winStates = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                {1, 5, 9}, {3, 5, 7}
        };
        return winStates;
    }
}
