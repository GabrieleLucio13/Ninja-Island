package game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sounds.AudioManager;


public class GameSettings {
    AudioManager audioManager;
    private Slider musicVolumeSlider;
    private Slider effectsVolumeSlider;

    public GameSettings(Stage settingsStage, AudioManager audioManager) {

        this.audioManager = audioManager;

        Label musicLabel = new Label("Volume Musica");
        musicVolumeSlider = new Slider(0, 1, audioManager.getMusicVolume()); // Da 0.0 a 1.0
        musicVolumeSlider.setShowTickLabels(true);
        musicVolumeSlider.setShowTickMarks(true);


        musicVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                audioManager.setMusicVolume(newVal.doubleValue())
        );

        // Slider per il volume degli effetti sonori
        Label effectsLabel = new Label("Volume Effetti Sonori");
        effectsVolumeSlider = new Slider(0, 1, audioManager.getEffectsVolume());
        effectsVolumeSlider.setShowTickLabels(true);
        effectsVolumeSlider.setShowTickMarks(true);

        // Aggiungi un listener per aggiornare il volume degli effetti sonori
        effectsVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                audioManager.setEffectsVolume(newVal.doubleValue())
        );

        // Configura il layout e la scena delle impostazioni
        VBox layout = new VBox(10, musicLabel, musicVolumeSlider, effectsLabel, effectsVolumeSlider);
        layout.setAlignment(Pos.CENTER);
        Scene settingsScene = new Scene(layout, 300, 200);

        settingsStage.setScene(settingsScene);
        settingsStage.setTitle("Impostazioni");
    }
}
