package tictactoe.core.players;

import tictactoe.core.Mediator;

import static tictactoe.core.players.PlayerSymbol.O;
import static tictactoe.core.players.PlayerSymbol.X;

public class PlayersFactory {

    public static int minOption = 1;
    public static int maxOption = 3;
    private Time time;
    private Mediator mediator;

    public PlayersFactory(Mediator mediator, Time time) {
        this.mediator = mediator;
        this.time = time;
    }

    public Players createPlayers(int option) {
        switch (option) {
            case 1:
                return humanVsHuman();
            case 2:
                return humanVsComputer();
            default:
                return computerVsComputer();
        }
    }

    private Players humanVsHuman() {
        return new Players(
                new HumanPlayer(X, mediator::requestMoveFromUI),
                new HumanPlayer(O, mediator::requestMoveFromUI)
        );
    }

    private Players humanVsComputer() {
        return new Players(
                new HumanPlayer(X, mediator::requestMoveFromUI),
                new UnbeatablePlayer(O, time, mediator::receiveMove)
        );
    }

    private Players computerVsComputer() {
        return new Players(
                new UnbeatablePlayer(X, time, mediator::receiveMove),
                new UnbeatablePlayer(O, time, mediator::receiveMove)
        );
    }
}
