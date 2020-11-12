package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.entities.move.Direction;
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

    protected void chooseRandomDirect() {
        int randomIndexInDirectList = new Random().nextInt(4);
        direct = Direction.directions[randomIndexInDirectList].getDirect();
        stepInDirect = 0;
    }

    /**
     * choose direct which decrease manhattan distance to bomber
     */
    protected void chooseAttackDirect() {
        double currentDistance = pos.distance(Bomber.INSTANCE.getPos());
        for (Direction dir : Direction.directions){
            Point tryPos = new Point(pos.x + velocity * dir.getTranslateX(), pos.y + velocity * dir.getTranslateY());
            if (!hasObstacle(tryPos.x, tryPos.y) && tryPos.distance(Bomber.INSTANCE.getPos()) < currentDistance) {
                direct = dir.getDirect();
                return;
            }
        }
    }
}



