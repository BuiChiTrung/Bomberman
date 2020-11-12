package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.move.MovingEntity;

import java.util.Random;

public abstract class Enemy extends MovingEntity {
    protected static int moveTimeToCrossOneCell;
    protected static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 2;
    protected static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private static final double acceptedPass = 0.125;
    private static final double[] tryStep = {0, -acceptedPass * 2, acceptedPass * 2, -acceptedPass, acceptedPass};

    protected KeyCode direct = KeyCode.RIGHT;

    public Enemy(double x, double y, Image img) {
        super(x, y, img);
        velocity = 0.125 / 4;
    }

    public abstract void move();

    public abstract void changeDirection();

    protected boolean moveUp() {
        for (double stepX : tryStep) {
            if (!hasObstacle(pos.x + stepX, pos.y - velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y - velocity;
                return true;
            }
        }
        return false;
    }

    protected boolean moveLeft() {
        for (double stepY : tryStep) {
            if (!hasObstacle(pos.x - velocity, pos.y + stepY)) {
                pos.x = pos.x - velocity;
                pos.y = pos.y + stepY;
                return true;
            }
        }
        return false;
    }

    protected boolean moveDown() {
        for (double stepX : tryStep) {
            if (!hasObstacle(pos.x + stepX, pos.y + velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y + velocity;
                return true;
            }
        }
        return false;
    }

    protected boolean moveRight() {
        for (double stepY : tryStep) {
            if (!hasObstacle(pos.x + velocity, pos.y + stepY)) {
                pos.x = pos.x + velocity;
                pos.y = pos.y + stepY;
                return true;
            }
        }
        return false;
    }
}


