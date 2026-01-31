package sounds;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utility.ResourceLoader;

public class GameMusic {

    private final MediaPlayer mediaPlayer;

    public GameMusic(String path) {

        Media media = ResourceLoader.loadMedia(path);

        if (media == null) {
            throw new RuntimeException("Impossibile inizializzare GameMusic: " + path);
        }

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void playMusic() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }
}


