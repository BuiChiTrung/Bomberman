package uet.oop.bomberman.entities.move.enemy;

//<<<<<<< HEAD
//import javafx.scene.image.Image;
//import javafx.scene.input.KeyCode;
//import uet.oop.bomberman.entities.move.Bomber;
//import uet.oop.bomberman.graphics.Sprite;
//
//import java.util.Random;
//
//public class Balloom extends Enemy {
//    public Balloom(double x, double y, Image img) {
//        super(x, y, img);
//    }
//    private long lastChange = 0;
//    protected static double attackRadius = 1.0;
//
//    private static Image[] imgState = {
//            // 0 -> 2
//            Sprite.balloom_left0.getFxImage(),
//            Sprite.balloom_left1.getFxImage(),
//            Sprite.balloom_left2.getFxImage(),
//            // 3 -> 5
//            Sprite.balloom_right0.getFxImage(),
//            Sprite.balloom_right1.getFxImage(),
//            Sprite.balloom_right2.getFxImage(),
//            // 6
//            Sprite.balloom_dead.getFxImage()
//    };
//
//    @Override
//    public void update() {
//        while (lastChange != 0 && System.currentTimeMillis() - lastChange < 35) {
//
//        }
//
//        collide = true;
//
//        if (pos.distance(Bomber.INSTANCE.getPos()) <= attackRadius) {
//            chooseAttackDirect();
//        }
//
//        updatePos(direct);
//        updateDirectAndStepInDirect();
//        updateImg();
//
//        lastChange = System.currentTimeMillis();
//    }
//
//    protected void updateDirectAndStepInDirect() {
//        if (collide) {
//            chooseRandomDirect();
//        }
//        else {
//            stepInDirect = stepInDirect + 1;
//
//            // enemy can change direct after moving >= 3 cells consecutively in one direct
//            if (stepInDirect % moveTimeToCrossOneCell == 0 && stepInDirect / moveTimeToCrossOneCell >= 3) {
//                Random changeDirect = new Random();
//                if (changeDirect.nextInt(3) >= 2) {
//                    chooseRandomDirect();
//                }
//            }
//        }
//    }
//
//    @Override
//    protected void updateImg() {
//        if (direct == KeyCode.LEFT) imgIndex = 0;
//        else if (direct == KeyCode.RIGHT) imgIndex = 3;
//        img = imgState[imgIndex + (stepInDirect % (MOVE_TIME_TO_CHANGE_IMG * NUMBER_OF_IMG_IN_ONE_DIRECTION)) / MOVE_TIME_TO_CHANGE_IMG];
//    }
//
//
//=======
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.DirectionUtil;


public class Ballom extends Enemy {
    private long lastTimeChangeDirection = 0;
    private long lastMoveTime = 0;

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

    public static Image[][] getImgState() {
        return imgState;
    }

    /**
     * change direct after >= 300ms
     */
    public void changeDirection() {
        if(System.currentTimeMillis() - lastTimeChangeDirection > 300) {
            Direction newDirect = chooseNewDirect();
            updateDirectionAndStepInDirect(newDirect);
            lastTimeChangeDirection = System.currentTimeMillis();
        }
    }

    @Override
    public void move() {
        if(System.currentTimeMillis() - lastMoveTime < 20) {
            return ;
        }
        changeDirection();
        moveAlongDirection();
        lastMoveTime = System.currentTimeMillis();
    }

    private Direction chooseNewDirect() {
        int directionAsNumber = (int)(Math.random() * ((3) + 1));
        return DirectionUtil.getDirectionFromId(directionAsNumber);
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

