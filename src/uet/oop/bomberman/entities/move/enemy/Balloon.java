package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.Util;


public class Balloon extends Enemy {
    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 2;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private long lastTimeChangeDirection = 0;
    private long lastMoveTime = 0;

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
            Direction newDirect = chooseNewDirect();
            updateDirectionAndStepInDirect(newDirect);
            lastTimeChangeDirection = System.currentTimeMillis();
        }
    }

    @Override
    public void move() {
        if(System.currentTimeMillis() - lastMoveTime < 20) {
            return ;
        }
        changeDirection();
        moveAlongDirection();;
        lastMoveTime = System.currentTimeMillis();
    }

    private Direction chooseNewDirect() {
        int directionAsNumber = (int)(Math.random() * ((3 - 0) + 1));
        return DirectionUtil.getDirectionFromId(directionAsNumber);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img[DirectionUtil.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION], pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
}
