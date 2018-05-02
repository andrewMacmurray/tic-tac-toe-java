package tictactoe.cli;

import tictactoe.core.Game;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;

import java.io.InputStream;
import java.io.PrintStream;

public class GameRunner {

    private final InputStream in;
    private final PrintStream print;

    public GameRunner(InputStream in, PrintStream print) {
        this.in = in;
        this.print = print;
    }

    public void run() {
        initGame().run();
    }

    private Game initGame() {
        Console console = new Console(in, print);
        console.greetUser();
        console.showInstructions();
        Players players = console.requestPlayers();
        return new Game(3, console, players);
    }

}
