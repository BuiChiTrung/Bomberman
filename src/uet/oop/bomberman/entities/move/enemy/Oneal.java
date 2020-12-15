package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.util.Point;
import uet.oop.bomberman.util.ImgFactory;

public class Oneal extends Enemy {
    private static final Image[][] imgState = ImgFactory.onealImg;

    public Image[][] getImgState() {
        return imgState;
    }

    public Oneal(Point pos, Image img) {
        super(pos, img);
        attackRadius = 99999;
        NUMBER_OF_MOVE_TO_CHANGE_IMG = 5;
    }

}