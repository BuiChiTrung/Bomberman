package uet.oop.bomberman.util;

import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.scene.Container;

public class MoveUtil {
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
        return Util.getLast(Container.stillEntities[(int)pos.x][(int)pos.y]) instanceof Brick
                || Util.getLast(Container.stillEntities[(int)pos.x][(int)pos.y]) instanceof Wall;
    }
}
