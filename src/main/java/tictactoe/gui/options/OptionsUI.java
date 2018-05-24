package tictactoe.gui.options;

import javafx.scene.layout.VBox;
import tictactoe.gui.title.AnimatedLogo;
import tictactoe.gui.title.TitleText;

import java.util.function.Consumer;

public class OptionsUI extends VBox {

    private AnimatedLogo animatedLogo;
    private TitleText titleText;
    private BoardSizeOptions boardSizeOptions;
    private GameTypeOptions gameTypeOptions;

    public OptionsUI(Consumer<Integer> sendBoardSize, Consumer<Integer> sendGameType) {
        this.animatedLogo = new AnimatedLogo();
        this.titleText = new TitleText();
        this.boardSizeOptions = new BoardSizeOptions(sendBoardSize);
        this.gameTypeOptions = new GameTypeOptions(sendGameType);
        setup();
    }

    public void showBoardSizeOptions() {
        this.getChildren().remove(gameTypeOptions);
        this.getChildren().add(boardSizeOptions);
    }

    private void setup() {
        assembleSceneGraph();
        addCss();
        animate();
    }

    private void assembleSceneGraph() {
        this.getChildren().addAll(
                animatedLogo,
                titleText,
                gameTypeOptions
        );
    }

    private void addCss() {
        this.getStyleClass().add("options-container");
    }

    private void animate() {
        animatedLogo.animateAndThen(() -> titleText.animateAndThen(() -> gameTypeOptions.animate()));
    }

}