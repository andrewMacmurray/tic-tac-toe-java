package tictactoe;

import tictactoe.core.PlayerSymbol;
import tictactoe.game.controllers.GameController;
import tictactoe.game.controllers.GameTypeController;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        GameTypeController gameTypeController = new GameTypeController(System.in, System.out);
        GameController gameController = new GameController(System.out, 3, PlayerSymbol.X);

        gameController.clearScreen();
        gameTypeController.run();
        gameController.run(new Scanner(System.in));
    }
}
