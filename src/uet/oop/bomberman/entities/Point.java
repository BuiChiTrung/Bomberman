package uet.oop.bomberman.entities;

import uet.oop.bomberman.timeline.CanvasManager;

public class Point {
    public double x;
    public double y;
    private final double eps = 0.125;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * check inside board
     */
    public boolean valid() {
        return x >= 0 && x < CanvasManager.ROW && y >=0 && y < CanvasManager.COLUMN;
    }

    public boolean isEquals(Point p) {
        return Math.abs(p.x - this.x) <= eps && Math.abs(p.y - this.y) <= eps;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public double distance(Point other) {
        return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
    }
}
