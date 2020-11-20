package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.Util;

import java.nio.file.DirectoryIteratorException;

public class Oneal extends Enemy {
    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 2;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private static long lastTimeChangeDirection = 0;

    private Image img[][] = {

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

    public Oneal(double x, double y, Image img) {
        super(x, y, img);
        velocity /= 4;
    }
    private Point nextDestination = pos;

    /**
     * Đuổi theo Bomber.
     */
    public void chase() {
        //System.out.println(pos.x + " " + pos.y + " " + nextDestination.x + " " + nextDestination.y + " " + Util.getDirectionId(direct));
        if(nextDestination.isEquals(pos) || Container.Objects[(int)nextDestination.x][(int)nextDestination.y].get(Container.Objects[(int)nextDestination.x][(int)nextDestination.y].size() - 1) instanceof Brick || Container.Objects[(int)nextDestination.x][(int)nextDestination.y].get(Container.Objects[(int)nextDestination.x][(int)nextDestination.y].size() - 1) instanceof Wall) {
            pos = getMostAreaStandingCells();
            nextDestination = Util.getNextDestination(pos, DirectionUtil.getDirectionFromId(Container.directionToBomber[(int)pos.x][(int)pos.y]));
            updateDirectionAndStepInDirect(DirectionUtil.getDirectionFromId(Container.directionToBomber[(int)pos.x][(int)pos.y]));
        }
        moveAlongDirection();
    }

    public boolean reachable() {
        Point mostAreaCell = getMostAreaStandingCells();
        return Container.directionToBomber[(int)mostAreaCell.x][(int)mostAreaCell.y] != 4;
    }

    private Direction chooseNewDirection() {
        int directionAsNumber = (int)(Math.random() * ((3 - 0) + 1));
        System.out.print(directionAsNumber);
        return DirectionUtil.getDirectionFromId(directionAsNumber);
    }

    public void changeDirection() {
        if(System.currentTimeMillis() - lastTimeChangeDirection > 90) {
            Direction newDirect = chooseNewDirection();
            updateDirectionAndStepInDirect(newDirect);
            lastTimeChangeDirection = System.currentTimeMillis();
        }
    }

    @Override
    public void move() {
        if(!reachable()) {
            changeDirection();
            moveAlongDirection();
        }
        chase();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img[DirectionUtil.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION], pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
}