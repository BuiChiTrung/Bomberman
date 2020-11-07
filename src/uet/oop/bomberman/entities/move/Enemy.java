package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;

public class Enemy extends MovingEntity {

    public Enemy(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}

class Balloon extends Enemy {
    public Balloon(double x, double y, Image img) {
        super(x, y, img);
    }
}

class Oneal extends Enemy {
    public Oneal(double x, double y, Image img) {
        super(x, y, img);
    }
}