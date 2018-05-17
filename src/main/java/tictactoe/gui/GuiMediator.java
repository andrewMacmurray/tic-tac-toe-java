package tictactoe.gui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;
import tictactoe.gui.board.BoardUI;

public class GuiMediator extends Mediator {

    private Scene currentScene;
    private BoardUI boardUI = new BoardUI(this);

    public GuiMediator() {
        StackPane stackPane = new StackPane();
        currentScene = initScene();
    }

    public Scene initScene() {
        Scene scene = new Scene(new StackPane(), 800, 800);
        new StylesheetLoader(scene).load();
        return scene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    @Override
    public void runGame() {
        requestPlayersFromUI();
    }

    @Override
    public void requestPlayersFromUI() {
        Players players = new PlayersFactory(this).createPlayers(1);
        super.receivePlayers(players);
    }

    @Override
    public void requestBoardSizeFromUI() {
        super.receiveBoardSize(3);
    }

    @Override
    public void requestMoveFromUI(Board board, PlayerSymbol playerSymbol) {
        boardUI.renderBoard(board);
    }

    @Override
    public void moveSummary(int move, Board prevBoard, Board nextBoard, PlayerSymbol playerSymbol) {
        String statusText = "Your turn player " + playerSymbol.getAlternate();
        boardUI.renderBoard(nextBoard);
        boardUI.setStatusText(statusText);
    }

    @Override
    public void gameInstructions(Board board, PlayerSymbol playerSymbol) {
        String instructions = "Your turn player " + playerSymbol;
        boardUI.setStatusText(instructions);
        currentScene.setRoot(boardUI);
    }

    @Override
    public void announceWin(PlayerSymbol playerSymbol, Board board) {
        boardUI.disableClicks();
        boardUI.setStatusText("Player " + playerSymbol.toString() + " Won!");
    }

    @Override
    public void announceDraw(Board board) {
        boardUI.disableClicks();
        boardUI.setStatusText("It's a draw!");
    }

}