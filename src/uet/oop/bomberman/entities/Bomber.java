package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;

import java.util.List;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

    public void move(KeyCode direction) {
        List<Entity> test = BombermanGame.getStillObjects();
        for (int i = 0; i < test.size(); ++i) {
            System.out.println(test.get(i));
        }
        switch (direction) {
            case LEFT:
                x -= 0.25;
                break;
            case RIGHT:
                x += 0.25;
                break;
            case DOWN:
                y += 0.25;
                break;
            case UP:
                y -= 0.25;
                break;
        }
    }
}
