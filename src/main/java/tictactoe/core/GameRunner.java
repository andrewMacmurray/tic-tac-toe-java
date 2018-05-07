package tictactoe.core;

import tictactoe.core.players.Players;

public class GameRunner {

    private final UI ui;

    public GameRunner(UI ui) {
        this.ui = ui;
    }

    public void run() {
        ui.clear();
        ui.greetUser();
        gameLoop();
    }

    private void gameLoop() {
        ui.showInstructions();
        Players players = ui.requestPlayers();
        int boardSize = ui.requestBoardSize();

        Game game = new Game(boardSize, ui, players);
        game.play();
        playAgain();
    }

    private void playAgain() {
        if (ui.requestPlayAgain()) {
            ui.clear();
            gameLoop();
        } else {
            ui.goodbye();
        }
    }

}
