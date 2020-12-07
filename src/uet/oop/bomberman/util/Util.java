package uet.oop.bomberman.util;

import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.timeline.CanvasManager;
import uet.oop.bomberman.timeline.Container;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class Util {
    /**
     * BFS từ vị trí của bomber
     */
    public static void bfsFromBomber() {
        Point[] move = {new Point(0, -1), new Point(-1, 0), new Point(0, 1), new Point(1, 0)};
        for(int i = 0; i < CanvasManager.ROW; i++) {
            for(int j = 0; j < CanvasManager.COLUMN; j++) {
                Container.directionToBomber[i][j] = 4;
            }
        }
        Queue<Point> queue = new LinkedList<Point>();
        //System.out.println(Container.bomber.isDestroy());
        Point BomberPos = Container.bomber.getMostAreaStandingCells();
        queue.offer(Container.bomber.getMostAreaStandingCells());
        Container.directionToBomber[(int)BomberPos.x][(int)BomberPos.y] = 0;
        try {
            while(queue.size() > 0) {
                Point u = queue.remove();
                for(int i = 0; i < 4; i++) {
                    Point mv = move[i];
                    Point newPos = new Point(u.x + mv.x, u.y + mv.y);
                    if(!newPos.valid()) {
                        continue;
                    }
                    if(getLast(Container.stillEntities[(int)newPos.x][(int)newPos.y]) instanceof Brick
                            || getLast(Container.stillEntities[(int)newPos.x][(int)newPos.y]) instanceof Wall
                            || checkContainBomb(new Point(newPos.x, newPos.y))) {
                        continue;
                    }
                    if(Container.directionToBomber[(int)newPos.x][(int)newPos.y] == 4) {
                        Container.directionToBomber[(int)newPos.x][(int)newPos.y] = i ^ 2;
                        queue.offer(newPos);
                    }
                }
            }
        }
        catch(Exception ex) {
            System.out.println("BFS Error");
        }
    }
    public static <T> T getLast(ArrayList<T> arr) {
        return arr.get(arr.size() - 1);
    }
    public static <T> void removeLastEntity(ArrayList<T> arr) {
        arr.remove(arr.size() - 1);
    }

    public static boolean checkContainBomb(Point pos) {
        for(Bomb bomb: Container.bombs) {
            if(bomb.getPos().x == pos.x && bomb.getPos().y == pos.y) {
                return true;
            }
        }
        return false;
    }

    public static double getDistance(Point p, Point q) {
        return Math.sqrt((p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y));
    }
}
