package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.util.Point;
import uet.oop.bomberman.util.ImgFactory;

public class Ballom extends Enemy {

    public Ballom(Point pos, Image img) {
        super(pos, img);
        attackRadius = 3;
    }

    private static final Image[][] imgState = ImgFactory.ballomImg;

    public Image[][] getImgState() {
        return imgState;
    }
}

