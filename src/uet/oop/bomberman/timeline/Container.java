package uet.oop.bomberman.timeline;

import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.still.StillEntity;
import uet.oop.bomberman.entities.move.MovingEntity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.move.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Container {
    // 2D array contains arraylist
    public static ArrayList<StillEntity>[][] objects = new ArrayList[CanvasManager.ROW][CanvasManager.COLUMN];

    public static List<Enemy> enemies =  new ArrayList<>();

    public static Bomber bomber;

    //Hướng đi ngắn nhất đến bomber
    public static int[][] directionToBomber = new int[CanvasManager.ROW][CanvasManager.COLUMN];

    public static ArrayList<Bomb> bombs = new ArrayList<>();
}
