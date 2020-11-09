package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MovingEntity {
    private static final int PRESS_TIME_TO_CHANGE_IMG = 5;
    private static double[] tryStep;

    private static Image[] imgState = {
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

    public static final Bomber INSTANCE = new Bomber(1, 1, Sprite.player_right_0.getFxImage());

    private Bomber(double x, double y, Image img) {
        super( x, y, img);
        velocity = 0.125;
        tryStep = new double[]{0, -velocity, velocity};
    }

    @Override
    public void update() {}

    public void move(KeyCode eventDirection) {
        // make bomber move slower.
        //if (System.currentTimeMillis() - CanvasManager.lastRenderTime < 25 && eventDirection == direct) {
        //    return;
        //}

        // render again old pos and new pos of bomber in canvas by adding to modified list
        addToModifiedObjects(pos);
        updateDirectAndStepInDirect(eventDirection);
        updatePos(eventDirection);
        updateImg();
        addToModifiedObjects(pos);
    }

    protected void updateDirectAndStepInDirect(KeyCode eventDirection) {
        if (eventDirection != direct)
            stepInDirect = 0;
        else
            stepInDirect = (stepInDirect + 1) % (PRESS_TIME_TO_CHANGE_IMG * NUMBER_OF_IMG_IN_ONE_DIRECTION);
        direct = eventDirection;
    }

    protected void moveUp() {
        for (double stepX : tryStep)
            if (!hasObstacle(pos.x + stepX, pos.y - velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y - velocity;
                return;
            }
    }

    protected void moveLeft() {
        for (double stepY : tryStep)
            if (!hasObstacle(pos.x - velocity, pos.y + stepY)) {
                pos.x = pos.x - velocity;
                pos.y = pos.y + stepY;
                return;
            }
    }

    protected void moveDown() {
        for (double stepX : tryStep)
            if (!hasObstacle(pos.x + stepX, pos.y + velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y + velocity;
                return;
            }
    }

    protected void moveRight() {
        for (double stepY : tryStep)
            if (!hasObstacle(pos.x + velocity, pos.y + stepY)) {
                pos.x = pos.x + velocity;
                pos.y = pos.y + stepY;
                return;
            }
    }

    @Override
    protected void updateImg() {
       switch (direct) {
           case UP:
               imgIndex = 0;
               break;
           case LEFT:
               imgIndex = 3;
               break;
           case DOWN:
               imgIndex = 6;
               break;
           case RIGHT:
               imgIndex = 9;
               break;
       }
       img = imgState[imgIndex + stepInDirect / PRESS_TIME_TO_CHANGE_IMG];
    }
}
