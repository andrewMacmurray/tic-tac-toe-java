package tictactoe.core;

import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;

public abstract class Mediator {

    private final Game game = new Game(this);

    public abstract void requestPlayersFromUI();

    public abstract void requestBoardSizeFromUI();

    public abstract void requestMoveFromUI(Board board, PlayerSymbol playerSymbol);

    public void receivePlayers(Players players) {
        game.receivePlayers(players);
    }

    public void receiveBoardSize(int boardSize) {
        game.receiveBoardSize(boardSize);
    }

    public void receiveMove(int move) {
        game.receiveMove(move);
    }

    public abstract void moveSummary(int move, Board board, PlayerSymbol playerSymbol);

    public abstract void gameInstructions(Board board, PlayerSymbol playerSymbol);

    public abstract void currentBoard(Board board);

    public abstract void announceWin(PlayerSymbol playerSymbol, Board board);

    public abstract void announceDraw(Board board);

}