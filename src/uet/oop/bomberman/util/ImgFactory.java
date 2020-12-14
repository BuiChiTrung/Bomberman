package uet.oop.bomberman.util;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImgFactory {
    public static Image[][] ballomImg;
    public static Image[][] onealImg;
    public static Image[][] bomberImg;
    public static Image[][] dollImg;

    public static Image[] bombImg;
    public static Image[] flameImg;

    public static Image bombItemImg;
    public static Image flameItemImg;
    public static Image speedItemImg;

    public static Image[] brickImg;
    public static Image grassImg;
    public static Image portalImg;
    public static Image wallImg;
    public static Image[][] minvoImg;

    public static void createClassicImg() {
        createConstantImg();
        bombImg = new Image[]{
                Sprite.bomb0.getFxImage(),
                Sprite.bomb1.getFxImage(),
                Sprite.bomb2.getFxImage()
        };

        ballomImg = new Image[][]{
                //LEFT
                {Sprite.ballom_left0.getFxImage(),
                        Sprite.ballom_left1.getFxImage(),
                        Sprite.ballom_left2.getFxImage()},
                //UP
                {Sprite.ballom_left0.getFxImage(),
                        Sprite.ballom_left1.getFxImage(),
                        Sprite.ballom_left2.getFxImage()},
                //RIGHT
                {Sprite.ballom_right0.getFxImage(),
                        Sprite.ballom_right1.getFxImage(),
                        Sprite.ballom_right2.getFxImage()},
                //UP
                {Sprite.ballom_right0.getFxImage(),
                        Sprite.ballom_right1.getFxImage(),
                        Sprite.ballom_right2.getFxImage()},
                // DIE
                {Sprite.ballom_dead.getFxImage()}
        };

        onealImg = new Image[][] {
                {Sprite.oneal_left0.getFxImage(),
                        Sprite.oneal_left1.getFxImage(),
                        Sprite.oneal_left2.getFxImage()} ,

                {Sprite.oneal_left0.getFxImage(),
                        Sprite.oneal_left1.getFxImage(),
                        Sprite.oneal_left2.getFxImage()} ,

                {Sprite.oneal_right0.getFxImage(),
                        Sprite.oneal_right1.getFxImage(),
                        Sprite.oneal_right2.getFxImage()} ,

                {Sprite.oneal_right0.getFxImage(),
                        Sprite.oneal_right1.getFxImage(),
                        Sprite.oneal_right2.getFxImage()} ,

                {Sprite.oneal_dead.getFxImage()}
        };

        bomberImg = new Image[][] {
                //LEFT:0
                {Sprite.player_left_0.getFxImage(),
                        Sprite.player_left_1.getFxImage(),
                        Sprite.player_left_2.getFxImage()},
                //UP:1
                {Sprite.player_up_0.getFxImage(),
                        Sprite.player_up_1.getFxImage(),
                        Sprite.player_up_2.getFxImage()},
                //RIGHT:2
                {Sprite.player_right_0.getFxImage(),
                        Sprite.player_right_1.getFxImage(),
                        Sprite.player_right_2.getFxImage()},
                //DOWN:3
                {Sprite.player_down_0.getFxImage(),
                        Sprite.player_down_1.getFxImage(),
                        Sprite.player_down_2.getFxImage()},
                //DEATH:4
                {Sprite.player_dead_0.getFxImage(),
                        Sprite.player_dead_1.getFxImage(),
                        Sprite.player_dead_2.getFxImage()}
        };

        bombItemImg = Sprite.powerup_bombs.getFxImage();
        speedItemImg = Sprite.powerup_speed.getFxImage();
        flameItemImg = Sprite.powerup_flames.getFxImage();

        brickImg = new Image[]{
                Sprite.brick.getFxImage(),
                Sprite.brick_exploded0.getFxImage(),
                Sprite.brick_exploded1.getFxImage(),
                Sprite.brick_exploded2.getFxImage(),
        };
        wallImg = Sprite.wall.getFxImage();
        grassImg = Sprite.grass.getFxImage();
        portalImg = Sprite.portal.getFxImage();
    }

    public static void createCustomImg() {
        createConstantImg();
        try {
            bombImg = new Image[]{
                    new Image(new FileInputStream("res/sprites/custom_sprite/bomb01.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                    new Image(new FileInputStream("res/sprites/custom_sprite/bomb02.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                    new Image(new FileInputStream("res/sprites/custom_sprite/bomb03.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
            };

            ballomImg = new Image[][]{
                    //LEFT
                    {Sprite.ballom_left0.getFxImage(),
                            Sprite.ballom_left1.getFxImage(),
                            Sprite.ballom_left2.getFxImage()},
                    //UP
                    {Sprite.ballom_left0.getFxImage(),
                            Sprite.ballom_left1.getFxImage(),
                            Sprite.ballom_left2.getFxImage()},
                    //RIGHT
                    {Sprite.ballom_right0.getFxImage(),
                            Sprite.ballom_right1.getFxImage(),
                            Sprite.ballom_right2.getFxImage()},
                    //UP
                    {Sprite.ballom_right0.getFxImage(),
                            Sprite.ballom_right1.getFxImage(),
                            Sprite.ballom_right2.getFxImage()},
                    // DIE
                    {Sprite.ballom_dead.getFxImage()}
            };

            onealImg = new Image[][] {
                    {Sprite.oneal_left0.getFxImage(),
                            Sprite.oneal_left1.getFxImage(),
                            Sprite.oneal_left2.getFxImage()} ,

                    {Sprite.oneal_left0.getFxImage(),
                            Sprite.oneal_left1.getFxImage(),
                            Sprite.oneal_left2.getFxImage()} ,

                    {Sprite.oneal_right0.getFxImage(),
                            Sprite.oneal_right1.getFxImage(),
                            Sprite.oneal_right2.getFxImage()} ,

                    {Sprite.oneal_right0.getFxImage(),
                            Sprite.oneal_right1.getFxImage(),
                            Sprite.oneal_right2.getFxImage()} ,

                    {Sprite.oneal_dead.getFxImage()}
            };

            bomberImg = new Image[][] {
                    //LEFT:0
                    {Sprite.player_left_0.getFxImage(),
                            Sprite.player_left_1.getFxImage(),
                            Sprite.player_left_2.getFxImage()},
                    //UP:1
                    {Sprite.player_up_0.getFxImage(),
                            Sprite.player_up_1.getFxImage(),
                            Sprite.player_up_2.getFxImage()},
                    //RIGHT:2
                    {
                            Sprite.player_right_0.getFxImage(),
                            //new Image(new FileInputStream("res/sprites/custom_sprite/player09.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                            Sprite.player_right_1.getFxImage(),
                            Sprite.player_right_2.getFxImage()},
                    //DOWN:3
                    {Sprite.player_down_0.getFxImage(),
                            Sprite.player_down_1.getFxImage(),
                            Sprite.player_down_2.getFxImage()},
                    //DEATH:4
                    {Sprite.player_dead_0.getFxImage(),
                            Sprite.player_dead_1.getFxImage(),
                            Sprite.player_dead_2.getFxImage()}
            };

            bombItemImg = new Image(new FileInputStream("res/sprites/custom_sprite/itembomb.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true);
            speedItemImg = new Image(new FileInputStream("res/sprites/custom_sprite/itemspeed.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true);
            flameItemImg = new Image(new FileInputStream("res/sprites/custom_sprite/itemflame.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true);

            brickImg = new Image[]{
                    new Image(new FileInputStream("res/sprites/custom_sprite/brick02.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                    new Image(new FileInputStream("res/sprites/custom_sprite/brick03.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                    new Image(new FileInputStream("res/sprites/custom_sprite/brick04.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
                    //Sprite.brick_exploded2.getFxImage(),
            };
            wallImg = new Image(new FileInputStream("res/sprites/custom_sprite/grass02.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true);
            grassImg = new Image(new FileInputStream("res/sprites/custom_sprite/grass08.jpg"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true);
            portalImg = new Image(new FileInputStream("res/sprites/custom_sprite/portal01.png"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true);;
        }
        // new Image(new FileInputStream("res/sprites/custom_sprite/"), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, true, true),
        catch (FileNotFoundException e) {
            System.out.println("Error while init img");
        }
    }

    public static void createConstantImg() {
        try {
            flameImg = new Image[]{
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
            dollImg = new Image[][] {
                    {Sprite.doll_left1.getFxImage(),
                            Sprite.doll_left2.getFxImage(),
                            Sprite.doll_left3.getFxImage(),
                            Sprite.doll_right1.getFxImage(),
                            Sprite.doll_right2.getFxImage(),
                            Sprite.doll_right3.getFxImage()},
                    {Sprite.doll_dead.getFxImage()}
            };

            minvoImg = new Image[][] {
                    {Sprite.minvo_left1.getFxImage(),
                            Sprite.minvo_left2.getFxImage(),
                            Sprite.minvo_left3.getFxImage()} ,

                    {Sprite.minvo_left1.getFxImage(),
                            Sprite.minvo_left2.getFxImage(),
                            Sprite.minvo_left3.getFxImage()} ,

                    {Sprite.minvo_right1.getFxImage(),
                            Sprite.minvo_right2.getFxImage(),
                            Sprite.minvo_right3.getFxImage()} ,

                    {Sprite.minvo_right1.getFxImage(),
                            Sprite.minvo_right2.getFxImage(),
                            Sprite.minvo_right3.getFxImage()} ,

                    {Sprite.minvo_dead.getFxImage()}
            };
        } catch (FileNotFoundException e) {
            System.out.println("Error while init flame img");
        }
    }
}
