package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.entities.move.Enemy;
import uet.oop.bomberman.entities.still.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    // window size
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;

    private static Bomber bomber;

    // 2D array contains arraylist
    public static ArrayList<Entity>[][] stillObjects = new ArrayList[WIDTH][HEIGHT];
    public static List<Entity> moveObjects =  new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        addEventHandler(scene);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
    }

    public void addEventHandler(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                bomber.move(event.getCode());
            }
        });
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
                            stillObjects[x][y].add(new Portal(x, y, Sprite.wall.getFxImage()));
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
                            bomber = new Bomber(x, y, Sprite.player_right.getFxImage());
                            moveObjects.add(bomber);
                            break;
                        case '1':
                            moveObjects.add(new Enemy(x, y, Sprite.balloom_left1.getFxImage()));
                            break;
                        case '2':
                            moveObjects.add(new Enemy(x, y, Sprite.oneal_left1.getFxImage()));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR while reading map file");
        }
    }

    public void update() {
        // call method update of Entity 
        moveObjects.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < WIDTH; ++x)
            for (int y = 0; y < HEIGHT; ++y)
                stillObjects[x][y].forEach(g -> g.render(gc));
        moveObjects.forEach(g -> g.render(gc));
    }
}
