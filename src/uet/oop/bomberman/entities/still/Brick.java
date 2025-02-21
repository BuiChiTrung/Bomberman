package uet.oop.bomberman.entities.still;

import javafx.scene.image.Image;
import uet.oop.bomberman.util.Point;
import uet.oop.bomberman.util.ImgFactory;

public class Brick extends StillEntity {
    private int imgId = -1;
    private static final int NUMBER_OF_FRAME_TO_CHANGE_IMG = 8;
    private static final int DESTROY_IMG_ID = 3 * NUMBER_OF_FRAME_TO_CHANGE_IMG;

    public Brick(Point pos, Image img) {
        super(pos, img);
    }

    private final Image[] imgState = ImgFactory.brickImg;

    @Override
    public void update() {
        if (onFlame()) {
            imgId++;
            if (imgId == DESTROY_IMG_ID) {
                removableFromContainer = true;
            }
            else {
                img = imgState[imgId / NUMBER_OF_FRAME_TO_CHANGE_IMG];
            }
        }
    }
}
