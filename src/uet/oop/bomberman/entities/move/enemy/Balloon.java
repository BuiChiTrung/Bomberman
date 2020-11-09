package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloon extends Enemy {
    public Balloon(double x, double y, Image img) {
        super(x, y, img);
    }
    private long lastChange = 0;

    private static Image[] imgState = {
            // 0 -> 2
            Sprite.balloom_left0.getFxImage(),
            Sprite.balloom_left1.getFxImage(),
            Sprite.balloom_left2.getFxImage(),
            // 3 -> 5
            Sprite.balloom_right0.getFxImage(),
            Sprite.balloom_right1.getFxImage(),
            Sprite.balloom_right2.getFxImage(),
            // 6
            Sprite.balloom_dead.getFxImage()
    };

    @Override
    public void update() {
        collide = true;

        while (lastChange != 0 && System.currentTimeMillis() - lastChange < 35) {

        }

        addToModifiedObjects(pos);
        updatePos(direct);
        updateDirectAndStepInDirect();
        updateImg();
        addToModifiedObjects(pos);

        lastChange = System.currentTimeMillis();
    }

    protected void updateDirectAndStepInDirect() {
        if (collide) {
            chooseNewDirect();
        }
        else {
            stepInDirect = stepInDirect + 1;

            // enemy can change direct after moving >= 3 cells consecutively in one direct
            if (stepInDirect % moveTimeToCrossOneCell == 0 && stepInDirect / moveTimeToCrossOneCell >= 3) {
                Random changeDirect = new Random();
                if (changeDirect.nextInt(3) >= 2) {
                    System.out.println("hahah");
                    chooseNewDirect();
                }
            }
        }
    }

    private void chooseNewDirect() {
        Random randomIndexInDirectList = new Random();
        direct = directList[randomIndexInDirectList.nextInt(4)];
        stepInDirect = 0;
    }

    @Override
    protected void updateImg() {
        if (direct == KeyCode.LEFT) imgIndex = 0;
        else if (direct == KeyCode.RIGHT) imgIndex = 3;
        img = imgState[imgIndex + (stepInDirect % (MOVE_TIME_TO_CHANGE_IMG * NUMBER_OF_IMG_IN_ONE_DIRECTION)) / MOVE_TIME_TO_CHANGE_IMG];
    }


}
