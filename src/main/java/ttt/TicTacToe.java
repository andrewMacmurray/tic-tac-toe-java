package ttt;

import ttt.game.GameController;

public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController = new GameController(3);
        gameController.run();
    }
}
