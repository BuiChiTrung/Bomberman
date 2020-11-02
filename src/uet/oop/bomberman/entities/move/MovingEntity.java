package uet.oop.bomberman.entities.move;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

import static javafx.scene.input.KeyCode.RIGHT;

// moving object: bomber, enemy
public class MovingEntity extends Entity {
    protected KeyCode direct = RIGHT; // manage direction of object
    protected int stepInDirect; // số bước liên tiếp đi theo cùng một hướng lấy mod 3 (nếu rẽ => reset về 0)
    protected double velocity;

    public MovingEntity(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }


    public boolean hasObstacle(double x, double y) {
        if (x < 0 || x > BombermanGame.WIDTH) return true;
        if (y < 0 || y > BombermanGame.HEIGHT) return true;

        ArrayList<Entity>[][] stillObjects = BombermanGame.stillObjects;

        Entity lastEntity = stillObjects[(int) x][(int) y].get(stillObjects[(int) x][(int) y].size() - 1);
        if (lastEntity instanceof Brick || lastEntity instanceof Wall) return true;

        return false;
    }


}
