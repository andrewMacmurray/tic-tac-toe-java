package tictactoe.game;

import tictactoe.core.Player;
import tictactoe.core.Status;
import tictactoe.core.guess.GuessStatus;
import tictactoe.core.guess.GuessValidator;

import java.io.InputStream;
import java.io.PrintStream;

public class Controller {

    private Model model;
    private GuessValidator guessValidator;
    private final View view = new View();
    private final PrintStream out;

    public Controller(PrintStream out, int boardSize, Player firstPlayer) {
        this.model = new Model(boardSize, firstPlayer);
        this.guessValidator = new GuessValidator(1, boardSize * boardSize);
        this.out = out;
    }

    public void handleGuess(String input) {
        this.guessValidator.validate(input);
        if (this.guessValidator.isValid()) {
            nextGuess(this.guessValidator.getValue());
        } else {
            handleInvalidGuess();
        }
    }

    public void nextGuess(int guess) {
        Player currentPlayer = this.model.getCurrentPlayer();
        int adjustedGuess = adjustGuessIndex(guess);

        this.model = model.makeMove(adjustedGuess);
        printBoard();
        printPlayerGuess(guess, currentPlayer);
    }

    private void handleInvalidGuess() {
        GuessStatus status = this.guessValidator.getStatus();
        if (status == GuessStatus.OutOfBounds) {
            out.println("Please enter a number from 1-9");
        } else if (status == GuessStatus.Unrecognized) {
            out.println("Sorry I didn't recognise that");
        }
    }

    public void greetUser() {
        out.println("Welcome to Tic Tac Toe!");
    }

    public void printInstructions() {
        out.println("Enter a number from 1-9");
    }

    public void printBoard() {
        String boardString = view.renderTiles(model.getTiles(), model.getBoardSize());
        out.println(boardString);
    }

    public void printPlayerGuess(int guess, Player player) {
        out.println("Player " + player.toString() + " took tile " + String.valueOf(guess));
        out.println("Your turn Player " + player.getAlternate().toString());
    }

    public void printStatus() {
        if (isGameOver()) {
            out.println(status());
        }
    }

    public boolean isGameOver() {
        return model.getStatus().isGameOver();
    }

    public void clearScreen() {
        out.print("\033[H\033[2J");
        out.flush();
    }

    private String status() {
        if (model.getStatus() == Status.Win) {
            return "Player " + model.getWinner().toString() + " won!";
        } else {
            return "It's a draw!";
        }
    }

    private int adjustGuessIndex(int n) {
        return n - 1;
    }
}
