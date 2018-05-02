package tictactoe;

import tictactoe.cli.GameRunner;

public class TicTacToe {

    public static void main(String[] args) {
        new GameRunner(System.in, System.out).run();
    }

}
