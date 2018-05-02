package tictactoe.mocks;

import tictactoe.core.Board;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;
import tictactoe.core.ui.UIRequest;

public class MockUI implements UIRequest {

    private int currentMove;

    public MockUI(int firstMove) {
        this.currentMove = firstMove;
    }

    @Override
    public Integer requestMove(Board board, PlayerSymbol playerSymbol) {
        return currentMove;
    }

    @Override
    public Players requestPlayers() {
        return null;
    }

}
