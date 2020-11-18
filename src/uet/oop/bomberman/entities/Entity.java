package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected Point pos;
    protected Image img;


    public Entity( double x, double y, Image img) {
        this.pos = new Point(x, y);
        this.img = img;
    }

    /**
     * allow changing background color of png image
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(img, pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
    public abstract void update();
}