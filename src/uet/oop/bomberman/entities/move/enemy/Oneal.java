package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.MoveUtil;

import java.util.Random;

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
    private boolean alreadyGetNextDestination = false;

    public Image[][] getImgState() {
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
        if(nextDestination.isEquals(pos) || MoveUtil.blocked(nextDestination) || !alreadyGetNextDestination) {
            alreadyGetNextDestination = true;
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

    public void randomWalk() {
        alreadyGetNextDestination = false;
        if (needToChangeDirect())
            updateDirectionAndStepInDirect(chooseRandomDirection());
        else
            updateDirectionAndStepInDirect(direction);

        Point oldPos = new Point(pos);
        collide = false;

        moveAlongDirection();
        if (oldPos.isEquals(pos))
            collide = true;
    }

    private boolean needToChangeDirect() {
        return collide || moveInOneDirectLongEnough();
    }

    /**
     * xác suất 66% đổi hướng khi đi thẳng liên tiếp >= 3 ô.
     */
    private boolean moveInOneDirectLongEnough() {
        if (stepInDirect % moveTimeToCrossOneCell == 0 && stepInDirect / moveTimeToCrossOneCell >= 3) {
            Random changeDirect = new Random();
            return changeDirect.nextInt(3) >= 2;
        }
        return false;
    }

    @Override
    public void move() {
        if(System.currentTimeMillis() - lastMoveTime < 20) {
            return ;
        }
        if(!reachable()) {
            randomWalk();
            lastMoveTime = System.currentTimeMillis();
            return ;
        }
        chase();
        lastMoveTime = System.currentTimeMillis();
    }
}