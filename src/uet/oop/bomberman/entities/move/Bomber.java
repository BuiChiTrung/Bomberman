package uet.oop.bomberman.entities.move;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Util;

public class Bomber extends MovingEntity {

    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 2;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private static double[] tryStep;
    private static final Image[][] img = {
            //LEFT:0
            {Sprite.player_left_0.getFxImage(),
                    Sprite.player_left_1.getFxImage(),
                    Sprite.player_left_2.getFxImage()},
            //UP:1
            {Sprite.player_up_0.getFxImage(),
                    Sprite.player_up_1.getFxImage(),
                    Sprite.player_up_2.getFxImage()},
            //RIGHT:2
            {Sprite.player_right_0.getFxImage(),
                    Sprite.player_right_1.getFxImage(),
                    Sprite.player_right_2.getFxImage()},
            //DOWN:3
            {Sprite.player_down_0.getFxImage(),
                    Sprite.player_down_1.getFxImage(),
                    Sprite.player_down_2.getFxImage()},
            //DEAD:4
            {Sprite.player_dead_0.getFxImage(),
                    Sprite.player_dead_1.getFxImage(),
                    Sprite.player_dead_2.getFxImage()}
    };

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
        velocity = 0.125 * 2;
        tryStep = new double[]{0, -velocity * 2, velocity * 2, -velocity, velocity};
    }

    public void handle(KeyCode key) {
        if(key == KeyCode.RIGHT || key == KeyCode.LEFT || key == KeyCode.UP || key == KeyCode.DOWN) {
            updateDirectAndStepInDirect(key);
            move(key);
        }
    }

    public void move(KeyCode eventDirection) {
        switch (eventDirection) {
            case LEFT:
                moveLeft();
                break;
            case UP:
                moveUp();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN:
                moveDown();
                break;
        }
    }

    protected void updateDirectAndStepInDirect(KeyCode eventDirection) {
        if (eventDirection != direction)
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direction = eventDirection;
    }

    protected void moveLeft() {
        for (double stepX : tryStep) {
            if (!hasObstacle(pos.x + stepX, pos.y - velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y - velocity;
                return;
            }
        }
    }

    protected void moveUp() {
        for (double stepY : tryStep) {
            if (!hasObstacle(pos.x - velocity, pos.y + stepY)) {
                pos.x = pos.x - velocity;
                pos.y = pos.y + stepY;
                return;
            }
        }
    }

    protected void moveRight() {
        for (double stepX : tryStep) {
            if (!hasObstacle(pos.x + stepX, pos.y + velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y + velocity;
                return;
            }
        }
    }

    protected void moveDown() {
        for (double stepY : tryStep) {
            if (!hasObstacle(pos.x + velocity, pos.y + stepY)) {
                pos.x = pos.x + velocity;
                pos.y = pos.y + stepY;
                return;
            }
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img[Util.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION], pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }

}