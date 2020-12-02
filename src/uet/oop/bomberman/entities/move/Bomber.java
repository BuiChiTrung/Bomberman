package uet.oop.bomberman.entities.move;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Point;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.move.enemy.Enemy;
import uet.oop.bomberman.entities.still.item.BombItem;
import uet.oop.bomberman.entities.still.item.FlameItem;
import uet.oop.bomberman.entities.still.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.timeline.Container;
import uet.oop.bomberman.util.DirectionUtil;
import uet.oop.bomberman.util.Util;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

public class Bomber extends MovingEntity {

    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 5;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private static final int DESTROY_IMG_ID = 3 * NUMBER_OF_MOVE_TO_CHANGE_IMG;
    private int bombPower = 5;
    private int bombNumber = 2;
    private boolean isIncreaseSpeed = false;
    private boolean arrowKeyIsRelease = true;
    private static final Image[][] imgState = {
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

    public int getBombPower() {
        return bombPower;
    }

    public Bomber(Point pos, Image img) {
        super(pos, img);
        velocity = 0.125 / 2;
    }

    private void placeBomb() {
        Bomb bomb = new Bomb(getMostAreaStandingCells(), Sprite.bomb0.getFxImage());
        Container.bombs.add(bomb);
    }

    public boolean collideWithEnemy() {
        for (Enemy enemy: Container.enemies) {
            if (pos.distance(enemy.getPos()) < 1)
                return true;
        }
        return false;
    }

    @Override
    public void move() {
        if(!arrowKeyIsRelease) {
            updateDirectionAndStepInDirect(direction);
            moveAlongDirection();
        }
        eatItems();
    }

    private void eatItems() {
        ArrayList<Point> standingCells = getStandingCells(pos.x, pos.y);
        for(Point pos: standingCells) {
            if(Util.getLast(Container.stillEntities[(int)pos.x][(int)pos.y]) instanceof BombItem) {
                addBomb();
                Util.removeLastEntity(Container.stillEntities[(int)pos.x][(int)pos.y]);
            }
            if(Util.getLast(Container.stillEntities[(int)pos.x][(int)pos.y]) instanceof FlameItem) {
                upgradePower();
                Util.removeLastEntity(Container.stillEntities[(int)pos.x][(int)pos.y]);
            }
            if(Util.getLast(Container.stillEntities[(int)pos.x][(int)pos.y]) instanceof SpeedItem) {
                increaseSpeed();
                Util.removeLastEntity(Container.stillEntities[(int)pos.x][(int)pos.y]);
            }
        }
    }

    private void addBomb() {
        bombNumber++;
    }

    private void upgradePower() {
        bombPower++;
    }

    private void increaseSpeed() {
        if(isIncreaseSpeed) {
            return ;
        }
        velocity *= 2;
        isIncreaseSpeed = true;
    }
    @Override
    public void updateImg() {
        img = imgState[DirectionUtil.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION];
    }

    @Override
    public void changeToDeathImg() {
        imgId++;
        if (imgId == DESTROY_IMG_ID) {
            destroy = true;
        }
        else {
            img = imgState[4][imgId / NUMBER_OF_MOVE_TO_CHANGE_IMG];
        }
    }

    public void handlePress(KeyCode key) {
        if(key == KeyCode.RIGHT || key == KeyCode.LEFT || key == KeyCode.UP || key == KeyCode.DOWN) {
            updateDirectionAndStepInDirect(DirectionUtil.getDirectionFromKeyCode(key));
            arrowKeyIsRelease = false;
        }
        if(key == KeyCode.SPACE) {
            if (Container.bombs.size() < bombNumber)
                Container.bomber.placeBomb();
        }
    }

    public void handleRelease(KeyCode key) {
        if(key == KeyCode.RIGHT || key == KeyCode.LEFT || key == KeyCode.UP || key == KeyCode.DOWN) {
            arrowKeyIsRelease = true;
        }
    }
}