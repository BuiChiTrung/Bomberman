package uet.oop.bomberman.timeline;

import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.still.StillEntity;
import uet.oop.bomberman.entities.move.Bomber;
import uet.oop.bomberman.entities.move.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Container {
    // 2D array contains arraylist
    public static ArrayList<StillEntity>[][] stillEntities = new ArrayList[CanvasManager.ROW][CanvasManager.COLUMN];

    public static List<Enemy> enemies =  new ArrayList<>();

    public static Bomber bomber;

    //Shortest direction to bomber
    public static int[][] directionToBomber = new int[CanvasManager.ROW][CanvasManager.COLUMN];

    public static ArrayList<Bomb> bombs = new ArrayList<>();

    public static ArrayList<Flame>[][] flames = new ArrayList[CanvasManager.ROW][CanvasManager.COLUMN];

    static {
        for (int i = 0; i < CanvasManager.ROW; ++i)
            for (int j = 0; j < CanvasManager.COLUMN; ++j) {
                stillEntities[i][j] = new ArrayList<StillEntity>();
                flames[i][j] = new ArrayList<Flame>();
            }
    }

    public static void removeDestroyedEntity() {
        for (int i = 0; i < enemies.size(); ++i) {
            Enemy enemy = enemies.get(i);
            if (enemy.isDestroy()) {
                enemies.remove(i);
                --i;
            }
        }

        for (int i = 0; i < bombs.size(); ++i) {
            Bomb bomb = bombs.get(i);
            if (bomb.isDestroy()) {
                bombs.remove(i);
                --i;
            }
        }

        for (int i = 0; i < CanvasManager.ROW; ++i)
            for (int j = 0; j < CanvasManager.COLUMN; ++j) {
                for (int l = 0; l < stillEntities[i][j].size(); ++l) {
                    StillEntity stillEntity = stillEntities[i][j].get(l);
                    if (stillEntity.isDestroy()) {
                        stillEntities[i][j].remove(l);
                        --l;
                    }
                }

                for (int l = 0; l < flames[i][j].size(); ++l) {
                    Flame flame = flames[i][j].get(l);
                    if (flame.isDestroy()) {
                        flames[i][j].remove(l);
                        --l;
                    }
                }
            }
    }

    public static void updateEntity() {
        enemies.forEach(enemy -> enemy.update());
        bombs.forEach(bomb -> bomb.update());
        for (int i = 0; i < CanvasManager.ROW; ++i)
            for (int j = 0; j < CanvasManager.COLUMN; ++j) {
                flames[i][j].forEach(flame -> flame.update());
                stillEntities[i][j].forEach(stillEntity -> stillEntity.update());
            }

        bomber.update();
    }

    public static void reset() {
        enemies = new ArrayList<>();
        bombs = new ArrayList<>();
        for (int i = 0; i < CanvasManager.ROW; ++i)
            for (int j = 0; j < CanvasManager.COLUMN; ++j) {
                stillEntities[i][j].clear();
                flames[i][j].clear();
            }
        directionToBomber = new int[CanvasManager.ROW][CanvasManager.COLUMN];
    }
}
