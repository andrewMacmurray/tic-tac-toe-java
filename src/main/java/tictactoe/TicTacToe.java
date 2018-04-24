package tictactoe;

import tictactoe.core.Player;
import tictactoe.game.Controller;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(System.out, 3, Player.X);
        controller.greetUser();
        controller.printInstructions();

        while (!controller.isGameOver()) {
            int guess = scanner.nextInt();
            controller.clearScreen();
            controller.nextGuess(guess);
        }

        controller.printStatus();
    }
}
