package tictactoe.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import tictactoe.core.Board;
import tictactoe.core.Mediator;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.core.players.Players;
import tictactoe.core.players.PlayersFactory;
import tictactoe.gui.board.BoardUI;
import tictactoe.gui.options.OptionsUI;

public class GuiMediator extends Mediator {

    private Scene currentScene;
    private BoardUI boardUI = new BoardUI(this::receiveMove);
    private OptionsUI optionsUI = new OptionsUI(this::receiveBoardSize, this::receiveGameTypeOption);

    public GuiMediator() {
        currentScene = initScene();
    }

    private Scene initScene() {
        Scene scene = new Scene(optionsUI, 800, 700);
        Stylesheet.load(scene);
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
        optionsUI.reset();
        setScene(optionsUI);
    }

    public void receiveGameTypeOption(int option) {
        Players players = new PlayersFactory(this).createPlayers(option);
        receivePlayers(players);
    }

    @Override
    public void requestBoardSizeFromUI() {
        optionsUI.showBoardSizeOptions();
        setScene(optionsUI);
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
        boardUI.setStatusText("Player " + playerSymbol + " Won!");
    }

    @Override
    public void announceDraw(Board board) {
        boardUI.disableClicks();
        boardUI.setStatusText("It's a draw!");
    }

    private void setScene(Parent rootNode) {
        currentScene.setRoot(rootNode);
    }

}