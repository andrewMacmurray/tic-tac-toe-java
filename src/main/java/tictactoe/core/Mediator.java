package tictactoe.core;

import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;

public abstract class Mediator {

    private final Game game = new Game(this);

    public abstract void startGame();

    public abstract void gameInstructions(Board board, PlayerSymbol playerSymbol);

    public abstract void requestMoveFromUI(Board board, PlayerSymbol playerSymbol);

    public void receiveMove(int move) {
        game.playMove(move);
    }

    public abstract void moveSummary(int move, Board board, PlayerSymbol playerSymbol);

    public abstract void requestBoardSizeFromUI();

    public void receiveBoardSize(int boardSize) {
        game.setBoardSize(boardSize);
    }

    public abstract void requestPlayersFromUI();

    public void receivePlayers(Players players) {
        game.setPlayers(players);
    }

    public abstract void currentBoard(Board board);

    public abstract void announceWin(PlayerSymbol playerSymbol, Board board);

    public abstract void announceDraw(Board board);

}