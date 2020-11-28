package uet.oop.bomberman.entities.still;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends StillEntity {
    private int imgId = -1;
    private static final int NUMBER_OF_FRAME_TO_CHANGE_IMG = 5;
    private static final int DESTROY_IMG_ID = 3 * NUMBER_OF_FRAME_TO_CHANGE_IMG;

    public Brick(Point pos, Image img) {
        super(pos, img);
    }

    private static final Image[] imgState = {
        Sprite.brick_exploded0.getFxImage(),
        Sprite.brick_exploded1.getFxImage(),
        Sprite.brick_exploded2.getFxImage(),
    };

    @Override
    public void update() {
        if (onFlame()) {
            imgId++;
            if (imgId == DESTROY_IMG_ID) {
                destroy = true;
            }
            else {
                img = imgState[imgId / NUMBER_OF_FRAME_TO_CHANGE_IMG];
            }
        }
    }
}
