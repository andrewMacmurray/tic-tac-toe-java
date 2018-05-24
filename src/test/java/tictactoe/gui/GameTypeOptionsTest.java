package tictactoe.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import tictactoe.gui.options.GameTypeOptions;

import static junit.framework.TestCase.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class GameTypeOptionsTest extends ApplicationTest {

    private GameTypeOptions gameTypeOptions;
    private int gameTypeIndex;

    @Override
    public void start(Stage stage) {
        gameTypeOptions = new GameTypeOptions(this::setGameTypeIndex);
        Scene scene = new BaseScene(gameTypeOptions);

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void showOptions() {
        verifyThat(gameTypeOptions, hasChildren(1, ".option-button-1"));
        verifyThat(gameTypeOptions, hasChildren(1, ".option-button-2"));
        verifyThat(gameTypeOptions, hasChildren(1, ".option-button-3"));
    }

    @Test
    public void selectGameOption() {
        clickOn(".option-button-2");
        assertEquals(2, gameTypeIndex);
    }

    private void setGameTypeIndex(int index) {
        gameTypeIndex = index;
    }

}
