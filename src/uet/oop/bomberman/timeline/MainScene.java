package uet.oop.bomberman.timeline;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.entities.move.enemy.Ballom;
import uet.oop.bomberman.entities.move.enemy.Oneal;
import uet.oop.bomberman.entities.still.*;
import uet.oop.bomberman.entities.still.item.BombItem;
import uet.oop.bomberman.entities.still.item.FlameItem;
import uet.oop.bomberman.entities.still.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.ImgFactory;
import uet.oop.bomberman.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainScene {
    // window size
    public static final int ROW = 13;
    public static final int COLUMN = 31;
    private static Scene scene = null;

    private static GraphicsContext gc;

    public static Scene getScene() {
        if (scene == null)
            scene = setUpScene();
        return scene;
    }

    private static Scene setUpScene() {
        Canvas canvas = new Canvas(Sprite.SCALED_SIZE * COLUMN, Sprite.SCALED_SIZE * ROW);
        gc = canvas.getGraphicsContext2D();

        Group rootNode = new Group();
        scene = new Scene(rootNode);
        addEventHandler();
        createMap();

        rootNode.getChildren().add(canvas);

        return scene;
    }

    public static void addEventHandler() {
        scene.setOnKeyPressed(event -> Container.bomber.handlePress(event.getCode()));
        scene.setOnKeyReleased(event -> Container.bomber.handleRelease(event.getCode()));
    }

    public static void createMap() {
        try {
            File map = new File("./res/levels/Level1.txt");
            Scanner sc = new Scanner(map);
            for (int x = 0; x < ROW; ++x) {
                String line = sc.nextLine();
                for (int y = 0; y < COLUMN; ++y) {
                    // Add grass to all cell
                    Container.stillEntities[x][y].add(new Grass(new Point(x, y), ImgFactory.grassImg));

                    switch (line.charAt(y)) {
                        case '#':
                            Container.stillEntities[x][y].add(new Wall(new Point(x, y), ImgFactory.wallImg));
                            break;
                        case '*':
                            Container.stillEntities[x][y].add(new Brick(new Point(x, y), ImgFactory.brickImg[0]));
                            break;
                        // Add item roi lay brick de len
                        case 'x':
                            Container.stillEntities[x][y].add(new Portal(new Point(x, y), ImgFactory.portalImg));
                            Container.stillEntities[x][y].add(new Brick(new Point(x, y), ImgFactory.brickImg[0]));
                            break;
                        case 'b':
                            Container.stillEntities[x][y].add(new BombItem(new Point(x, y), ImgFactory.bombItemImg));
                            //Container.stillEntities[x][y].add(new Brick(new Point(x, y), ImgFactory.brickImg[0]));
                            break;
                        case 'f':
                            Container.stillEntities[x][y].add(new FlameItem(new Point(x, y), ImgFactory.flameItemImg));
                            //Container.stillEntities[x][y].add(new Brick(new Point(x, y), ImgFactory.brickImg[0]));
                            break;
                        case 's':
                            Container.stillEntities[x][y].add(new SpeedItem(new Point(x, y), ImgFactory.speedItemImg));
                            //Container.stillEntities[x][y].add(new Brick(new Point(x, y), ImgFactory.brickImg[0]));
                            break;
                        case 'p':
                            Container.bomber = new Bomber(new Point(x, y), ImgFactory.bomberImg[2][0]);
                            break;
                        case '1':
                            Container.enemies.add(new Ballom(new Point(x, y), ImgFactory.ballomImg[2][0]));
                            break;
                        case '2':
                            Container.enemies.add(new Oneal(new Point(x, y), ImgFactory.onealImg[2][0]));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR while reading map file");
        }
    }

    public static void loop() {
        Container.updateEntity();
        Container.removeDestroyedEntity();
        renderEntity();
        if (Container.bomber.isRemovableFromContainer()) {
            Container.reset();
            createMap();
        }
    }

    public static void renderEntity() {
        for (int x = 0; x < ROW; ++x) {
            for (int y = 0; y < COLUMN; ++y) {
                // Grass is rendered every frame
                Container.stillEntities[x][y].get(0).render(gc);

                Entity entity = Util.getLast(Container.stillEntities[x][y]);
                if (!(entity instanceof Brick)) entity.render(gc);

                if (!Container.flames[x][y].isEmpty())
                    Container.flames[x][y].get(0).render(gc);
                if ((entity instanceof Brick)) entity.render(gc);

            }
        }
        Container.enemies.forEach(g -> g.render(gc));
        Container.bomber.render(gc);
    }
}