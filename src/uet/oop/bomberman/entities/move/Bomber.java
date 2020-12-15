package uet.oop.bomberman.entities.move;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.util.Point;
import uet.oop.bomberman.entities.still.Portal;
import uet.oop.bomberman.entities.still.bomb.Bomb;
import uet.oop.bomberman.entities.move.enemy.Enemy;
import uet.oop.bomberman.entities.still.item.Item;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Container;
import uet.oop.bomberman.scene.MainScene;
import uet.oop.bomberman.util.*;

import java.util.ArrayList;

public class Bomber extends MovingEntity {

    private static final int NUMBER_OF_MOVE_TO_CHANGE_IMG = 5;
    private static final int NUMBER_OF_IMG_PER_DIRECTION = 3;
    private static final int DESTROY_IMG_ID = 3 * NUMBER_OF_MOVE_TO_CHANGE_IMG;
    private static final double MAX_VELOCITY = 0.125;

    private int bombPower = 1;
    private int bombNumber = 1;
    private int currentPlacedBomb = 0;
    private boolean arrowKeyIsRelease = true;
    private static final Image[][] imgState = ImgFactory.bomberImg;

    public int getBombPower() {
        return bombPower;
    }

    public void updateCurrentPlacedBomb() {
        currentPlacedBomb--;
    }

    public Bomber(Point pos, Image img) {
        super(pos, img);
        velocity = 0.125 / 2;
    }

    private void placeBomb() {
        Bomb bomb = new Bomb(getMostAreaStandingCells(), Sprite.bomb0.getFxImage());
        Container.stillEntities[(int) bomb.getPos().x][(int) bomb.getPos().y].add(bomb);
        currentPlacedBomb++;
    }

    public boolean collideWithEnemy() {
        for (Enemy enemy : Container.enemies) {
            if (pos.distance(enemy.getPos()) < 1)
                return true;
        }
        return false;
    }

    @Override
    public void move() {
        if (!arrowKeyIsRelease) {
            updateDirectionAndStepInDirect(direction);
            SoundUtil.playFootStepSound();
            moveAlongDirection();
            handleIfMeetPortalOrItem();
        }
        else {
            SoundUtil.pauseFootStepSound();
        }
    }

    private void handleIfMeetPortalOrItem() {
        ArrayList<Point> standingCells = getStandingCells(pos.x, pos.y);
        for (Point pos : standingCells) {
            Entity entity = Util.getLast(Container.stillEntities[(int) pos.x][(int) pos.y]);

            if (entity instanceof Item)
                ((Item) entity).affectBomber();
            else if (entity instanceof Portal && Container.enemyLeft == 0)
                MainScene.goToNextLevel();
        }
    }

    public void increaseBombNumber() {
        bombNumber++;
    }

    public void increaseBombPower() {
        bombPower++;
    }

    public void increaseSpeed() {
        velocity = Math.min(velocity * 2, MAX_VELOCITY);
    }

    @Override
    public void updateImg() {
        img = imgState[Direction.getDirectionId(direction)][(stepInDirect / NUMBER_OF_MOVE_TO_CHANGE_IMG) % NUMBER_OF_IMG_PER_DIRECTION];
    }

    @Override
    public void changeToDeathImg() {
        imgId++;
        death = true;
        if (imgId == DESTROY_IMG_ID) {
            SoundUtil.playDeadSound();
            removableFromContainer = true;
        } else {
            img = imgState[4][imgId / NUMBER_OF_MOVE_TO_CHANGE_IMG];
        }
    }

    public void handlePress(KeyCode key) {
        if (key == KeyCode.RIGHT || key == KeyCode.LEFT || key == KeyCode.UP || key == KeyCode.DOWN) {
            updateDirectionAndStepInDirect(Direction.getDirectionFromKeyCode(key));
            arrowKeyIsRelease = false;
        }
        if (key == KeyCode.SPACE) {
            if (currentPlacedBomb < bombNumber)
                Container.bomber.placeBomb();
        }
    }

    public void handleRelease(KeyCode key) {
        if (key == KeyCode.RIGHT || key == KeyCode.LEFT || key == KeyCode.UP || key == KeyCode.DOWN)
            arrowKeyIsRelease = true;
    }

    public void getPreviousLevelState(Bomber prevState) {
        bombPower = prevState.bombPower;
        bombNumber = prevState.bombNumber;
        velocity = prevState.velocity;
    }
}