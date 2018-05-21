package tictactoe.gui;

import javafx.scene.Scene;

public class Stylesheet {

    public static void load(Scene scene) {
        String stylesheet = Main.class.getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
    }

}
