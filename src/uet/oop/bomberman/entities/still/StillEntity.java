package uet.oop.bomberman.entities.still;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.util.Point;

/**
 * objects than can't move.
 */
public abstract class StillEntity extends Entity {
    public StillEntity(Point pos, Image img) {
        super(pos, img);
    }

    public abstract void update();
}
