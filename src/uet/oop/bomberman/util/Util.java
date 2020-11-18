package uet.oop.bomberman.util;

import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.timeline.CanvasManager;
import uet.oop.bomberman.timeline.Container;
import static javafx.scene.input.KeyCode.*;

import java.util.LinkedList;
import java.util.Queue;
public class Util {
    public static int getDirectionId(KeyCode key) {
        switch (key) {
            case LEFT:
                return 0;
            case UP:
                return 1;
            case RIGHT:
                return 2;
            case DOWN:
                return 3;
        }
        return 0;
    }
    public static KeyCode getDirection(int id) {
        switch (id) {
            case 0:
                return LEFT;
            case 1:
                return UP;
            case 2:
                return RIGHT;
            case 3:
                return DOWN;
        }
        return RIGHT;
    }

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
    }

    public static Point getNextDestination(Point pos, KeyCode key) {
        switch(key) {
            case LEFT:
                return new Point((int)pos.x, (int)pos.y - 1);
            case UP:
                return new Point((int)pos.x - 1, (int)pos.y);
            case RIGHT:
                return new Point((int)pos.x, (int)pos.y + 1);
            case DOWN:
                return new Point((int)pos.x + 1, (int)pos.y);
        }
        return pos;
    }
}
