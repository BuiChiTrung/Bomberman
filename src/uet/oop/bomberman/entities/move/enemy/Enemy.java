package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.move.MovingEntity;

import java.util.Random;

import static javafx.scene.input.KeyCode.*;

public abstract class Enemy extends MovingEntity {
    private static final double acceptedPass = 0.125;
    private static final double[] tryStep = {0, -acceptedPass * 2, acceptedPass * 2, -acceptedPass, acceptedPass};
    protected Direction direction = Direction.RIGHT;
    public Enemy(double x, double y, Image img) {
        super(x, y, img);
        velocity = 0.125;
    }

    public abstract void move();

    protected void updateDirectionAndStepInDirect(Direction nextDirection) {
        if (nextDirection.equals(direction))
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direction = nextDirection;
    }

    public void moveAlongDirection() {
        switch (direction) {
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

    protected void moveLeft() {
        for (double stepX : tryStep) {
            if (!hasObstacle(pos.x + stepX, pos.y - velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y - velocity;
            }
        }
    }

    protected void moveUp() {
        for (double stepY : tryStep) {
            if (!hasObstacle(pos.x - velocity, pos.y + stepY)) {
                pos.x = pos.x - velocity;
                pos.y = pos.y + stepY;
            }
        }
    }

    protected void moveRight() {
        for (double stepX : tryStep) {
            if (!hasObstacle(pos.x + stepX, pos.y + velocity)) {
                pos.x = pos.x + stepX;
                pos.y = pos.y + velocity;
                return ;
            }
        }
    }

    protected void moveDown() {
        for (double stepY : tryStep) {
            if (!hasObstacle(pos.x + velocity, pos.y + stepY)) {
                pos.x = pos.x + velocity;
                pos.y = pos.y + stepY;
                return ;
            }
        }
    }
}


