package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.graphics.Sprite;
import java.util.Random;

public class Ballom extends Enemy {
    private static final int attackRadius = 0;

    public Ballom(Point pos, Image img) {
        super(pos, img);
    }

    private static final Image[][] imgState = {
            //LEFT
            {Sprite.ballom_left0.getFxImage(),
                    Sprite.ballom_left1.getFxImage(),
                    Sprite.ballom_left2.getFxImage()},
            //UP
            {Sprite.ballom_left0.getFxImage(),
                    Sprite.ballom_left1.getFxImage(),
                    Sprite.ballom_left2.getFxImage()},
            //RIGHT
            {Sprite.ballom_right0.getFxImage(),
                    Sprite.ballom_right1.getFxImage(),
                    Sprite.ballom_right2.getFxImage()},
            //UP
            {Sprite.ballom_right0.getFxImage(),
                    Sprite.ballom_right1.getFxImage(),
                    Sprite.ballom_right2.getFxImage()},
            // DIE
            {Sprite.ballom_dead.getFxImage()}

    };

    public Image[][] getImgState() {
        return imgState;
    }

    @Override
    public void move() {
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
}

