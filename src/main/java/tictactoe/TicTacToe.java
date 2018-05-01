package tictactoe;

import tictactoe.core.types.PlayerSymbol;
import tictactoe.cli.GameController;
import tictactoe.cli.GameTypeController;

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
