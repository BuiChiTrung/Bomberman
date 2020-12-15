package uet.oop.bomberman.entities.still.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.StillEntity;
import uet.oop.bomberman.scene.Container;

public class SpeedItem extends Item {

    public SpeedItem(Point pos, Image img) {
        super(pos, img);
    }

    @Override
    public void update() {
    }

    @Override
    public void increaseBomberStrength() {
        Container.bomber.increaseSpeed();
    }
}
