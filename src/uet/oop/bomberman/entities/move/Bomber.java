package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

import static java.lang.Math.floor;

public class Bomber extends MovingEntity {
    private static double[] tryStep;

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
        velocity = 0.25;
        tryStep = new double[]{0, -0.25, 0.25};
    }

    @Override
    public void update() {

    }

    public void move(KeyCode eventDirection) {
        System.out.println(pos + " " +  BombermanGame.modifiedObjects.size());
        addToModifiedObjects(pos);

        if (eventDirection != direct)
            stepInDirect = 0;
        else
            stepInDirect = (stepInDirect + 1) % 3;
        direct = eventDirection;

        switch (eventDirection) {
            case UP:
                moveUp();
                img = img_state[0 + stepInDirect];
                break;
            case LEFT:
                moveLeft();
                img = img_state[3 + stepInDirect];
                break;
            case DOWN:
                moveDown();
                img = img_state[6 + stepInDirect];
                break;
            case RIGHT:
                moveRight();
                img = img_state[9 + stepInDirect];
                break;
        }
        addToModifiedObjects(pos);
    }

    private void moveUp() {
        for (double stepX : tryStep)
            if (!hasObstacle(pos.x + stepX, pos.y - velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y - velocity;
                return;
            }
    }

    private void moveLeft() {
        for (double stepY : tryStep)
            if (!hasObstacle(pos.x - velocity, pos.y + stepY)) {
                pos.x = pos.x - velocity;
                pos.y = pos.y + stepY;
                return;
            }
    }

    private void moveDown() {
        for (double stepX : tryStep)
            if (!hasObstacle(pos.x + stepX, pos.y + velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y + velocity;
                return;
            }
    }

    private void moveRight() {
        for (double stepY : tryStep)
            if (!hasObstacle(pos.x + velocity, pos.y + stepY)) {
                pos.x = pos.x + velocity;
                pos.y = pos.y + stepY;
                return;
            }
    }
}
