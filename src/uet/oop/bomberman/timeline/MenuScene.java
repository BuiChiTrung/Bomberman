package uet.oop.bomberman.timeline;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

public class MenuScene {
    private static Scene menuScene = null;

    private static Scene setUpMenuScene() {
        try {
            String path= "src/uet/oop/bomberman/timeline/MenuScene.fxml";
            Parent rootNode = FXMLLoader.load(Paths.get(path).toUri().toURL());
            menuScene = new Scene(rootNode, Sprite.SCALED_SIZE * 31, Sprite.SCALED_SIZE * 13);

            menuScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    BombermanGame.startGame();
                }
            });
        } catch(IOException e) {
            System.out.println("ERROR WHILE LOADING FXML");
        }

        return menuScene;
    }

    public static Scene getMenuScene() {
        if (menuScene == null)
            menuScene = setUpMenuScene();
        return menuScene;
    }

    public static void main(String[] args) {



    }
}
