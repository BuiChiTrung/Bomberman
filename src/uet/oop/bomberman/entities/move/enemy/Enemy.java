package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;

import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.move.MovingEntity;
import uet.oop.bomberman.timeline.CanvasManager;
import uet.oop.bomberman.util.DirectionUtil;

public abstract class Enemy extends MovingEntity {
    protected static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 3;
    protected static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    protected static int moveTimeToCrossOneCell;
    public static final int DESTROY_IMG_ID = 1 * NUMBER_OF_MOVE_TO_CHANGE_IMG; // 1 = enemy's death img number
    protected boolean collide = false;

    public Enemy(Point pos, Image img) {
        super(pos, img);
        velocity = 0.125 / 2;
        moveTimeToCrossOneCell = (int) (1 / velocity);
    }

    protected Direction chooseRandomDirection() {
        int directionAsNumber = (int)(Math.random() * ((3) + 1));
        return DirectionUtil.getDirectionFromId(directionAsNumber);
    }

    public void updateImg() {
        img = getImgState()[DirectionUtil.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION];
    }

    @Override
    public void changeToDeathImg() {
        imgId++;
        if (imgId == DESTROY_IMG_ID) {
            destroy = true;
        }
        else {
            img = getImgState()[4][imgId / NUMBER_OF_MOVE_TO_CHANGE_IMG];
        }
    }

    /**
     * factory design pattern.
     */
    public Image[][] getImgState() {
        return new Image[CanvasManager.ROW][CanvasManager.COLUMN];
    }
}



