package uet.oop.bomberman.entities.still;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

/**
 * objects than can't move.
 */
public class StillEntity extends Entity {
    public StillEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}
