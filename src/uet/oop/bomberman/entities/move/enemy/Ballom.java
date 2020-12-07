package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.graphics.Sprite;
import java.util.Random;

public class Ballom extends Enemy {

    public Ballom(Point pos, Image img) {
        super(pos, img);
        attackRadius = 5;
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
}

