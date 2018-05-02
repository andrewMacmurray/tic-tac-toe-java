package tictactoe.mocks;

import tictactoe.core.Board;
import tictactoe.core.UI;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;

public class MockUI implements UI {

    private int currentMove;

    public MockUI(int firstMove) {
        this.currentMove = firstMove;
    }

    @Override
    public int requestMove(Board board, PlayerSymbol playerSymbol) {
        return currentMove;
    }

    @Override
    public boolean requestPlayAgain() {
        return false;
    }

    @Override
    public void showBoard(Board board) {

    }

    @Override
    public void showWin(PlayerSymbol playerSymbol) {

    }

    @Override
    public void showDraw() {

    }

    @Override
    public void showMoveSummary(Integer move, Board board, PlayerSymbol playerSymbol) {

    }

    @Override
    public void showMoveInstructions(Integer boardSize, PlayerSymbol playerSymbol) {

    }

    @Override
    public void greetUser() {

    }

    @Override
    public void goodbye() {

    }

    @Override
    public void showInstructions() {

    }

    @Override
    public void clear() {

    }

    @Override
    public Players requestPlayers() {
        return null;
    }

}
