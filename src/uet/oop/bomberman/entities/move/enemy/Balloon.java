package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Util;

import java.security.Key;
import java.util.Random;

public class Balloon extends Enemy {
    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 2;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private long lastTimeChangeDirection = 0;


    public Balloon(double x, double y, Image img) {
        super(x, y, img);
        velocity /= 2;
    }

    private static final Image[][] img = {
            //LEFT
            {Sprite.balloom_left0.getFxImage(),
             Sprite.balloom_left1.getFxImage(),
             Sprite.balloom_left2.getFxImage()},
            //UP
            {Sprite.balloom_left0.getFxImage(),
             Sprite.balloom_left1.getFxImage(),
             Sprite.balloom_left2.getFxImage()},
            //RIGHT
            {Sprite.balloom_right0.getFxImage(),
             Sprite.balloom_right1.getFxImage(),
             Sprite.balloom_right2.getFxImage()},
            //UP
            {Sprite.balloom_right0.getFxImage(),
             Sprite.balloom_right1.getFxImage(),
             Sprite.balloom_right2.getFxImage()},

            {Sprite.balloom_dead.getFxImage()}

    };

    public void changeDirection() {
        if(System.currentTimeMillis() - lastTimeChangeDirection > 500) {
            KeyCode newDirect = chooseNewDirect();
            updateDirectionAndStepInDirect(newDirect);
            lastTimeChangeDirection = System.currentTimeMillis();
        }
    }

    protected void updateDirectionAndStepInDirect(KeyCode key) {
        if (key != direct)
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direct = key;
    }

    @Override
    public void move() {
        changeDirection();
        moveAlongDirection();
    }

    private KeyCode chooseNewDirect() {
        int directionAsNumber = (int)(Math.random() * ((3 - 0) + 1));
        return Util.getDirection(directionAsNumber);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img[Util.getDirectionId(direct)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION], pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
}
