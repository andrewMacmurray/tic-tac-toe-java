package tictactoe.game;

import tictactoe.core.Player;
import tictactoe.core.Status;

import java.io.InputStream;
import java.io.PrintStream;

public class Controller {

    private Model model;
    private final View view = new View();
    private final PrintStream out;

    public Controller(PrintStream out, int boardSize, Player firstPlayer) {
        this.model = new Model(boardSize, firstPlayer);
        this.out = out;
    }

    public void nextGuess(int guess) {
        Player currentPlayer = this.model.getCurrentPlayer();
        int adjustedGuess = adjustGuessIndex(guess);

        this.model = model.makeMove(adjustedGuess);
        printBoard();
        printPlayerGuess(guess, currentPlayer);
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
