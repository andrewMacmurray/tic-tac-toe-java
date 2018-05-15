package tictactoe.core;

import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;

public class Game {

    private Board board;
    private Players players;
    private Mediator mediator;

    public Game(Mediator mediator) {
        this.mediator = mediator;
    }

    public void playGame() {
        mediator.requestPlayers();
    }

    public void setPlayers(Players players) {
        this.players = players;
        mediator.requestBoardSize();
    }

    public void setBoardSize(int boardSize) {
        this.board = new Board(boardSize);
        mediator.requestMove(this.board, players.currentPlayerSymbol());
    }

    public void playMove(int move) {
        Board newBoard = evalNextMove(move);
        nextState(newBoard);
        nextMove(newBoard);
    }

    private void nextMove(Board board) {
        if (!board.isTerminal()) {
            players.chooseNextMove(board);
        } else {
            terminus(board);
        }
    }

    private void terminus(Board board) {
        if (board.xWin()) {
            mediator.announceWin(PlayerSymbol.X, board);
        } else if (board.oWin()) {
            mediator.announceWin(PlayerSymbol.O, board);
        } else {
            mediator.announceDraw(board);
        }
    }

    private void nextState(Board board) {
        this.board = board;
        players.switchPlayers();
    }

    private Board evalNextMove(int move) {
        Board nextBoard = board.makeMove(move, players.currentPlayerSymbol());
        mediator.refreshBoard(nextBoard);
        mediator.moveSummary(move, board, players.currentPlayerSymbol());
        return nextBoard;
    }

}