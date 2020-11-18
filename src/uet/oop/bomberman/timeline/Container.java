package uet.oop.bomberman.timeline;

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
    public static ArrayList<StillEntity>[][] Objects = new ArrayList[CanvasManager.ROW][CanvasManager.COLUMN];

    public static List<Enemy> enemy =  new ArrayList<>();

    public static Bomber bomber;

    public static int[][] directionToBomber = new int[CanvasManager.ROW][CanvasManager.COLUMN];
}
