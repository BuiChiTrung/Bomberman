package uet.oop.bomberman.entities.still.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.still.StillEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Flame extends StillEntity {
    private static final int NUMBER_OF_FRAME_TO_CHANGE_IMG = 2;
    private static final int DESTROY_IMG_ID = 24 * NUMBER_OF_FRAME_TO_CHANGE_IMG;
    public static Image[] imgState = new Image[24];

    // provide path, size to use custom sprite
    static {
        try {
            imgState = new Image[]{
                new Image(new FileInputStream("res/sprites/custom_sprite/flame01.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame02.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame03.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame04.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame05.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame06.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame07.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame08.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame09.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame10.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame11.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame12.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame13.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame14.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame15.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame16.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame17.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame18.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame19.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame20.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame21.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame22.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame23.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                new Image(new FileInputStream("res/sprites/custom_sprite/flame24.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
            };
        } catch (FileNotFoundException e) {
            System.out.println("Error while init flame img");
        }
    }

    public Flame(Point pos, Image img) {
        super(pos, img);
    }

    public void update() {
        updateImg();
    }

    /**
     * destroy khi chay het 24 anh
     */
    private void updateImg() {
        imgId++;
        if (imgId == DESTROY_IMG_ID) {
            destroy = true;
        }
        else img = imgState[imgId / NUMBER_OF_FRAME_TO_CHANGE_IMG];
    }
}
