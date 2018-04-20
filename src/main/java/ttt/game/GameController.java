package ttt.game;

import java.util.Scanner;

import ttt.core.Board;
import ttt.core.Player;

public class GameController {

    private Scanner scanner  = new Scanner(System.in);
    private static View view = new View();
    private Model model;

    public GameController(int boardScale) {
        this.model = new Model(boardScale, Player.X);
    }

    public void run() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Enter a guess from 1-9");
        gameLoop();
    }

    public void gameLoop() {
        if (model.winner() != Player.Empty) {
            System.out.println(model.winner().toString() + " won!");
        } else {
            int nextGuess = this.scanner.nextInt() - 1;
            model.update(nextGuess);
            printBoard();
            gameLoop();
        }
    }

    private void printBoard() {
        String m = view.renderBoard(model.getTiles(), model.getBoardScale());
        System.out.println(m);
    }
}
