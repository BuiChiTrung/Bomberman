package uet.oop.bomberman.entities.still.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.StillEntity;

public abstract class Item extends StillEntity {
    public Item(Point pos, Image img) {
        super(pos, img);
    }

    @Override
    public void update() {

    }

    public void affectBomber() {
        increaseBomberStrength();
        removableFromContainer = true;
    }

    public abstract void increaseBomberStrength();
}
