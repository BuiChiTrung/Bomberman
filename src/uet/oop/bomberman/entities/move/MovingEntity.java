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

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;
import static javafx.scene.input.KeyCode.RIGHT;

// moving object: bomber, enemy
public class MovingEntity extends Entity {
    protected KeyCode direct = RIGHT; // manage direction of object
    protected int stepInDirect; // số bước liên tiếp đi theo cùng một hướng lấy mod 3 (nếu rẽ => reset về 0)
    protected double velocity;

    public MovingEntity(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

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

    public boolean hasObstacle(double x, double y) {
        if (x < 0 || x > BombermanGame.WIDTH) return true;
        if (y < 0 || y > BombermanGame.HEIGHT) return true;

        ArrayList<Point> standingCells = getStandingCells(x, y);

        for (Point it : standingCells) {
            Entity lastEntity = BombermanGame.stillObjects[(int)it.x][(int)it.y].get(BombermanGame.stillObjects[(int)it.x][(int)it.y].size() - 1);
            if (lastEntity instanceof Brick || lastEntity instanceof Wall) return true;
        }

        return false;
    }

    public void addToModifiedObjects(Point pos) {
        ArrayList<Point> standingCells = getStandingCells(pos.x, pos.y);

        for (Point it: standingCells) {
            BombermanGame.modifiedObjects.add(it);
        }
    }
}
