package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;
import uet.oop.bomberman.util.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.util.Point;
import uet.oop.bomberman.entities.still.bomb.Bomb;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.scene.MainScene;
import uet.oop.bomberman.util.SoundUtil;

import java.util.ArrayList;

import static java.lang.Math.*;

// moving object: bomber, enemy
public abstract class MovingEntity extends Entity {
    protected static final double acceptedPass = 0.8;
    public Direction direction = Direction.RIGHT;       // manage direction of object
    protected int stepInDirect;                         // số bước liên tiếp đi theo cùng một hướng
    protected double velocity;
    protected boolean death = false;

    public MovingEntity(Point pos, Image img) {
        super(pos, img);
    }

    public void update() {
        if (death || onFlame() || (this instanceof Bomber && ((Bomber) this).collideWithEnemy())) {
            death = true;
            changeToDeathImg();
        }
        else if (!death){
            move();
            updateImg();
        }
    }

    public abstract void changeToDeathImg();
    public abstract void move();
    public abstract void updateImg();

    public void updateDirectionAndStepInDirect(Direction newDirection) {
        if (direction != newDirection)
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direction = newDirection;
    }

    public void moveAlongDirection() {
        if(!hasObstacle(pos.x + direction.getX() * velocity, pos.y + direction.getY() * velocity)) {
            pos.x += direction.getX() * velocity;
            pos.y += direction.getY() * velocity;
            return ;
        }
        if(getAreaOfMostStandingCells() > acceptedPass) {
            Point newPos = getMostAreaStandingCells();
            if(!hasObstacle(newPos.x + direction.getX() * velocity, newPos.y + direction.getY() * velocity)) {
                pos.x = newPos.x + direction.getX() * velocity;
                pos.y = newPos.y + direction.getY() * velocity;
            }
        }
    }

    /**
     * check vị trí đang đứng có vật cản nào ko
     */
    public boolean hasObstacle(double x, double y) {
        if (x < 0 || x > MainScene.ROW) return true;
        if (y < 0 || y > MainScene.COLUMN) return true;

        ArrayList<Point> standingCells = getStandingCells(x, y);

        for (Point it : standingCells) {
            Entity entity = getEntityAtPosition(it);
            if (entity instanceof Brick || entity instanceof Wall)
                return true;
            if (entity instanceof Bomb) {
                if (!(this instanceof Bomber && ((Bomb) entity).isOnBomberFoot()))
                    return true;
            }
        }
        return false;
    }

}