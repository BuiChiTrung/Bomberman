package uet.oop.bomberman.entities.move.enemy;

//<<<<<<< HEAD
//import javafx.scene.image.Image;
//import javafx.scene.input.KeyCode;
//import uet.oop.bomberman.entities.move.Bomber;
//import uet.oop.bomberman.graphics.Sprite;
//
//import java.util.Random;
//
//public class Balloon extends Enemy {
//    public Balloon(double x, double y, Image img) {
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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.Util;


public class Balloon extends Enemy {
    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 2;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private long lastTimeChangeDirection = 0;
    private long lastMoveTime = 0;

    public Balloon(Point pos, Image img) {
        super(pos, img);
    }

    private static final Image[][] img = {
            //LEFT
            {Sprite.balloom_left0.getFxImage(),
             Sprite.balloom_left1.getFxImage(),
             Sprite.balloom_left2.getFxImage()},
            //UP
            {Sprite.balloom_left0.getFxImage(),
             Sprite.balloom_left1.getFxImage(),
             Sprite.balloom_left2.getFxImage()},
            //RIGHT
            {Sprite.balloom_right0.getFxImage(),
             Sprite.balloom_right1.getFxImage(),
             Sprite.balloom_right2.getFxImage()},
            //UP
            {Sprite.balloom_right0.getFxImage(),
             Sprite.balloom_right1.getFxImage(),
             Sprite.balloom_right2.getFxImage()},

            {Sprite.balloom_dead.getFxImage()}

    };

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
        int directionAsNumber = (int)(Math.random() * ((3 - 0) + 1));
        return DirectionUtil.getDirectionFromId(directionAsNumber);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img[DirectionUtil.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION], pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
}
