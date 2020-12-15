package uet.oop.bomberman.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.nio.file.Paths;

public class MenuScene {
    private static Scene scene = null;

    public static Scene setUpScene() {
        try {
            String path= "src/uet/oop/bomberman/scene/MenuScene.fxml";
            Parent rootNode = FXMLLoader.load(Paths.get(path).toUri().toURL());
            scene = new Scene(rootNode, Sprite.SCALED_SIZE * 31, Sprite.SCALED_SIZE * 13);

        } catch(IOException e) {
            System.out.println("ERROR WHILE LOADING FXML");
        }

        return scene;
    }

    public static void main(String[] args) {

    }
}
