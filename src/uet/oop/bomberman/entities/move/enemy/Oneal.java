package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.ImgFactory;
import uet.oop.bomberman.util.MoveUtil;

public class Oneal extends Enemy {
    private long lastMoveTime = 0;
    private static final Image[][] imgState = ImgFactory.onealImg;

    public Image[][] getImgState() {
        return imgState;
    }

    public Oneal(Point pos, Image img) {
        super(pos, img);
        attackRadius = 99999;
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