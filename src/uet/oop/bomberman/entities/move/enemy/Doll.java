package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.ImgFactory;
import uet.oop.bomberman.util.MoveUtil;

public class Doll extends Enemy {
    public Doll(Point pos, Image img) {
        super(pos, img);
        velocity = 0.125 / 8;
    }

    private static final Image[][] imgState = ImgFactory.dollImg;

    public Image[][] getImgState() {
        return imgState;
    }

    @Override
    public void changeToDeathImg() {
        imgId++;
        death = true;
        if (imgId == DESTROY_IMG_ID) {
            destroy = true;
        }
        else {
            img = getImgState()[1][imgId / NUMBER_OF_MOVE_TO_CHANGE_IMG];
        }
    }
    private final int numOfMoveImg = 6;
    private int step = 0;
    @Override
    public void updateImg() {
        img = imgState[0][step % numOfMoveImg];
    }

    @Override
    public void move() {
        Point nextDirection = new Point(Container.bomber.getPos().x - this.pos.x, Container.bomber.getPos().y - this.pos.y);
        double leng = MoveUtil.euclidDistance(nextDirection, new Point(0, 0));
        nextDirection.x /= leng;
        nextDirection.y /= leng;
        nextDirection.x *= velocity;
        nextDirection.y *= velocity;
        pos.x += nextDirection.x;
        pos.y += nextDirection.y;
    }


    @Override
    public void update() {
        if (onFlame() || death) {
            changeToDeathImg();
        }
        else {
            step++;
            move();
            updateImg();
        }
    }
}
