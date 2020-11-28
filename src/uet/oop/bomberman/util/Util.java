package uet.oop.bomberman.util;

import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.timeline.CanvasManager;
import uet.oop.bomberman.timeline.Container;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

import java.util.LinkedList;
import java.util.Queue;
public class Util {
    /**
     * BFS từ vị trí của bomber
     */
    public static void bfsFromBomber() {
        Point move[] = {new Point(0, -1), new Point(-1, 0), new Point(0, 1), new Point(1, 0)};
        for(int i = 0; i < CanvasManager.ROW; i++) {
            for(int j = 0; j < CanvasManager.COLUMN; j++) {
                Container.directionToBomber[i][j] = 4;
            }
        }
        Queue<Point> queue = new LinkedList<Point>();
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
                    if(Container.stillEntities[(int)newPos.x][(int)newPos.y].get(Container.stillEntities[(int)newPos.x][(int)newPos.y].size() - 1) instanceof Brick
                            || Container.stillEntities[(int)newPos.x][(int)newPos.y].get(Container.stillEntities[(int)newPos.x][(int)newPos.y].size() - 1) instanceof Wall) {
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
//        for(int i = 0; i < CanvasManager.ROW; i++) {
//            for(int j = 0; j < CanvasManager.COLUMN; j++) {
//                System.out.print(Container.directionToBomber[i][j]);
//            }
//            System.out.println();
//        }
    }

    public static Point getMostAreaStandingCells(Point pos){
        if(pos.y % 1 == 0) {
            if(pos.x - (int)pos.x <= 0.5) {
                return new Point(floor(pos.x), pos.y);
            }
            else {
                return new Point(ceil(pos.x), pos.y);
            }
        }
        if(pos.y - (int)pos.y <= 0.5) {
            return new Point(pos.x, floor(pos.y));
        }
        else {
            return new Point(pos.x, ceil(pos.y));
        }
    }
}
