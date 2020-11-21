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
