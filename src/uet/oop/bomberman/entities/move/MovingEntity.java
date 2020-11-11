package uet.oop.bomberman.entities.move;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.CanvasManager;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;
import static javafx.scene.input.KeyCode.RIGHT;

// moving object: bomber, enemy
public abstract class MovingEntity extends Entity {
    protected static final int NUMBER_OF_IMG_IN_ONE_DIRECTION = 3;

    protected static KeyCode[] directList = {KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT};
    protected KeyCode direct = RIGHT;       // manage direction of object
    protected int stepInDirect;             // số bước liên tiếp đi theo cùng một hướng lấy mod 3 (nếu rẽ => reset về 0)

    protected double velocity;
    protected boolean collide;

    protected int imgIndex;

    public MovingEntity(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    protected void updateDirectAndStepInDirect(KeyCode eventDirection) {}

    protected void updateDirectAndStepInDirect(){}

    protected abstract void updateImg();

    protected void updatePos(KeyCode eventDirection) {
        switch (eventDirection) {
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

    protected abstract void moveUp();
    protected abstract void moveLeft();
    protected abstract void moveDown();
    protected abstract void moveRight();

    /**
     * check vị trí đang đứng có vật cản nào ko
     */
    public boolean hasObstacle(double x, double y) {
        if (x < 0 || x > CanvasManager.WIDTH) return true;
        if (y < 0 || y > CanvasManager.HEIGHT) return true;

        ArrayList<Point> standingCells = getStandingCells(x, y);

        for (Point it : standingCells) {
            Entity lastEntity = CanvasManager.stillObjects[(int)it.x][(int)it.y].get(CanvasManager.stillObjects[(int)it.x][(int)it.y].size() - 1);
            if (lastEntity instanceof Brick || lastEntity instanceof Wall) return true;
        }

        return false;
    }

    /**
     * return danh sách các ô mà nhân vật đứng trên ô đó. VD: tọa độ nhân vật là (1.5, 1.0) => nhân vạt đứng trên ô (1.0, 1.0) và (2.0, 1.0)
     */
    public ArrayList<Point> getStandingCells(double x, double y) {
        ArrayList<Point> standingCells = new ArrayList<Point>();
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
