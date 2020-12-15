package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Container;
import uet.oop.bomberman.util.Point;
import uet.oop.bomberman.util.Util;

import java.util.ArrayList;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public abstract class Entity {
    protected Point pos;
    protected Image img;
    protected boolean removableFromContainer = false;
    protected int imgId = -1;

    public Entity(Point pos, Image img) {
        this.pos = pos;
        this.img = img;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public boolean isRemovableFromContainer() {
        return removableFromContainer;
    }

    public void setRemovableFromContainer(boolean removableFromContainer) {
        this.removableFromContainer = removableFromContainer;
    }

    protected Entity getEntityAtPosition(Point it) {
        return Util.getLast(Container.stillEntities[(int)it.x][(int)it.y]);
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

    protected boolean onFlame() {
        ArrayList<Point> cells = getStandingCells(pos.x, pos.y);
        for (Point cell: cells) {
            if(Container.flames[(int)cell.x][(int)cell.y].isEmpty()) {
                continue;
            }
            return true;
        }
        return false;
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

    public double getAreaOfMostStandingCells() {
        if (pos.x % 1 == 0) {
            return Math.max(pos.y, 1 - pos.y);
        }
        return Math.max(pos.x, 1 - pos.x);
    }

    public void render(GraphicsContext gc){
        gc.drawImage(img, pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
}