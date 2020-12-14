package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.bomberman.scene.Container;
import uet.oop.bomberman.scene.MainScene;
import uet.oop.bomberman.scene.MenuScene;
import uet.oop.bomberman.util.SoundUtil;
import uet.oop.bomberman.util.Util;

public class BombermanGame extends Application {
    private static Stage primaryStage;
    private static long lastRenderTime;

    private static AnimationTimer timer;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static AnimationTimer getTimer() {
        return timer;
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

        timer = new AnimationTimer() {
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