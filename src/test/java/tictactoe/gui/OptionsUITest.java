package tictactoe.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import tictactoe.gui.options.OptionsUI;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class OptionsUITest extends ApplicationTest {

    private OptionsUI optionsUI;

    @Override
    public void start(Stage stage) {
        optionsUI = new OptionsUI(n -> {}, n -> {});
        Scene scene = new BaseScene(optionsUI);

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void initialElements() {
        verifyThat(optionsUI, hasChildren(1, ".logo-container"));
        verifyThat(optionsUI, hasChildren(1, ".option-button-1"));
        verifyThat(optionsUI, hasChildren(1, ".option-button-2"));
        verifyThat(optionsUI, hasChildren(1, ".option-button-3"));
        verifyThat(optionsUI, hasChildren(0, ".option-button-4"));
    }

    @Test
    public void boardSizeOptions() {
        new FxRobot().interact(() -> optionsUI.showBoardSizeOptions());
        verifyThat(optionsUI, hasChildren(1, ".option-button-3"));
        verifyThat(optionsUI, hasChildren(1, ".option-button-4"));
        verifyThat(optionsUI, hasChildren(0, ".option-button-2"));
        verifyThat(optionsUI, hasChildren(0, ".option-button-1"));
    }

}
