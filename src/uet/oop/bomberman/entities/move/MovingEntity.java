package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.timeline.CanvasManager;
import uet.oop.bomberman.timeline.Container;

import java.util.ArrayList;

import static java.lang.Math.*;

// moving object: bomber, enemy
public abstract class MovingEntity extends Entity {
    protected static final double acceptedPass = 0.125;
    protected static final double[] tryStep = {0, -acceptedPass * 2, acceptedPass * 2, -acceptedPass, acceptedPass};
    public Direction direction = Direction.RIGHT;       // manage direction of object
    protected int stepInDirect;                         // số bước liên tiếp đi theo cùng một hướng
    protected double velocity = 0.125;

    public MovingEntity(Point pos, Image img) {
        super(pos, img);
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
        if (x < 0 || x > CanvasManager.ROW) return true;
        if (y < 0 || y > CanvasManager.COLUMN) return true;

        ArrayList<Point> standingCells = getStandingCells(x, y);

        for (Point it : standingCells) {
            Entity lastEntity = Container.objects[(int)it.x][(int)it.y].get(Container.objects[(int)it.x][(int)it.y].size() - 1);
            if (lastEntity instanceof Brick || lastEntity instanceof Wall) return true;
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

    /**
     * Trả về ô mà Entity chiếm diện tích nhiều nhất
     */
    public Point getMostAreaStandingCells(){
        if(pos.y % 1 == 0) {
            if(pos.x - (int)pos.x <= 0.5) {
                return new Point(floor(pos.x), pos.y);
            }
            else {
                return new Point(ceil(pos.x), pos.y);
            }
        }
        if(pos.y - (int)pos.y <= 0.5) {
            return new Point(pos.x, floor(pos.y));
        }
        else {
            return new Point(pos.x, ceil(pos.y));
        }
    }
}