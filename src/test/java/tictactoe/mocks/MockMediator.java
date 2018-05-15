package tictactoe.mocks;

import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.players.PlayerSymbol;

public class MockMediator extends Mediator {

    private String mediatorLog = "";
    private int currentMove;
    private Board currentBoard;

    public String getLog() {
        return mediatorLog;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    @Override
    public void runGame() {
        mediatorLog = "play game";
    }

    @Override
    public void gameInstructions(Board board, PlayerSymbol playerSymbol) {
        mediatorLog = "game instructions";
    }

    @Override
    public void requestMoveFromUI(Board board, PlayerSymbol playerSymbol) {
        mediatorLog = "request move";
    }

    @Override
    public void receiveMove(int move) {
        mediatorLog = "receive move";
        currentMove = move;
    }

    @Override
    public void moveSummary(int move, Board board, PlayerSymbol playerSymbol) {
        currentMove = move;
        mediatorLog = "player " + playerSymbol.toString() + " played move " + move;
    }

    @Override
    public void requestBoardSizeFromUI() {
        mediatorLog = "request board size";
    }

    @Override
    public void requestPlayersFromUI() {
        mediatorLog = "request players";
    }

    @Override
    public void currentBoard(Board board) {
        currentBoard = board;
    }

    @Override
    public void announceWin(PlayerSymbol playerSymbol, Board board) {
        mediatorLog = playerSymbol.toString() + " won";
    }

    @Override
    public void announceDraw(Board board) {
        mediatorLog = "it's a draw";
    }
}
