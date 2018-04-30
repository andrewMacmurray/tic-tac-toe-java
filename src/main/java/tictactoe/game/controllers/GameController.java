package tictactoe.game.controllers;

import tictactoe.core.GuessStatus;
import tictactoe.core.PlayerSymbol;
import tictactoe.core.GameStatus;
import tictactoe.game.Model;
import tictactoe.game.View;

import java.io.PrintStream;

public class GameController {

    private Model model;
    private final PrintStream out;

    public GameController(PrintStream out, int boardSize, PlayerSymbol firstPlayer) {
        this.model = new Model(boardSize, firstPlayer);
        this.out = out;
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

    public void greetUser() {
        out.println("Welcome to Tic Tac Toe!");
    }

    public void printInstructions() {
        out.println("Enter a number from 1-9");
    }

    public void printBoard() {
        String boardString = View.renderTiles(model.getTiles(), model.getBoardSize());
        out.println(boardString);
    }

    public void printPlayerGuess(int guess, PlayerSymbol player) {
        out.println("PlayerSymbol " + player.toString() + " took tile " + String.valueOf(guess));
        out.println("Your turn PlayerSymbol " + player.getAlternate().toString());
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
            printInstructions();
        } else {
            printAlreadyTaken(guess);
        }
    }

    private void printUnrecognised() {
        out.println("Sorry I didn't recognise that");
    }

    private void printAlreadyTaken(int guess) {
        out.println(String.valueOf(guess) + " is already taken! Try another tile");
    }

    private void printValid(int guess, PlayerSymbol currentPlayer) {
        printBoard();
        printPlayerGuess(guess, currentPlayer);
    }

    private String status() {
        if (model.getGameStatus() == GameStatus.Win) {
            return "PlayerSymbol " + renderWinner() + " won!";
        } else {
            return "It's a draw!";
        }
    }

    private String renderWinner() {
        return model
                .getWinner()
                .map(PlayerSymbol::toString)
                .orElse("");
    }
}
