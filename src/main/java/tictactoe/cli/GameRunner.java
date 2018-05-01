package tictactoe.cli;

import tictactoe.core.Game;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;

public class GameRunner {

    public static void run() {
        initGame().run();
    }

    private static Game initGame() {
        Console console = new Console(System.in, System.out);
        console.greetUser();
        console.showInstructions();
        Players players = console.requestPlayers();
        return new Game(3, console, players);
    }

}
