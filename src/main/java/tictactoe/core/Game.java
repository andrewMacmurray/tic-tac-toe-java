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

    public void receivePlayers(Players players) {
        this.players = players;
        mediator.requestBoardSizeFromUI();
    }

    public void receiveBoardSize(int boardSize) {
        board = new Board(boardSize);
        mediator.gameInstructions(board, players.currentPlayerSymbol());
        players.chooseNextMove(board);
    }

    public void receiveMove(int move) {
        Board newBoard = evalNextMove(move);
        mediator.moveSummary(move, board, newBoard, players.currentPlayerSymbol());
        setNextState(newBoard);
        playNextMove(newBoard);
    }

    private void playNextMove(Board board) {
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

    private void setNextState(Board board) {
        this.board = board;
        players.switchPlayers();
    }

    private Board evalNextMove(int move) {
        return board.makeMove(move, players.currentPlayerSymbol());
    }

}