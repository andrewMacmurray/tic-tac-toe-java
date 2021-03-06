package tictactoe.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GuiMediator guiMediator = new GuiMediator();
        guiMediator.runGame();

        Scene scene = guiMediator.getCurrentScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}