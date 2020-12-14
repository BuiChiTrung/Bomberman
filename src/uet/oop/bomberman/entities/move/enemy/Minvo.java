package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.util.ImgFactory;

public class Minvo extends Enemy {

    public Minvo(Point pos, Image img) {
        super(pos, img);
        attackRadius = 8;
    }

    private static final Image[][] imgState = ImgFactory.minvoImg;

    public Image[][] getImgState() {
        return imgState;
    }
}

