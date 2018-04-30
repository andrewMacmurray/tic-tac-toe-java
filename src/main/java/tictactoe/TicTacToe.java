package tictactoe;

import tictactoe.core.PlayerSymbol;
import tictactoe.game.controllers.GameController;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController(System.out, 3, PlayerSymbol.X);
        controller.clearScreen();
        controller.greetUser();
        controller.printInstructions();

        while (!controller.isGameOver()) {
            String input = scanner.next();
            controller.handleGuess(input);
        }

        controller.printTerminus();
    }
}
