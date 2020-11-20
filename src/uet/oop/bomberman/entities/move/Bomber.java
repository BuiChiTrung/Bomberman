package uet.oop.bomberman.entities.move;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.Util;

public class Bomber extends MovingEntity {

    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 2;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private static double[] tryStep;
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

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
        velocity = 0.125;
        tryStep = new double[]{0, -velocity * 2, velocity * 2, -velocity, velocity};
    }

    public void handle(KeyCode key) {
        if(key == KeyCode.RIGHT || key == KeyCode.LEFT || key == KeyCode.UP || key == KeyCode.DOWN) {
            updateDirectAndStepInDirect(DirectionUtil.getDirectionFromKeyCode(key));
            move();
        }
    }

    public void move() {
        for (double step : tryStep) {
            double stepX;
            double stepY;
            if(direction.getX() != 0) {
                stepX = 0;
                stepY = step;
            }
            else {
                stepX = step;
                stepY = 0;
            }
            if (!hasObstacle(pos.x + stepX + velocity * direction.getX(), pos.y + stepY + velocity * direction.getY())) {
                pos.x = pos.x + stepX + velocity * direction.getX();
                pos.y = pos.y + stepY + velocity * direction.getY();
                System.out.println(pos.x + " " + pos.y);
                return;
            }
        }
    }

    protected void updateDirectAndStepInDirect(Direction newDirection) {
        if (direction != newDirection)
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direction = newDirection;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img[DirectionUtil.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION], pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }

}