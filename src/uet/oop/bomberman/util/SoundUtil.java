package uet.oop.bomberman.util;

import jaco.mp3.player.MP3Player;

import java.io.File;

public class SoundUtil {
    public static void playThemeSound(String path) {
        MP3Player player = new MP3Player();
        player.addToPlayList(new File(path));
        player.setRepeat(true);
        player.play();
    }
    public static void playExplodingSound() {
        MP3Player player = new MP3Player();
        player.addToPlayList(new File("res/sound/explode.mp3"));
        player.play();
    }
}
