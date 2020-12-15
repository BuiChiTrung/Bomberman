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
    @FXML
    private ImageView soundOnImage;
    @FXML
    private ImageView soundOffImage;

    private static boolean firstTime = true;
    public void initialize() {
        if(firstTime) {
            soundOffImage.setVisible(false);
            soundButton.setSelected(true);
        }
    }

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
            soundOnImage.setVisible(true);
            soundOffImage.setVisible(false);
        }
        else {
            Container.soundOn = false;
            SoundUtil.pauseThemeSound();
            soundOnImage.setVisible(false);
            soundOffImage.setVisible(true);
        }
    }
}
