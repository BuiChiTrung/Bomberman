package uet.oop.bomberman.entities.move;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.Util;

public class Bomber extends MovingEntity {

    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 5;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private static final Image[][] img = {
            //LEFT:0
            {Sprite.player_left_0.getFxImage(),
                    Sprite.player_left_1.getFxImage(),
                    Sprite.player_left_2.getFxImage()},
            //UP:1
            {Sprite.player_up_0.getFxImage(),
                    Sprite.player_up_1.getFxImage(),
                    Sprite.player_up_2.getFxImage()},
            //RIGHT:2
            {Sprite.player_right_0.getFxImage(),
                    Sprite.player_right_1.getFxImage(),
                    Sprite.player_right_2.getFxImage()},
            //DOWN:3
            {Sprite.player_down_0.getFxImage(),
                    Sprite.player_down_1.getFxImage(),
                    Sprite.player_down_2.getFxImage()},
            //DEAD:4
            {Sprite.player_dead_0.getFxImage(),
                    Sprite.player_dead_1.getFxImage(),
                    Sprite.player_dead_2.getFxImage()}
    };

    private int bombPower = 1;
    private int bombNumber = 1;
    private boolean arrowKeyIsRelease = true;

    public Bomber(Point pos, Image img) {
        super(pos, img);
        velocity = 0.125;
    }

    public void move() {
        if(!arrowKeyIsRelease) {
            updateDirectionAndStepInDirect(direction);
            moveAlongDirection();
        }
    }

    public void handlePress(KeyCode key) {
        if(key == KeyCode.RIGHT || key == KeyCode.LEFT || key == KeyCode.UP || key == KeyCode.DOWN) {
            updateDirectionAndStepInDirect(DirectionUtil.getDirectionFromKeyCode(key));
            arrowKeyIsRelease = false;
        }
        if(key == KeyCode.SPACE) {
            //if (Container.bombs.size() < bombNumber)
                Container.bomber.placeBomb();
        }
    }

    public void handleRelease(KeyCode key) {
        if(key == KeyCode.RIGHT || key == KeyCode.LEFT || key == KeyCode.UP || key == KeyCode.DOWN) {
            arrowKeyIsRelease = true;
        }
    }

    private void placeBomb() {
        Bomb bomb = new Bomb(Util.getMostAreaStandingCells(pos), Sprite.bomb0.getFxImage());
        Container.bombs.add(bomb);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img[DirectionUtil.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION], pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }

}