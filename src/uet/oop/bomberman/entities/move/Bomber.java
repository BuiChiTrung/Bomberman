package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;

public class Bomber extends MovingEntity {

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

    public void move(KeyCode direction) {
        switch (direction) {
            case LEFT:
                if (!hasObstacle(x - 1, y))
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

    private boolean hasObstacle(double x, double y) {
        if (0 < x || x > BombermanGame.WIDTH) return false;
        if (0 < y || y > BombermanGame.HEIGHT) return false;
        ArrayList<Entity>[][] stillObjects = BombermanGame.stillObjects;

        return false;
    }
}
