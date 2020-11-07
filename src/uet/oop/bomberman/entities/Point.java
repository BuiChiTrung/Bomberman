package uet.oop.bomberman.entities;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
