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
    private List<Entity> moveObjects =  new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private Bomber bomber;

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

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
            for (int i = 0; i < HEIGHT; ++i) {
                String line = sc.nextLine();
                for (int j = 0; j < WIDTH; ++j) {
                    switch (line.charAt(j)) {
                        case '#':
                            stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                            break;
                        case ' ':
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            break;
                        case '*':
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'p':
                            bomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                            moveObjects.add(bomber);
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            break;
                        case '1':
                            moveObjects.add(new Enemy(j, i, Sprite.balloom_left1.getFxImage()));
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            break;
                        case '2':
                            moveObjects.add(new Enemy(j, i, Sprite.oneal_left1.getFxImage()));
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            break;
                        default:
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
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
        stillObjects.forEach(g -> g.render(gc));
        moveObjects.forEach(g -> g.render(gc));

    }
}
