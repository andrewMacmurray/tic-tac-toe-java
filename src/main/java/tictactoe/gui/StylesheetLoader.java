package tictactoe.gui;

import javafx.scene.Scene;

public class StylesheetLoader {

    private Scene scene;

    public StylesheetLoader(Scene scene) {
        this.scene = scene;
    }

    public void load() {
        String stylesheet = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
    }

}
