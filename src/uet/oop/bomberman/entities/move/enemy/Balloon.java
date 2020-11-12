package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Util;

import java.security.Key;
import java.util.Random;

public class Balloon extends Enemy {

    public Balloon(double x, double y, Image img) {
        super(x, y, img);
    }
    private long lastTimeChangeDirection = 0;
    private static final Image[][] img = {

            {Sprite.balloom_left0.getFxImage(),
             Sprite.balloom_left1.getFxImage(),
             Sprite.balloom_left2.getFxImage()},

            {Sprite.balloom_left0.getFxImage(),
             Sprite.balloom_left1.getFxImage(),
             Sprite.balloom_left2.getFxImage()},

            {Sprite.balloom_right0.getFxImage(),
             Sprite.balloom_right1.getFxImage(),
             Sprite.balloom_right2.getFxImage()},

            {Sprite.balloom_right0.getFxImage(),
             Sprite.balloom_right1.getFxImage(),
             Sprite.balloom_right2.getFxImage()},

            {Sprite.balloom_dead.getFxImage()}

    };

    @Override
    public void changeDirection() {
        if(System.currentTimeMillis() - lastTimeChangeDirection > 500) {
            KeyCode newDirect = chooseNewDirect();
            updateDirectAndStepInDirect(newDirect);
            lastTimeChangeDirection = System.currentTimeMillis();
        }
    }

    protected void updateDirectAndStepInDirect(KeyCode key) {
        if (key != direct)
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direct = key;
    }

    @Override
    public void move() {
        switch (direct) {
            case UP:
                moveUp();
                break;
            case LEFT:
                moveLeft();
                break;
            case DOWN:
                moveDown();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }

    private KeyCode chooseNewDirect() {
        int directionAsNumber = (int)(Math.random() * ((3 - 0) + 1));
        switch (directionAsNumber) {
            case 0:
                return KeyCode.LEFT;
            case 1:
                return KeyCode.UP;
            case 2:
                return KeyCode.RIGHT;
            case 3:
                return KeyCode.DOWN;
        }
        return KeyCode.RIGHT;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img[Util.getDirection(direct)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION], pos.x * Sprite.SCALED_SIZE, pos.y * Sprite.SCALED_SIZE);
    }
}
