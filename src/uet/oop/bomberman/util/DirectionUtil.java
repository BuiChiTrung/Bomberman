//package uet.oop.bomberman.util;
//
//import javafx.scene.input.KeyCode;
//
//public enum DirectionUtil {
//    LEFT(0, -1), UP(-1, 0), RIGHT(0, 1), DOWN(1, 0);
//
//    private double x;
//    private double y;
//
//    DirectionUtil(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public void setX(double x) {
//        this.x = x;
//    }
//
//    public void setY(double y) {
//        this.y = y;
//    }
//
//    public static DirectionUtil getDirectionFromId(int id) {
//        switch(id) {
//            case 0:
//                return LEFT;
//            case 1:
//                return UP;
//            case 2:
//                return RIGHT;
//            case 3:
//                return DOWN;
//        }
//        System.out.println("Direction Error : Choose from 0-3");
//        return LEFT;
//    }
//
//    public static DirectionUtil getDirectionFromKeyCode(KeyCode key) {
//        switch(key) {
//            case LEFT:
//                return LEFT;
//            case UP:
//                return UP;
//            case RIGHT:
//                return RIGHT;
//            case DOWN:
//                return DOWN;
//        }
//        System.out.println("Direction Error : Choose KeyCode LEFT, UP, RIGHT, DOWN");
//        return LEFT;
//    }
//
//    public static int getDirectionId(DirectionUtil direction) {
//        switch (direction) {
//            case LEFT:
//                return 0;
//            case UP:
//                return 1;
//            case RIGHT:
//                return 2;
//            case DOWN:
//                return 3;
//        }
//        return 0;
//    }
//}
package uet.oop.bomberman.util;

import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Direction;
public class DirectionUtil {
    public static Direction getDirectionFromId(int id) {
        switch(id) {
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.UP;
            case 2:
                return Direction.RIGHT;
            case 3:
                return Direction.DOWN;
        }
        System.out.println("Direction Error : Choose from 0-3");
        return Direction.LEFT;
    }

    public static Direction getDirectionFromKeyCode(KeyCode key) {
        switch(key) {
            case LEFT:
                return Direction.LEFT;
            case UP:
                return Direction.UP;
            case RIGHT:
                return Direction.RIGHT;
            case DOWN:
                return Direction.DOWN;
        }
        System.out.println("Direction Error : Choose KeyCode LEFT, UP, RIGHT, DOWN");
        return Direction.LEFT;
    }

    public static int getDirectionId(Direction direction) {
        switch (direction) {
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
}
