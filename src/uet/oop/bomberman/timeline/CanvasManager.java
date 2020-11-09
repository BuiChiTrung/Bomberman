package uet.oop.bomberman.timeline;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.entities.move.MovingEntity;
import uet.oop.bomberman.entities.move.enemy.Balloon;
import uet.oop.bomberman.entities.move.enemy.Oneal;
import uet.oop.bomberman.entities.still.*;
import uet.oop.bomberman.entities.still.item.BombItem;
import uet.oop.bomberman.entities.still.item.FlameItem;
import uet.oop.bomberman.entities.still.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CanvasManager {
    // window size
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private Canvas canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    public static long lastRenderTime;

    // 2D array contains arraylist
    public static ArrayList<StillEntity>[][] stillObjects = new ArrayList[WIDTH][HEIGHT];
    public static List<MovingEntity> moveObjects =  new ArrayList<>();
    public static List<Point> modifiedObjects = new ArrayList<>();

    public Canvas getCanvas() {
        return canvas;
    }

    public long getLastRenderTime() {
        return lastRenderTime;
    }

    public void createMap() {
        try {
            File map = new File("./res/levels/Level1.txt");
            Scanner sc = new Scanner(map);
            for (int y = 0; y < HEIGHT; ++y) {
                String line = sc.nextLine();
                for (int x = 0; x < WIDTH; ++x) {
                    // Add grass to all cell
                    stillObjects[x][y] = new ArrayList<>();
                    stillObjects[x][y].add(new Grass(x, y, Sprite.grass.getFxImage()));

                    switch (line.charAt(x)) {
                        case '#':
                            stillObjects[x][y].add(new Wall(x, y, Sprite.wall.getFxImage()));
                            break;
                        case '*':
                            stillObjects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        // Add item roi lay brick de len
                        case 'x':
                            stillObjects[x][y].add(new Portal(x, y, Sprite.portal.getFxImage()));
                            stillObjects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        case 'b':
                            stillObjects[x][y].add(new BombItem(x, y, Sprite.powerup_bombs.getFxImage()));
                            stillObjects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        case 'f':
                            stillObjects[x][y].add(new FlameItem(x, y, Sprite.powerup_flames.getFxImage()));
                            stillObjects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        case 's':
                            stillObjects[x][y].add(new SpeedItem(x, y, Sprite.powerup_speed.getFxImage()));
                            stillObjects[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                            break;
                        case 'p':
                            moveObjects.add(Bomber.INSTANCE);
                            break;
                        case '1':
                            moveObjects.add(new Balloon(x, y, Sprite.balloom_right0.getFxImage()));
                            break;
                        case '2':
                            moveObjects.add(new Oneal(x, y, Sprite.oneal_right0.getFxImage()));
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
        for (int x = 0; x < WIDTH; ++x)
            for (int y = 0; y < HEIGHT; ++y)
                stillObjects[x][y].get(stillObjects[x][y].size() - 1).render(gc);

        moveObjects.forEach(g -> g.render(gc));
    }

    /**
     * only render modified entities to increase fps
     */
    public void render_modified_entities() {
        // clear cells contain modified entities and render again
        if (modifiedObjects.isEmpty()) return;

        for (Point it : modifiedObjects) {
            //gc.clearRect(it.x * Sprite.SCALED_SIZE, it.y * Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
            stillObjects[(int)it.x][(int)it.y].get(stillObjects[(int)it.x][(int)it.y].size() - 1).render(gc);
        }
        moveObjects.forEach(g -> g.render(gc));

        lastRenderTime = System.currentTimeMillis();
        modifiedObjects.clear();
    }
}
