package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.Util;

public class Oneal extends Enemy {
    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 2;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;

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

    protected void updateDirectionAndStepInDirect(KeyCode key) {
        if (key != direct)
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direct = key;
    }

    public Oneal(double x, double y, Image img) {
        super(x, y, img);
        velocity /= 4;
    }
    private Point nextDestination = pos;
    public void chase() {
        System.out.println(pos.x + " " + pos.y + " " + nextDestination.x + " " + nextDestination.y);
        if(nextDestination.isEquals(pos)) {
            pos = getMostAreaStandingCells();
            nextDestination = Util.getNextDestination(pos, Util.getDirection(Container.directionToBomber[(int)pos.x][(int)pos.y]));
            updateDirectionAndStepInDirect(Util.getDirection(Container.directionToBomber[(int)pos.x][(int)pos.y]));
        }
        moveAlongDirection();
    }
    @Override
    public void move() {
        chase();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img[Util.getDirectionId(direct)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION], pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
}