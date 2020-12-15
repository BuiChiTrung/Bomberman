package uet.oop.bomberman.util;

import jaco.mp3.player.MP3Player;
import uet.oop.bomberman.scene.Container;

import java.io.File;

public class SoundUtil {

    public static MP3Player themeSongPlayer = new MP3Player();
    public static MP3Player footStepPlayer = new MP3Player();
    static {
        themeSongPlayer.addToPlayList(new File("res/sound/themeSound.mp3"));
        themeSongPlayer.setRepeat(true);
        footStepPlayer.addToPlayList(new File("res/sound/footStepSound.mp3"));
        footStepPlayer.setRepeat(true);
        footStepPlayer.play();
        footStepPlayer.pause();
        themeSongPlayer.play();
    }
    public static void playThemeSound() {
        if(!Container.soundOn) {
            return ;
        }
        themeSongPlayer.play();
    }

    public static void pauseThemeSound() {
        themeSongPlayer.pause();
    }

    public static void playExplodingSound() {
        if(!Container.soundOn) {
            return ;
        }
        new MP3Player(new File("res/sound/explode.mp3")).play();
    }

    public static void playFootStepSound() {
        if(!Container.soundOn) {
            return ;
        }
        if(footStepPlayer.isPaused()) {
            footStepPlayer = new MP3Player();
            footStepPlayer.addToPlayList(new File("res/sound/footStepSound.mp3"));
            footStepPlayer.setRepeat(true);
            footStepPlayer.play();
        }
    }

    public static void pauseFootStepSound() {
        footStepPlayer.pause();
    }

    public static void playDeadSound() {
        if(!Container.soundOn) {
            return ;
        }
        MP3Player player = new MP3Player();
        player.addToPlayList(new File("res/sound/deathSound.mp3"));
        player.play();
    }
}
