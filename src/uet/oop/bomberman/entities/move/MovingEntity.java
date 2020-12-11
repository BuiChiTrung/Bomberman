package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.bomb.Bomb;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.timeline.MainScene;

import java.util.ArrayList;

import static java.lang.Math.*;

// moving object: bomber, enemy
public abstract class MovingEntity extends Entity {
    protected static final double acceptedPass = 0.8;
    protected static final double[] tryStep = {0, -acceptedPass * 2, acceptedPass * 2, -acceptedPass, acceptedPass};
    public Direction direction = Direction.RIGHT;       // manage direction of object
    protected int stepInDirect;                         // số bước liên tiếp đi theo cùng một hướng
    protected double velocity;
    protected boolean death = false;
    public MovingEntity(Point pos, Image img) {
        super(pos, img);
    }

    public void update() {
        if (onFlame() || (this instanceof Bomber && ((Bomber) this).collideWithEnemy()) || death) {
            changeToDeathImg();
        }
        else {
            move();
            updateImg();
        }
    }

    public abstract void changeToDeathImg();
    public abstract void move();
    public abstract void updateImg();

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

    public void updateDirectionAndStepInDirect(Direction newDirection) {
        if (direction != newDirection)
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direction = newDirection;
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

    /**
     * return danh sách các ô mà nhân vật đứng trên ô đó. VD: tọa độ nhân vật là (1.5, 1.0) => nhân vạt đứng trên ô (1.0, 1.0) và (2.0, 1.0)
     */
    public ArrayList<Point> getStandingCells(double x, double y) {
        ArrayList<Point> standingCells = new ArrayList<>();
        if (x != floor(x) && y != floor(y)) {
            standingCells.add(new Point(floor(x), floor(y)));
            standingCells.add(new Point(floor(x) + 1, floor(y)));
            standingCells.add(new Point(floor(x), floor(y) + 1));
            standingCells.add(new Point(floor(x) + 1, floor(y) + 1));
        }
        else if (x != floor(x)) {
            standingCells.add(new Point(floor(x), y));
            standingCells.add(new Point(floor(x) + 1, y));
        }
        else if (y != floor(y)) {
            standingCells.add(new Point(x, floor(y)));
            standingCells.add(new Point(x, floor(y) + 1));
        }
        else {
            standingCells.add(new Point(x, y));
        }

        return standingCells;
    }
}