package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;

import uet.oop.bomberman.util.Direction;
import uet.oop.bomberman.util.Point;
import uet.oop.bomberman.entities.move.MovingEntity;
import uet.oop.bomberman.scene.MainScene;
import uet.oop.bomberman.scene.Container;
import uet.oop.bomberman.util.ImgFactory;
import uet.oop.bomberman.util.MoveUtil;
import uet.oop.bomberman.util.Util;

import java.util.Random;

public abstract class Enemy extends MovingEntity {
    protected static int NUMBER_OF_MOVE_TO_CHANGE_IMG = 3;
    protected static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    protected static int moveTimeToCrossOneCell;
    public static final int DESTROY_IMG_ID = 1 * NUMBER_OF_MOVE_TO_CHANGE_IMG; // 1 = enemy's death img number
    protected boolean collide = false;
    protected boolean alreadyGetNextDestination = false;
    protected double attackRadius;
    protected long lastMoveTime = 0;
    protected boolean death = false;

    public Enemy(Point pos, Image img) {
        super(pos, img);
        Container.enemyLeft++;
        velocity = 0.125 / 2;
        moveTimeToCrossOneCell = (int) (1 / velocity);
    }

    protected Direction chooseRandomDirection() {
        int directionAsNumber = (int)(Math.random() * ((3) + 1));
        return Direction.getDirectionFromId(directionAsNumber);
    }

    public void updateImg() {
        img = getImgState()[Direction.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION];
    }

    @Override
    public void changeToDeathImg() {
        imgId++;
        death = true;
        if (imgId == DESTROY_IMG_ID) {
            removableFromContainer = true;
            if (this instanceof Minvo) {
                Container.enemies.add(new Ballom(Util.findRandomGrassCell(), ImgFactory.ballomImg[2][0]));
                Container.enemies.add(new Ballom(Util.findRandomGrassCell(), ImgFactory.ballomImg[2][0]));
            }
        }
        else
            img = getImgState()[4][imgId / NUMBER_OF_MOVE_TO_CHANGE_IMG];
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

    private Point nextDestination = pos;

    /**
     * Chase Bomber.
     */
    public void chase() {
        //System.out.println(pos.x + " " + pos.y + " " + nextDestination.x + " " + nextDestination.y + " " + DirectionUtil.getDirectionId(direction));
        if(nextDestination.isEquals(pos) || MoveUtil.blocked(nextDestination) || !alreadyGetNextDestination) {
            alreadyGetNextDestination = true;
            pos = getMostAreaStandingCells();
            nextDestination = MoveUtil.getNextDestination(pos, Direction.getDirectionFromId(Container.directionToBomber[(int)pos.x][(int)pos.y]));
            updateDirectionAndStepInDirect(Direction.getDirectionFromId(Container.directionToBomber[(int)pos.x][(int)pos.y]));
        } else {
            updateDirectionAndStepInDirect(this.direction);
        }
        moveAlongDirection();
    }

    public boolean reachable() {
        Point mostAreaCell = getMostAreaStandingCells();
        return Container.directionToBomber[(int)mostAreaCell.x][(int)mostAreaCell.y] != 4;
    }

    public void move() {
        if(System.currentTimeMillis() - lastMoveTime < 20) {
            return ;
        }
        //System.out.println(reachable() + " " + (Util.getDistance(this.pos, Container.bomber.getPos()) < attackRadius));
        if(!reachable() || pos.distance(Container.bomber.getPos())> attackRadius) {
            randomWalk();
            lastMoveTime = System.currentTimeMillis();
            return ;
        }
        chase();
        lastMoveTime = System.currentTimeMillis();
    }
    /**
     * factory design pattern.
     */
    public Image[][] getImgState() {
        return new Image[MainScene.ROW][MainScene.COLUMN];
    }
}



