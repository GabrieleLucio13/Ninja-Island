package sounds;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import utility.ResourceLoader;

public class SoundEffect {

    private final AudioClip audioClip;

    public SoundEffect(String resourcePath) {

        Media media = ResourceLoader.loadMedia(resourcePath);

        if (media == null) {
            throw new RuntimeException("Impossibile caricare SoundEffect: " + resourcePath);
        }

        audioClip = new AudioClip(media.getSource());
    }

    public void play() {
        audioClip.play();
    }

    public void setVolume(double volume) {
        audioClip.setVolume(volume);
    }

    public double getVolume() {
        return audioClip.getVolume();
    }
}
