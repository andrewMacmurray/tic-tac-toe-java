package tictactoe.cli;

import tictactoe.core.GameRunner;

public class Main {

    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        new GameRunner(console).run();
    }

}
