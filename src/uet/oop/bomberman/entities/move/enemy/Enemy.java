package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.move.MovingEntity;

import java.util.Random;

public abstract class Enemy extends MovingEntity {
    protected static final int MOVE_TIME_TO_CHANGE_IMG = 4;
    protected static int moveTimeToCrossOneCell;

    public Enemy(double x, double y, Image img) {
        super(x, y, img);
        velocity = 0.125 / 2;
        moveTimeToCrossOneCell = (int)(1 / velocity);
    }

    @Override
    protected void moveUp() {
        if (!hasObstacle(pos.x, pos.y - velocity)) {
            collide = false;
            pos.y = pos.y - velocity;
        }
    }

    @Override
    protected void moveLeft() {
        if (!hasObstacle(pos.x - velocity, pos.y)) {
            collide = false;
            pos.x = pos.x - velocity;
        }
    }

    @Override
    protected void moveDown() {
        if (!hasObstacle(pos.x, pos.y + velocity)) {
            collide = false;
            pos.y = pos.y + velocity;
        }
    }

    @Override
    protected void moveRight() {
        if (!hasObstacle(pos.x + velocity, pos.y)) {
            collide = false;
            pos.x = pos.x + velocity;
        }
    }
}



