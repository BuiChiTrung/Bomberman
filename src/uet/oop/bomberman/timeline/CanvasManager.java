package uet.oop.bomberman.timeline;

import com.sun.rowset.internal.Row;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.entities.move.MovingEntity;
import uet.oop.bomberman.entities.move.enemy.Balloon;
import uet.oop.bomberman.entities.move.enemy.Oneal;
import uet.oop.bomberman.entities.still.*;
import uet.oop.bomberman.entities.still.item.BombItem;
import uet.oop.bomberman.entities.still.item.FlameItem;
import uet.oop.bomberman.entities.still.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.entities.move.Bomber;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CanvasManager {
    // window size
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int ROW = 13;
    public static final int COLUMN = 31;
    private final Canvas canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    public Canvas getCanvas() {
        return canvas;
    }

    public void createMap() {
        try {
            File map = new File("./res/levels/Level1.txt");
            Scanner sc = new Scanner(map);
            for (int x = 0; x < ROW; ++x) {
                String line = sc.nextLine();
                for (int y = 0; y < COLUMN; ++y) {
                    // Add grass to all cell
                    Container.Objects[x][y] = new ArrayList<>();
                    Container.Objects[x][y].add(new Grass(x, y, Sprite.grass.getFxImage()));

                    switch (line.charAt(y)) {
                        case '#':
                            Container.Objects[x][y].add(new Wall(x, y, Sprite.wall.getFxImage()));
                            break;
                        case '*':
                            //Container.Objects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        // Add item roi lay brick de len
                        case 'x':
                            Container.Objects[x][y].add(new Portal(x, y, Sprite.portal.getFxImage()));
                            Container.Objects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        case 'b':
                            Container.Objects[x][y].add(new BombItem(x, y, Sprite.powerup_bombs.getFxImage()));
                            Container.Objects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        case 'f':
                            Container.Objects[x][y].add(new FlameItem(x, y, Sprite.powerup_flames.getFxImage()));
                            Container.Objects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        case 's':
                            Container.Objects[x][y].add(new SpeedItem(x, y, Sprite.powerup_speed.getFxImage()));
                            Container.Objects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        case 'p':
                            Container.bomber = new Bomber(1, 1, Sprite.player_right_0.getFxImage());
                            break;
                        case '1':
                            Container.enemy.add(new Balloon(x, y, Sprite.balloom_right0.getFxImage()));
                            break;
                        case '2':
                            Container.enemy.add(new Oneal(x, y, Sprite.oneal_right0.getFxImage()));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR while reading map file");
        }
    }

    /**
     * render only when the game starts
     */
    public void render_all_entities() {
        for (int x = 0; x < ROW; ++x)
            for (int y = 0; y < COLUMN; ++y)
                Container.Objects[x][y].get(Container.Objects[x][y].size() - 1).render(gc);
        Container.enemy.forEach(g -> g.render(gc));
        Container.bomber.render(gc);
    }
}