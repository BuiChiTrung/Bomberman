package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.MoveUtil;

public class Oneal extends Enemy {
    private long lastMoveTime = 0;
    private static final Image[][] imgState = {
            {Sprite.oneal_left0.getFxImage(),
                    Sprite.oneal_left1.getFxImage(),
                    Sprite.oneal_left2.getFxImage()} ,

            {Sprite.oneal_left0.getFxImage(),
                    Sprite.oneal_left1.getFxImage(),
                    Sprite.oneal_left2.getFxImage()} ,

            {Sprite.oneal_right0.getFxImage(),
                    Sprite.oneal_right1.getFxImage(),
                    Sprite.oneal_right2.getFxImage()} ,

            {Sprite.oneal_right0.getFxImage(),
                    Sprite.oneal_right1.getFxImage(),
                    Sprite.oneal_right2.getFxImage()} ,

            {Sprite.oneal_dead.getFxImage()}
    };

    public static Image[][] getImgState() {
        return imgState;
    }

    public Oneal(Point pos, Image img) {
        super(pos, img);
    }

    private Point nextDestination = pos;

    /**
     * Chase Bomber.
     */
    public void chase() {
        //System.out.println(pos.x + " " + pos.y + " " + nextDestination.x + " " + nextDestination.y + " " + DirectionUtil.getDirectionId(direction));
        if(nextDestination.isEquals(pos) || MoveUtil.blocked(nextDestination)) {
            pos = getMostAreaStandingCells();
            nextDestination = MoveUtil.getNextDestination(pos, DirectionUtil.getDirectionFromId(Container.directionToBomber[(int)pos.x][(int)pos.y]));
            updateDirectionAndStepInDirect(DirectionUtil.getDirectionFromId(Container.directionToBomber[(int)pos.x][(int)pos.y]));
        }
        moveAlongDirection();
    }

    public boolean reachable() {
        Point mostAreaCell = getMostAreaStandingCells();
        return Container.directionToBomber[(int)mostAreaCell.x][(int)mostAreaCell.y] != 4;
    }

    private Direction chooseNewDirection() {
        int directionAsNumber = (int)(Math.random() * ((3) + 1));
        return DirectionUtil.getDirectionFromId(directionAsNumber);
    }

    public void changeDirection() {
        Direction newDirect = chooseNewDirection();
        updateDirectionAndStepInDirect(newDirect);
    }

    void RandomWalk() {
        changeDirection();
        moveAlongDirection();
    }

    @Override
    public void move() {
        if(System.currentTimeMillis() - lastMoveTime < 20) {
            return ;
        }
        if(!reachable()) {
            RandomWalk();
            lastMoveTime = System.currentTimeMillis();
            return ;
        }
        chase();
        lastMoveTime = System.currentTimeMillis();
    }

    @Override
    public void updateImg() {
        img = imgState[DirectionUtil.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION];
    }

    @Override
    public void changeToDeathImg() {
        imgId++;
        if (imgId == DESTROY_IMG_ID) {
            destroy = true;
        }
        else {
            img = imgState[4][imgId / NUMBER_OF_MOVE_TO_CHANGE_IMG];
        }
    }
}