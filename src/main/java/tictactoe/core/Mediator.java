package tictactoe.core;

import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;

public abstract class Mediator {

    private final Game game = new Game(this);

    public abstract void playGame();

    public abstract void requestMove(Board board, PlayerSymbol playerSymbol);

    public void receiveMove(int move) {
        game.playMove(move);
    }

    public abstract void moveSummary(int move, Board board, PlayerSymbol playerSymbol);

    public abstract void requestBoardSize();

    public void receiveBoardSize(int boardSize) {
        game.setBoardSize(boardSize);
    }

    public abstract void requestPlayers();

    public void receivePlayers(Players players) {
        game.setPlayers(players);
    };

    public abstract void refreshBoard(Board board);

    public abstract void announceWin(PlayerSymbol playerSymbol, Board board);

    public abstract void announceDraw(Board board);

}