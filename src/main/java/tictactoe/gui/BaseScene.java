package tictactoe.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class BaseScene extends Scene {

    public BaseScene() {
        super(new Pane(), 800, 700);
        loadStylesheet();
    }

    public BaseScene(Parent parent) {
        super(parent, 800, 700);
        loadStylesheet();
    }

    private void loadStylesheet() {
        String stylesheet = Main.class.getResource("/style.css").toExternalForm();
        this.getStylesheets().add(stylesheet);
    }

}
