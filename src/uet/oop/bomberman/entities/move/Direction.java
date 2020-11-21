package uet.oop.bomberman.entities.move;

import javafx.scene.input.KeyCode;

public enum Direction {
    /**
     * equivalent:
     * public static final Direction UP = new Direction(KeyCode.UP, 0, -1);
     */
    UP(KeyCode.UP, 0, -1),
    RIGHT(KeyCode.RIGHT, 1, 0),
    DOWN(KeyCode.DOWN, 0, 1),
    LEFT(KeyCode.LEFT, -1, 0);

    public static Direction[] directions = Direction.values();

    private KeyCode direct;
    private double translateX;
    private double translateY;

    private Direction(KeyCode direct, double translateX, double translateY) {
        this.direct = direct;
        this.translateX = translateX;
        this.translateY = translateY;
    }

    public KeyCode getDirect() {
        return direct;
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }
}

