package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Entity;

import static javafx.scene.input.KeyCode.RIGHT;

// moving object: bomber, enemy
public class MovingEntity extends Entity {
    protected KeyCode direct = RIGHT; // manage direction of object
    protected int stepInDirect; // số bước liên tiếp đi theo cùng một hướng lấy mod 3 (nếu rẽ => reset về 0)
    public MovingEntity(double x, double y, Image img) {
        super( x, y, img);
    }
    
    @Override
    public void update() {

    }
}
