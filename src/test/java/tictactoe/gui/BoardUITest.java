package tictactoe.gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import tictactoe.core.Board;
import tictactoe.core.players.PlayerSymbol;
import tictactoe.gui.board.BoardUI;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.control.TextMatchers.hasText;

public class BoardUITest extends ApplicationTest {

    private int currentMove;
    private BoardUI boardUI = new BoardUI(this::setCurrentMove);

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(boardUI, 800, 700);
        boardUI.renderBoard(new Board(3));
        Stylesheet.load(scene);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void checkLayout() {
        verifyThat(".status-text", isNotNull());
        verifyThat(".board-container", isNotNull());
        verifyThat(".board-ui-container", isNotNull());
    }

    @Test
    public void setStatusText() {
        boardUI.setStatusText("your turn player O");
        verifyThat(".status-text", hasText("your turn player O"));
    }

    @Test
    public void renderBoard() {
        verifyThat(boardUI, hasChildren(9, ".tile"));
    }

    @Test
    public void clickTile() {
        List<Node> tilesList = new ArrayList<>(lookup(".tile").queryAll());
        Node tile1 = tilesList.get(0);
        clickOn(tile1);

        assertEquals(
                "mediator receives correct move",
                1,
                currentMove
        );
    }

    @Test
    public void renderMoves() {
        Board currentBoard = new Board(3)
                .makeMove(1, PlayerSymbol.O)
                .makeMove(2, PlayerSymbol.X);
        new FxRobot().interact(() -> boardUI.renderBoard(currentBoard));

        verifyThat(boardUI, hasChildren(1, ".player-o"));
        verifyThat(boardUI, hasChildren(1, ".player-x"));
    }

    private void setCurrentMove(int move) {
        this.currentMove = move;
    }

}
