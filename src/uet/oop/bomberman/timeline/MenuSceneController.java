package uet.oop.bomberman.timeline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import uet.oop.bomberman.BombermanGame;

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
        System.out.println("haha");
        BombermanGame.startGame();
    }

    public void handleClassicButton() {
        System.out.println("haha");
        BombermanGame.startGame();
    }
}
