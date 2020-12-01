package uet.oop.bomberman.timeline;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.entities.move.enemy.Ballom;
import uet.oop.bomberman.entities.move.enemy.Oneal;
import uet.oop.bomberman.entities.still.*;
import uet.oop.bomberman.entities.still.item.BombItem;
import uet.oop.bomberman.entities.still.item.FlameItem;
import uet.oop.bomberman.entities.still.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CanvasManager {
    // window size
    public static final int ROW = 13;
    public static final int COLUMN = 31;
    private final Canvas canvas = new Canvas(Sprite.SCALED_SIZE * COLUMN, Sprite.SCALED_SIZE * ROW);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    public static long lastRenderTime;

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
                    Container.stillEntities[x][y].add(new Grass(new Point(x, y), Sprite.grass.getFxImage()));

                    switch (line.charAt(y)) {
                        case '#':
                            Container.stillEntities[x][y].add(new Wall(new Point(x, y), Sprite.wall.getFxImage()));
                            break;
                        case '*':
                            //Container.stillEntities[x][y].add(new Brick(new Point(x, y), Sprite.brick.getFxImage()));
                            break;
                        // Add item roi lay brick de len
                        case 'x':
                            //Container.stillEntities[x][y].add(new Portal(new Point(x, y), Sprite.portal.getFxImage()));
                            Container.stillEntities[x][y].add(new Portal(new Point(x, y), new Image(new FileInputStream("res/sprites/custom_sprite/portal01.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true)));
                            Container.stillEntities[x][y].add(new Brick(new Point(x, y), Sprite.brick.getFxImage()));
                            break;
                        case 'b':
                            Container.stillEntities[x][y].add(new BombItem(new Point(x, y), Sprite.powerup_bombs.getFxImage()));
                            // Container.stillEntities[x][y].add(new Brick(new Point(x, y), Sprite.brick.getFxImage()));
                            break;
                        case 'f':
                            Container.stillEntities[x][y].add(new FlameItem(new Point(x, y), Sprite.powerup_flames.getFxImage()));
                            //Container.stillEntities[x][y].add(new Brick(new Point(x, y), Sprite.brick.getFxImage()));
                            break;
                        case 's':
                            Container.stillEntities[x][y].add(new SpeedItem(new Point(x, y), Sprite.powerup_speed.getFxImage()));
                            //Container.stillEntities[x][y].add(new Brick(new Point(x, y), Sprite.brick.getFxImage()));
                            break;
                        case 'p':
                            Container.bomber = new Bomber(new Point(x, y), Sprite.player_right_0.getFxImage());
                            break;
                        case '1':
                            //Container.enemies.add(new Ballom(new Point(x, y), Sprite.ballom_right0.getFxImage()));
                            break;
                        case '2':
                            //Container.enemies.add(new Oneal(new Point(x, y), Sprite.oneal_right0.getFxImage()));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR while reading map file");
        }
    }

    public void renderEntity() {
        //delayRenderTimeBetweenTwoFrame();

        for (int x = 0; x < ROW; ++x) {
            for (int y = 0; y < COLUMN; ++y) {
                Container.stillEntities[x][y].get(Container.stillEntities[x][y].size() - 1).render(gc);
                if (!Container.flames[x][y].isEmpty())
                    Container.flames[x][y].get(0).render(gc);
            }
        }
        Container.bombs.forEach(bomb -> bomb.render(gc));
        Container.enemies.forEach(g -> g.render(gc));
        Container.bomber.render(gc);
    }

    public void delayRenderTimeBetweenTwoFrame() {
        while (System.currentTimeMillis() - lastRenderTime < 20) {
            // loop until difference >= 40
        }
        lastRenderTime = System.currentTimeMillis();
    }
}