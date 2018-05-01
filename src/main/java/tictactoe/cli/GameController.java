package tictactoe.cli;

import tictactoe.core.types.GuessStatus;
import tictactoe.core.types.PlayerSymbol;
import tictactoe.core.types.GameStatus;
import tictactoe.core.Game;

import java.io.PrintStream;
import java.util.Scanner;

public class GameController {

    private Game game;
    private final PrintStream out;

    public GameController(PrintStream out, int boardSize, PlayerSymbol firstPlayer) {
        this.game = new Game(boardSize, firstPlayer);
        this.out = out;
    }

    public void run(Scanner scanner) {
        enterNumberInstructions();
        while (!isGameOver()) handleGuess(scanner.next());
        printTerminus();
    }

    public void handleGuess(String input) {
        try {
            int guess = Integer.parseInt(input);
            handleParsedGuess(guess);
        } catch (NumberFormatException err) {
            printUnrecognised();
        }
    }

    private void handleParsedGuess(int guess) {
        PlayerSymbol currentPlayer = this.game.getCurrentPlayer();

        this.game = game.evalMove(guess);
        printGuessResult(guess, currentPlayer);
    }

    public void enterNumberInstructions() {
        out.println(Messages.enterNumbers(game.getBoardSize()));
    }

    public void printBoard() {
        String boardString = BoardSerializer.render(game.getTiles(), game.getBoardSize());
        out.println(boardString);
    }

    public void printPlayerGuess(int guess, PlayerSymbol player) {
        Messages.playerGuess(guess, player)
                .forEach(out::println);
    }

    public void printTerminus() {
        if (isGameOver()) {
            out.println(status());
        }
    }

    public boolean isGameOver() {
        return game.getGameStatus().isGameOver();
    }

    public void clearScreen() {
        out.print("\033[H\033[2J");
        out.flush();
    }

    private void printGuessResult(int guess, PlayerSymbol player) {
        GuessStatus status = this.game.getGuessStatus();
        if (status == GuessStatus.Valid) {
            clearScreen();
            printValid(guess, player);
        } else if (status == GuessStatus.OutOfBounds) {
            enterNumberInstructions();
        } else {
            printAlreadyTaken(guess);
        }
    }

    private void printUnrecognised() {
        out.println(Messages.unrecognised);
    }

    private void printAlreadyTaken(int guess) {
        out.println(Messages.alreadyTaken(guess));
    }

    private void printValid(int guess, PlayerSymbol currentPlayer) {
        printBoard();
        printPlayerGuess(guess, currentPlayer);
    }

    private String status() {
        if (game.getGameStatus() == GameStatus.Win) {
            return renderWinner();
        } else {
            return Messages.draw;
        }
    }

    private String renderWinner() {
        return game
                .getWinner()
                .map(Messages::winner)
                .orElse("");
    }
}
