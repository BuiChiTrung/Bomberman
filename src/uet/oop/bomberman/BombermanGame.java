package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.timeline.MainScene;
import uet.oop.bomberman.timeline.MenuScene;
import uet.oop.bomberman.util.SoundUtil;
import uet.oop.bomberman.util.Util;

public class BombermanGame extends Application {
    private static Stage primaryStage;
    private static long lastRenderTime;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Bomberman");

        primaryStage.setScene(MenuScene.getScene());
        primaryStage.show();
    }

    public static void startGame() {
        primaryStage.setScene(MainScene.getScene());

        if(Container.soundOn) {
            SoundUtil.playThemeSound("res/sound/themesound.mp3");
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                delayRenderTimeBetweenTwoFrame();
                Util.bfsFromBomber();
                MainScene.loop();
            }
        };

        timer.start();
    }

    public static void delayRenderTimeBetweenTwoFrame() {
        while (System.currentTimeMillis() - lastRenderTime < 30) {
            // loop until difference >= 40
        }
        lastRenderTime = System.currentTimeMillis();
    }
}