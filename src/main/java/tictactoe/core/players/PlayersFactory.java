package tictactoe.core.players;

import tictactoe.core.UI;
import tictactoe.core.util.ThreadControl;
import tictactoe.core.util.Time;

import static tictactoe.core.players.PlayerSymbol.O;
import static tictactoe.core.players.PlayerSymbol.X;

public class PlayersFactory {

    public static int minOption = 1;
    public static int maxOption = 3;
    private static ThreadControl time = new Time();

    public static Players createPlayers(int option, UI ui) {
        switch (option) {
            case 1:
                return humanVsHuman(ui);
            case 2:
                return humanVsComputer(ui);
            default:
                return computerVsComputer();
        }
    }

    private static Players humanVsHuman(UI ui) {
        return new Players(
                new HumanPlayer(X, ui),
                new HumanPlayer(O, ui)
        );
    }

    private static Players humanVsComputer(UI ui) {
        return new Players(
                new HumanPlayer(X, ui),
                new UnbeatablePlayer(O)
        );
    }

    private static Players computerVsComputer() {
        return new Players(
                new UnbeatablePlayer(X),
                new UnbeatablePlayer(O)
        );
    }
}
