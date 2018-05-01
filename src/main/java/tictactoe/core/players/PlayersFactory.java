package tictactoe.core.players;

import tictactoe.core.ui.UIRequest;

import static tictactoe.core.players.PlayerSymbol.O;
import static tictactoe.core.players.PlayerSymbol.X;

public class PlayersFactory {

    public static int minOption = 1;
    public static int maxOption = 3;

    public static Players createPlayers(int option, UIRequest ui) {
        switch (option) {
            case 1:
                return humanVsHuman(ui);
            case 2:
                return humanVsComputer(ui);
            case 3:
                return computerVsComputer();
            default:
                return humanVsHuman(ui);
        }
    }

    private static Players humanVsHuman(UIRequest ui) {
        return new Players(
                new HumanPlayer(X, ui),
                new HumanPlayer(O, ui)
        );
    }

    private static Players humanVsComputer(UIRequest ui) {
        return new Players(
                new HumanPlayer(X, ui),
                new ComputerPlayer(O)
        );
    }

    private static Players computerVsComputer() {
        return new Players(
                new ComputerPlayer(X),
                new ComputerPlayer(O)
        );
    }
}
