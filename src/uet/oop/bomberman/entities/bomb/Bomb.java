package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;

public class Bomb extends Entity {
    private static final double timeToExplode = 3000;         // 3000ms
    private boolean onBomberFoot;                             // check whether bomber still standing on bomb or not
    private long placeMoment;                                 // moment bomb is placed
    private static final int NUMBER_OF_FRAME_TO_CHANGE_IMG = 10;
    private static final int NUMBER_OF_IMG = 3;
    private int imgId = 0;

    private Image[] imgArray = {
            Sprite.bomb0.getFxImage(),
            Sprite.bomb1.getFxImage(),
            Sprite.bomb2.getFxImage()
    };

    public Bomb(Point pos, Image img) {
        super(pos, img);
        onBomberFoot = true;
        placeMoment  = System.currentTimeMillis();
    }

    public boolean isOnBomberFoot() {
        return onBomberFoot;
    }

    public void update() {
        updateImg();
        if (pos.distance(Container.bomber.getPos()) >= 1) onBomberFoot = false;

    }

    private void updateImg() {
        imgId = (imgId + 1) % (NUMBER_OF_FRAME_TO_CHANGE_IMG * NUMBER_OF_IMG);
        img = imgArray[imgId / NUMBER_OF_FRAME_TO_CHANGE_IMG];
    }

    public void render(GraphicsContext gc) {

        gc.drawImage(img, pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }


}
