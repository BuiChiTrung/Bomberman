package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;

public class Bomb extends Entity {
    public Bomb(Point pos, Image img) { super(pos, img); }
    private static final double timeToExplode = 3000;               // 3000ms
    private boolean bomberOnBomb;                             // check whether bomber still standing on bomb or not
    private long placeMoment;                                 // moment bomb is placed


}
