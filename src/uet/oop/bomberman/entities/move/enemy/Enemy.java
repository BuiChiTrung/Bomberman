package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.move.MovingEntity;

import java.util.Random;

import static javafx.scene.input.KeyCode.*;

public abstract class Enemy extends MovingEntity {
    protected Direction direction = Direction.RIGHT;
    public Enemy(double x, double y, Image img) {
        super(x, y, img);
        velocity = 0.125;
    }

    public void moveAlongDirection() {
        for (double step : tryStep) {
            double stepX;
            double stepY;
            if(direction.getX() != 0) {
                stepX = 0;
                stepY = step;
            }
            else {
                stepX = step;
                stepY = 0;
            }
            if (!hasObstacle(pos.x + stepX + velocity * direction.getX(), pos.y + stepY + velocity * direction.getY())) {
                pos.x = pos.x + stepX + velocity * direction.getX();
                pos.y = pos.y + stepY + velocity * direction.getY();
                return;
            }
        }
    }

    protected void updateDirectionAndStepInDirect(Direction nextDirection) {
        if (nextDirection.equals(direction))
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direction = nextDirection;
    }
    public abstract void move();
}


