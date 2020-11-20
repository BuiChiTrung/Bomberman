package uet.oop.bomberman.util;

import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.timeline.CanvasManager;
import uet.oop.bomberman.timeline.Container;
import static javafx.scene.input.KeyCode.*;

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
                    if(Container.Objects[(int)newPos.x][(int)newPos.y].get(Container.Objects[(int)newPos.x][(int)newPos.y].size() - 1) instanceof Brick
                            || Container.Objects[(int)newPos.x][(int)newPos.y].get(Container.Objects[(int)newPos.x][(int)newPos.y].size() - 1) instanceof Wall) {
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

    /**
     * Lấy vị trí tiếp theo theo hướng
     */
    public static Point getNextDestination(Point pos, Direction direction) {
        return new Point((int)(pos.x + direction.getX()), (int)(pos.y + direction.getY()));
    }

    public static Point getNextPosition(Point pos, Direction direction) {
        return new Point(pos.x + direction.getX(), pos.y + direction.getY());
    }

    public static double euclidDistance(Point point1, Point point2) {
        return Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y));
    }

    public static boolean blocked(Point pos) {
        return Container.Objects[(int)pos.x][(int)pos.y].get(Container.Objects[(int)pos.x][(int)pos.y].size() - 1) instanceof Brick && Container.Objects[(int)pos.x][(int)pos.y].get(Container.Objects[(int)pos.x][(int)pos.y].size() - 1) instanceof Wall;
    }
}
