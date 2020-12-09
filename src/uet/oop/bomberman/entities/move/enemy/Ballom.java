package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.util.ImgFactory;

public class Ballom extends Enemy {

    public Ballom(Point pos, Image img) {
        super(pos, img);
        attackRadius = 5;
    }

    private static final Image[][] imgState = ImgFactory.ballomImg;

    public Image[][] getImgState() {
        return imgState;
    }
}

