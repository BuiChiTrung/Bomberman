package uet.oop.bomberman.entities.still;

import javafx.scene.image.Image;
import uet.oop.bomberman.util.Point;

public class Wall extends StillEntity {

    public Wall(Point pos, Image img) {
        super(pos, img);
    }

    @Override
    public void update() {
    }
}
