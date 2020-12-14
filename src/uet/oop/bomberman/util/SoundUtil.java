package uet.oop.bomberman.util;

import jaco.mp3.player.MP3Player;

import java.io.File;

public class SoundUtil {

    public static MP3Player themeSongPlayer = new MP3Player();

    static {
        themeSongPlayer.addToPlayList(new File("res/sound/themeSound.mp3"));
        themeSongPlayer.setRepeat(true);
    }
    public static void playThemeSound() {
        themeSongPlayer.play();
    }

    public static void pauseThemeSound() {
        themeSongPlayer.pause();
    }

    public static void playExplodingSound() {
        MP3Player player = new MP3Player();
        player.addToPlayList(new File("res/sound/explode.mp3"));
        player.play();
    }

    public static void playDeadSound() {
        MP3Player player = new MP3Player();
        player.addToPlayList(new File("res/sound/deathSound.mp3"));
        player.play();
    }
}
