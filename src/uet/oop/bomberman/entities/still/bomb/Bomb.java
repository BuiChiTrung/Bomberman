package uet.oop.bomberman.entities.still.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.StillEntity;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.scene.Container;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.ImgFactory;
import uet.oop.bomberman.util.SoundUtil;

public class Bomb extends StillEntity {
    private static final double timeToExplode = 2500;         // milliseconds
    private boolean onBomberFoot;                             // check whether bomber still standing on bomb or not
    private long placeMoment;                                 // moment bomb is placed
    private static final int NUMBER_OF_FRAME_TO_CHANGE_IMG = 5;
    private static final int NUMBER_OF_IMG = 3;
    private int imgId = -1;

    private final Image[] imgArray = ImgFactory.bombImg;

    public Bomb(Point pos, Image img) {
        super(pos, img);
        onBomberFoot = true;
        placeMoment = System.currentTimeMillis();
    }

    public boolean isOnBomberFoot() {
        return onBomberFoot;
    }

    @Override
    public void update() {
        updateImg();
        if (pos.distance(Container.bomber.getPos()) >= 1) onBomberFoot = false;  // bomb becomes an obstacle for bomber
        if (System.currentTimeMillis() - placeMoment > timeToExplode || onFlame()) {
            explode();
            SoundUtil.playExplodingSound();
        }
    }

    /**
     * handle bomb img when bomb hasn't exploded
     */
    private void updateImg() {
        imgId = (imgId + 1) % (NUMBER_OF_FRAME_TO_CHANGE_IMG * NUMBER_OF_IMG);
        img = imgArray[imgId / NUMBER_OF_FRAME_TO_CHANGE_IMG];
    }

    private void explode() {
        createFlame();
        Container.bomber.updateCurrentPlacedBomb();
        removableFromContainer = true;
    }


    private void createFlame() {
        addFlameAtPosition(pos);

        for (int directId = 0; directId <= 3; ++directId) {
            Direction dir = DirectionUtil.getDirectionFromId(directId);

            for (int i = 1; i <= Container.bomber.getBombPower(); ++i) {
                Point flamePos = new Point(pos.x + dir.getX() * i, pos.y + dir.getY() * i);

                if (flamePos.valid()) {
                    Entity entity = getEntityAtPosition(flamePos);

                    if (entity instanceof Wall) break;
                    else if (entity instanceof Brick) {
                        addFlameAtPosition(flamePos);
                        break;
                    } else {
                        addFlameAtPosition(flamePos);
                    }
                }
            }
        }
    }

    private void addFlameAtPosition(Point pos) {
        Container.flames[(int) pos.x][(int) pos.y].add(new Flame(pos, Flame.imgState[0]));
    }
}
