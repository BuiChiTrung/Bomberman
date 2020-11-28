package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;

import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.move.MovingEntity;

public abstract class Enemy extends MovingEntity {
    protected static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 3;
    protected static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    public static final int DESTROY_IMG_ID = 1 * NUMBER_OF_MOVE_TO_CHANGE_IMG;

    public Enemy(Point pos, Image img) {
        super(pos, img);
        velocity = 0.125 / 2;
    }

    public static int getDestroyImgId() {
        return DESTROY_IMG_ID;
    }

    public abstract void changeToDeathImg();
}



