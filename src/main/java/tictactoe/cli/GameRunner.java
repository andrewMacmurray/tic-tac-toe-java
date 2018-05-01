package tictactoe.cli;

import tictactoe.core.Game;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;

public class GameRunner {

    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        Players players = console.requestPlayers();
        Game game = new Game(3, console, players);
        game.run();
    }
}
