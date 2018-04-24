package tictactoe;

import tictactoe.core.Player;
import tictactoe.game.Controller;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(System.out, 3, Player.X);
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
