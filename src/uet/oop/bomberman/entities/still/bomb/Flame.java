package uet.oop.bomberman.entities.still.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.StillEntity;
import uet.oop.bomberman.util.ImgFactory;


public class Flame extends StillEntity {
    private static final int NUMBER_OF_FRAME_TO_CHANGE_IMG = 1;
    private static final int DESTROY_IMG_ID = 24 * NUMBER_OF_FRAME_TO_CHANGE_IMG;
    public static Image[] imgState = ImgFactory.flameImg;

    public Flame(Point pos, Image img) {
        super(pos, img);
    }

    public void update() {
        updateImg();
    }

    /**
     * destroy khi chay het 24 anh
     */
    private void updateImg() {
        imgId++;
        if (imgId == DESTROY_IMG_ID) {
            removableFromContainer = true;
        }
        else img = imgState[imgId / NUMBER_OF_FRAME_TO_CHANGE_IMG];
    }
}
