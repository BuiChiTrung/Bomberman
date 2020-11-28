package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.DirectionUtil;

public class Bomb extends Entity {
    private static final double timeToExplode = 1000;         // 1000ms
    private boolean onBomberFoot;                             // check whether bomber still standing on bomb or not
    private long placeMoment;                                 // moment bomb is placed
    private static final int NUMBER_OF_FRAME_TO_CHANGE_IMG = 10;
    private static final int NUMBER_OF_IMG = 3;
    private int imgId = -1;

    private final Image[] imgArray = {
            Sprite.bomb0.getFxImage(),
            Sprite.bomb1.getFxImage(),
            Sprite.bomb2.getFxImage()
    };

    public Bomb(Point pos, Image img) {
        super(pos, img);
        onBomberFoot = true;
        placeMoment  = System.currentTimeMillis();
    }

    public boolean isOnBomberFoot() {
        return onBomberFoot;
    }

    public void update() {
        updateImg();
        if (pos.distance(Container.bomber.getPos()) >= 1) onBomberFoot = false;  // bomb becomes an obstacle for bomber
        if (System.currentTimeMillis() - placeMoment > timeToExplode) explode();
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
        destroy = true;
    }

    private void createFlame() {
        addFlameToAtPosition(pos);

        for (int directId = 0; directId <= 3; ++directId) {
            Direction dir = DirectionUtil.getDirectionFromId(directId);

            for (int i = 1; i <= Container.bomber.getBombPower(); ++i) {
                Point flamePos = new Point(pos.x + dir.getX() * i, pos.y + dir.getY() * i);

                if (flamePos.valid()) {
                    Entity entity = getEntityAtPosition(flamePos);

                    if      (entity instanceof Wall) break;
                    else if (entity instanceof Brick) {
                        addFlameToAtPosition(flamePos);
                        break;
                    }
                    else {
                        addFlameToAtPosition(flamePos);
                    }
                }
            }
        }
    }

    private void addFlameToAtPosition(Point pos) {
        Container.flames[(int)pos.x][(int)pos.y].add(new Flame(pos, Flame.imgState[0]));
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, pos.y * Sprite.SCALED_SIZE, pos.x * Sprite.SCALED_SIZE);
    }
}
