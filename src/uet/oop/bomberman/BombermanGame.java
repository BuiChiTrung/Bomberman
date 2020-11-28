package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.timeline.CanvasManager;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.Util;


public class BombermanGame extends Application {
    private CanvasManager canvasManager = new CanvasManager();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvasManager.getCanvas());

        // Tao scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        addEventHandler(scene);
        canvasManager.createMap();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                canvasManager.delayRenderTimeBetweenTwoFrame();

                Util.bfsFromBomber();

                Container.updateEntity();
                Container.removeDestroyedEntity();
                canvasManager.renderEntity();
            }
        };

        timer.start();
    }

    public void addEventHandler(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Container.bomber.handlePress(event.getCode());
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Container.bomber.handleRelease(event.getCode());
            }
        });
    }
}