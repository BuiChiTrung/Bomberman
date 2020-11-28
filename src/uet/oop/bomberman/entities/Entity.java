package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;

public abstract class Entity {
    protected Point pos;
    protected Image img;
    protected boolean destroy = false;

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

    protected Entity getEntityAtPosition(Point it) {
        for (Bomb bomb : Container.bombs)
            if (it.isEquals(bomb.getPos()))
                return bomb;

        return Container.objects[(int)it.x][(int)it.y].get(Container.objects[(int)it.x][(int)it.y].size() - 1);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
}