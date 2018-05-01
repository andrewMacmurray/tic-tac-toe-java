package tictactoe.cli;

import tictactoe.core.types.PlayerSymbol;

import java.util.stream.Stream;

public class Messages {

    public static final String welcome = "Welcome to Tic Tac Toe!";
    public static final String unrecognised = "Sorry I didn't recognise that";
    public static final String draw = "It's a draw!";

    public static String enterNumbers(int boardSize) {
        return String.format("Enter a number from 1-%d", boardSize * boardSize);
    }

    public static String winner(PlayerSymbol playerSymbol) {
        return String.format("Player %s won!", playerSymbol.toString());
    }

    public static String alreadyTaken(int guess) {
        return String.format("%d is already taken! Try another tile", guess);
    }

    public static Stream<String> playerGuess(int guess, PlayerSymbol playerSymbol) {
        return Stream.of(
                playerMove(guess, playerSymbol),
                nextTurn(playerSymbol.getAlternate())
        );
    }

    private static String playerMove(int guess, PlayerSymbol playerSymbol) {
        return String.format("Player %s took tile %d", playerSymbol.toString(), guess);
    }

    private static String nextTurn(PlayerSymbol playerSymbol) {
        return String.format("Your turn Player %s", playerSymbol.toString());
    }

    public static Stream<String> gameTypeOptions() {
        return Stream.of(
                "Select a cli to play:",
                "-----------------------",
                "1. Human vs Human",
                "2. Human vs Computer",
                "3. Computer vs Computer",
                "-----------------------",
                "Enter 1, 2 or 3:"
        );
    }
}