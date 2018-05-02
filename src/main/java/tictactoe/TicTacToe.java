package tictactoe;

import tictactoe.cli.Console;
import tictactoe.core.GameRunner;

public class TicTacToe {

    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        new GameRunner(console).run();
    }

}
