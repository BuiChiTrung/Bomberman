package uet.oop.bomberman.scene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.util.ImgFactory;
import uet.oop.bomberman.util.SoundUtil;

public class MenuSceneController {
    @FXML
    private Button customButton;
    @FXML
    private Button classButton;
    @FXML
    private VBox navbar;
    @FXML
    private ImageView image;
    @FXML
    private ToggleButton soundButton;

    public void handleCustomButton() {
        ImgFactory.createCustomImg();
        BombermanGame.startGame();
    }

    public void handleClassicButton() {
        ImgFactory.createClassicImg();
        BombermanGame.startGame();
    }

    public void handleSoundButton() {
        if(soundButton.isSelected()) {
            Container.soundOn = true;
            SoundUtil.playThemeSound();

        }
        else {
            Container.soundOn = false;
            SoundUtil.pauseThemeSound();
        }
        Container.soundOn = !soundButton.isSelected();
    }
}
