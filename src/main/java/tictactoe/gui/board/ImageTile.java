package tictactoe.gui.board;

import javafx.scene.image.ImageView;

public class ImageTile extends ImageView {

    public ImageTile(String url, int boardSize) {
        super(url);
        setSize(boardSize);
    }

    private void setSize(int boardSize) {
        if (boardSize == 3) {
            setWidthHeight(120);
        } else {
            setWidthHeight(90);
        }
    }

    private void setWidthHeight(int size) {
        this.setFitHeight(size);
        this.setFitWidth(size);
    }

}
