package uet.oop.bomberman.entities.still.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.util.Point;
import uet.oop.bomberman.scene.Container;

public class BombItem extends Item {

    public BombItem(Point pos, Image img) {
        super(pos, img);
    }

    @Override
    public void update() {
    }

    @Override
    public void increaseBomberStrength() {
        Container.bomber.increaseBombNumber();
    }
}
