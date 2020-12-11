package uet.oop.bomberman.timeline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.util.ImgFactory;

public class MenuSceneController {
    @FXML
    private Button customButton;
    @FXML
    private Button classButton;
    @FXML
    private VBox navbar;
    @FXML
    private ImageView image;

    public void handleCustomButton() {
        ImgFactory.createCustomImg();
        BombermanGame.startGame();
    }

    public void handleClassicButton() {
        ImgFactory.createClassicImg();
        BombermanGame.startGame();
    }
}
