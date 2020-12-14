package uet.oop.bomberman.scene;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.entities.move.enemy.Ballom;
import uet.oop.bomberman.entities.move.enemy.Doll;
import uet.oop.bomberman.entities.move.enemy.Minvo;
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

import static uet.oop.bomberman.scene.Container.bomber;

public class MainScene {
    // window size
    public static final int ROW = 13;
    public static final int COLUMN = 31;
    private static Scene scene = null;

    private static GraphicsContext gc;

    public static Scene getScene() {
        return setUpScene();
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

    private static void addEventHandler() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // user can get back to menu scene to restart
                if (event.getCode() == KeyCode.R) {
                    BombermanGame.getTimer().stop();
                    Container.reset();
                    BombermanGame.getPrimaryStage().setScene(MenuScene.getScene());
                }
                else bomber.handlePress(event.getCode());
            }
        });
        scene.setOnKeyReleased(event -> bomber.handleRelease(event.getCode()));
    }

    private static void createMap() {
        try {
            String mapPath = "./res/levels/Level" + Container.currentLevel + ".txt";
            File map = new File(mapPath);
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
                            //Container.stillEntities[x][y].add(new Brick(new Point(x, y), ImgFactory.brickImg[0]));
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
                            Container.bomber = new Bomber(new Point(1, 1), ImgFactory.bomberImg[2][0]);
                            break;
                        case '1':
                            Container.enemies.add(new Ballom(new Point(x, y), ImgFactory.ballomImg[2][0]));
                            Container.enemyLeft++;
                            break;
                        case '2':
                            Container.enemies.add(new Oneal(new Point(x, y), ImgFactory.onealImg[2][0]));
                            Container.enemyLeft++;
                            break;
                        case '3':
                            Container.enemies.add(new Doll(new Point(x, y), ImgFactory.dollImg[0][0]));
                            Container.enemyLeft++;
                            break;
                        case '4':
                            Container.enemies.add(new Minvo(new Point(x, y), ImgFactory.dollImg[0][0]));
                            Container.enemyLeft++;
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR while reading map file");
        }
    }

    private static void createBomber() {
        bomber = new Bomber(new Point(1, 1), ImgFactory.bomberImg[2][0]);
    }

    public static void loop() {
        Container.updateEntity();
        Container.removeDestroyedEntity();
        renderEntity();
        if (bomber.isRemovableFromContainer()) {
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
        bomber.render(gc);
    }

    public static void goToNextLevel() {
        Container.currentLevel++;
        Bomber prevState = Container.bomber;
        Container.reset();
        createMap();
        bomber.getPreviousLevelState(prevState);
    }
}