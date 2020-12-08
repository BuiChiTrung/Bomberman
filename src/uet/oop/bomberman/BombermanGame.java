package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.CanvasManager;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.timeline.MenuScene;
import uet.oop.bomberman.util.SoundUtil;
import uet.oop.bomberman.util.Util;

import java.io.IOException;
import java.nio.file.Paths;


public class BombermanGame extends Application {
    private static Stage primaryStage;
    private static CanvasManager canvasManager = new CanvasManager();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Bomberman");

//        String path= "src/uet/oop/bomberman/timeline/MenuScene.fxml";
//        Parent rootNode = FXMLLoader.load(Paths.get(path).toUri().toURL());
//        Scene menuScene = new Scene(rootNode, Sprite.SCALED_SIZE * 31, Sprite.SCALED_SIZE * 13);
//
//        menuScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                startGame();
//            }
//        });

        primaryStage.setScene(MenuScene.getMenuScene());
        primaryStage.show();
    }

    public static void startGame() {
        Group root = new Group();
        root.getChildren().add(canvasManager.getCanvas());
        Scene gameScene = new Scene(root);

        primaryStage.setScene(gameScene);
        addEventHandler(gameScene);
        canvasManager.createMap();
        SoundUtil.playThemeSound();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                canvasManager.delayRenderTimeBetweenTwoFrame();

                Util.bfsFromBomber();

                Container.updateEntity();
                Container.removeDestroyedEntity();
                canvasManager.renderEntity();
                if (Container.bomber.isDestroy()) {
                    Container.reset();
                    canvasManager.createMap();
                }
            }
        };

        timer.start();
    }

    public static void addEventHandler(Scene gameScene) {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Container.bomber.handlePress(event.getCode());
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Container.bomber.handleRelease(event.getCode());
            }
        });
    }
}