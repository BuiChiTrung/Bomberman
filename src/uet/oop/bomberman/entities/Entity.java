package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.Util;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public abstract class Entity {
    protected Point pos;
    protected Image img;
    protected boolean destroy = false;
    protected int imgId = -1;

    public Entity(Point pos, Image img) {
        this.pos = pos;
        this.img = img;
    }

    public Point getPos() {
        return pos;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
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