package tictactoe.cli;

import tictactoe.core.players.PlayerSymbol;

import java.util.stream.Stream;

class Messages {

    static final String welcome = "Welcome to Tic Tac Toe!";
    static final String unrecognised = "Sorry I didn't recognise that";
    static final String draw = "It's a draw!";
    static final String goodbye = "Ok bye!";
    static final String playAgain = "Play again? Enter Y or N";

    static String enterNumbers(int boardSize, PlayerSymbol playerSymbol) {
        return String.format(
                "Ok player %s, Enter a number from 1-%d",
                playerSymbol.toString(),
                boardSize * boardSize
        );
    }

    static String winner(PlayerSymbol playerSymbol) {
        return String.format("Player %s won!", playerSymbol.toString());
    }

    static String alreadyTaken(int guess) {
        return String.format("%d is already taken! Try another tile", guess);
    }

    static String outOfBounds(int guess) {
        return String.format("%d is not on the board! Try another tile", guess);
    }

    static Stream<String> playerGuess(int guess, PlayerSymbol playerSymbol) {
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

    static Stream<String> gameTypeOptions() {
        return Stream.of(
                "Select a game to play:",
                "-----------------------",
                "1. Human vs Human",
                "2. Human vs Computer",
                "3. Computer vs Computer",
                "-----------------------",
                "Enter 1, 2 or 3:"
        );
    }
}