package tictactoe.game.controllers;

import tictactoe.core.GuessStatus;
import tictactoe.core.PlayerSymbol;
import tictactoe.core.GameStatus;
import tictactoe.game.Model;
import tictactoe.game.views.BoardView;
import tictactoe.game.views.Messages;

import java.io.PrintStream;
import java.util.Scanner;

public class GameController {

    private Model model;
    private final PrintStream out;

    public GameController(PrintStream out, int boardSize, PlayerSymbol firstPlayer) {
        this.model = new Model(boardSize, firstPlayer);
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
        PlayerSymbol currentPlayer = this.model.getCurrentPlayer();

        this.model = model.evalMove(guess);
        printGuessResult(guess, currentPlayer);
    }

    public void enterNumberInstructions() {
        out.println(Messages.enterNumbers(model.getBoardSize()));
    }

    public void printBoard() {
        String boardString = BoardView.renderTiles(model.getTiles(), model.getBoardSize());
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
        return model.getGameStatus().isGameOver();
    }

    public void clearScreen() {
        out.print("\033[H\033[2J");
        out.flush();
    }

    private void printGuessResult(int guess, PlayerSymbol player) {
        GuessStatus status = this.model.getGuessStatus();
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
        if (model.getGameStatus() == GameStatus.Win) {
            return renderWinner();
        } else {
            return Messages.draw;
        }
    }

    private String renderWinner() {
        return model
                .getWinner()
                .map(Messages::winner)
                .orElse("");
    }
}
