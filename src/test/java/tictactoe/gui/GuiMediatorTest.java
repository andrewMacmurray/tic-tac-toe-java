package tictactoe.gui;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import static org.testfx.matcher.control.TextMatchers.hasText;

public class GuiMediatorTest extends ApplicationTest {

    private GuiMediator guiMediator;
    private Parent rootNode;

    @Override
    public void start(Stage stage) {
        GuiMediator guiMediator = new GuiMediator();
        Scene scene = guiMediator.getCurrentScene();
        guiMediator.runGame();

        runXWinSequence(guiMediator);

        rootNode = scene.getRoot();

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void tilesPresent() {
        verifyThat(rootNode, hasChildren(3, ".player-x"));
        verifyThat(rootNode, hasChildren(2, ".player-o"));
        verifyThat(rootNode, hasChildren(4, ".empty"));
    }

    @Test
    public void disabledClicks() {
        clickOnTile(0);
        verifyThat(rootNode, hasChildren(3, ".player-x"));
        verifyThat(rootNode, hasChildren(2, ".player-o"));
    }
    @Test
    public void xWin() {
        verifyThat(".status-text", hasText("Player X Won!"));
    }

    private void runXWinSequence(GuiMediator guiMediator) {
        guiMediator.receiveMove(1);
        guiMediator.receiveMove(4);
        guiMediator.receiveMove(2);
        guiMediator.receiveMove(5);
        guiMediator.receiveMove(3);
    }

    private void clickOnTile(int index) {
        clickOn(lookupTile(index));
    }

    private Node lookupTile(int index) {
        ArrayList<Node> tiles = new ArrayList<>(lookup(".tile").queryAll());
        return tiles.get(index);
    }

}
