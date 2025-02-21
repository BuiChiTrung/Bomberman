package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Container;
import uet.oop.bomberman.util.Point;
import uet.oop.bomberman.util.Util;

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

    protected boolean onFlame() {
        Point pos = getMostAreaStandingCells();
        return !Container.flames[(int)pos.x][(int)pos.y].isEmpty();
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