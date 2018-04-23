package tictactoe.game;

import tictactoe.core.Player;

import java.io.InputStream;
import java.io.PrintStream;

public class Controller {

    private Model model;
    private final View view = new View();
    private final PrintStream out;

    public Controller(PrintStream out, int boardSize, Player firstPlayer) {
        this.model = new Model(boardSize, firstPlayer);
        this.out   = out;
    }

    public void nextGuess(int guess) {
        this.model = model.makeMove(guess);
        printBoard();
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

    public boolean isGameOver() {
        return model.getStatus().isGameOver();
    }
}
