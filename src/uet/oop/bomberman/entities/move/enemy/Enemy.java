package uet.oop.bomberman.entities.move.enemy;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.move.MovingEntity;

import java.util.Random;

import static javafx.scene.input.KeyCode.*;

public abstract class Enemy extends MovingEntity {
    public Enemy(double x, double y, Image img) {
        super(x, y, img);
        velocity = 0.125;
    }

    protected void updateDirectionAndStepInDirect(Direction nextDirection) {
        if (nextDirection.equals(direction))
            stepInDirect = 0;
        else
            stepInDirect += 1;
        direction = nextDirection;
    }
    public abstract void move();
}


