package tictactoe.core;

import tictactoe.core.players.Players;

public class GameRunner {

    private final UI ui;

    public GameRunner(UI ui) {
        this.ui = ui;
    }

    public void run() {
        ui.greetUser();
        gameLoop();
    }

    private void gameLoop() {
        ui.showInstructions();
        Players players = ui.requestPlayers();

        Game game = new Game(3, ui, players);
        game.play();
        playAgain();
    }

    private void playAgain() {
        if (ui.requestPlayAgain()) {
           gameLoop();
        } else {
            ui.goodbye();
        }
    }

}
