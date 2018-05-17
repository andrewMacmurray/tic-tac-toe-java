package tictactoe.gui;

import javafx.scene.Node;
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
    private Scene currentScene;

    @Override
    public void start(Stage stage) {
        GuiMediator guiMediator = new GuiMediator();
        currentScene = guiMediator.getCurrentScene();
        guiMediator.playGame();

        stage.setScene(currentScene);
        stage.show();
    }

    @Test
    public void clickOnTile() {
        clickOn(lookupTile(0));

        verifyThat(currentScene.getRoot(), hasChildren(1, ".player-x"));
        verifyThat(currentScene.getRoot(), hasChildren(0, ".player-o"));
    }

    @Test
    public void makeMultipleMoves() {
        clickOn(lookupTile(0));
        clickOn(lookupTile(1));
        clickOn(lookupTile(2));
        clickOn(lookupTile(3));

        verifyThat(currentScene.getRoot(), hasChildren(2, ".player-x"));
        verifyThat(currentScene.getRoot(), hasChildren(2, ".player-o"));
    }

    @Test
    public void disabledClickOnExistingMove() {
        clickOn(lookupTile(0));
        clickOn(lookupTile(0));

        verifyThat(currentScene.getRoot(), hasChildren(1, ".player-x"));
        verifyThat(currentScene.getRoot(), hasChildren(0, ".player-o"));
    }

    @Test
    public void xWin() {
        clickOn(lookupTile(0));
        clickOn(lookupTile(3));
        clickOn(lookupTile(1));
        clickOn(lookupTile(4));
        clickOn(lookupTile(2));

        verifyThat(".status-text", hasText("Player X Won!"));
    }

    @Test
    public void oWin() {
        clickOn(lookupTile(3));
        clickOn(lookupTile(0));
        clickOn(lookupTile(4));
        clickOn(lookupTile(1));
        clickOn(lookupTile(6));
        clickOn(lookupTile(2));

        verifyThat(".status-text", hasText("Player O Won!"));
    }

    @Test
    public void disabledClicksAfterGame() {
        clickOn(lookupTile(0));
        clickOn(lookupTile(3));
        clickOn(lookupTile(1));
        clickOn(lookupTile(4));
        clickOn(lookupTile(2));

        clickOn(lookupTile(8));

        verifyThat(currentScene.getRoot(), hasChildren(3, ".player-x"));
        verifyThat(currentScene.getRoot(), hasChildren(2, ".player-o"));
    }

    private Node lookupTile(int index) {
        ArrayList<Node> tiles = new ArrayList<>(lookup(".tile").queryAll());
        return tiles.get(index);
    }

}
