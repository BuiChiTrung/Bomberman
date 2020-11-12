package uet.oop.bomberman.util;

import javafx.scene.input.KeyCode;
import uet.oop.bomberman.timeline.Container;
import static javafx.scene.input.KeyCode.*;

public class Util {
    public static int getDirection(KeyCode key) {
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
}
