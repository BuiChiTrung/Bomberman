package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Enemy extends Entity {

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}

class Balloon extends Enemy {
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }
}

class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }
}