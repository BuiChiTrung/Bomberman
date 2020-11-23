package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected Point pos;
    protected Image img;


    public Entity(Point pos, Image img) {
        this.pos = pos;
        this.img = img;
    }

    public Point getPos() {
        return pos;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
}