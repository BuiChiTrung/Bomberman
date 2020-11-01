package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Bomber extends MovingEntity {
    private static Image[] img_state = {
            // 0 -> 2
            Sprite.player_up_0.getFxImage(),
            Sprite.player_up_1.getFxImage(),
            Sprite.player_up_2.getFxImage(),
            // 3 -> 5
            Sprite.player_left_0.getFxImage(),
            Sprite.player_left_1.getFxImage(),
            Sprite.player_left_2.getFxImage(),
            // 6 -> 8
            Sprite.player_down_0.getFxImage(),
            Sprite.player_down_1.getFxImage(),
            Sprite.player_down_2.getFxImage(),
            // 9 -> 11
            Sprite.player_right_0.getFxImage(),
            Sprite.player_right_1.getFxImage(),
            Sprite.player_right_2.getFxImage(),
            // 12 -> 14
            Sprite.player_dead_0.getFxImage(),
            Sprite.player_dead_1.getFxImage(),
            Sprite.player_dead_2.getFxImage(),

    };

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

    public void move(KeyCode eventDirection) {
        if (eventDirection != direct)
            stepInDirect = 0;
        else
            stepInDirect = (stepInDirect + 1) % 3;
        direct = eventDirection;

        switch (eventDirection) {
            case UP:
                if (!hasObstacle(x, y - 1))
                    y -= 1;
                    img = img_state[0 + stepInDirect];
                break;
            case LEFT:
                if (!hasObstacle(x - 1, y))
                    x -= 1;
                    img = img_state[3 + stepInDirect];
                break;
            case DOWN:
                if (!hasObstacle(x, y + 1))
                    y += 1;
                    img = img_state[6 + stepInDirect];
                break;
            case RIGHT:
                if (!hasObstacle(x + 1, y))
                    x += 1;
                    img = img_state[9 + stepInDirect];
                break;
        }
        System.out.println(x + " " + y);
    }

    private boolean hasObstacle(double x, double y) {
        if (x < 0 || x > BombermanGame.WIDTH) return true;
        if (y < 0 || y > BombermanGame.HEIGHT) return true;
        ArrayList<Entity>[][] stillObjects = BombermanGame.stillObjects;

        Entity lastEntity = stillObjects[(int) x][(int) y].get(stillObjects[(int) x][(int) y].size() - 1);
        if (lastEntity instanceof Brick || lastEntity instanceof Wall) return true;

        return false;
    }
}
