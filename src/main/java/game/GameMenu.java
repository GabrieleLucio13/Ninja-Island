package game;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sounds.AudioManager;

public class GameMenu {
    Image backgroundImage;
    private GameState gameState;
    private AudioManager audioManager;
    public GameMenu(GameState gameState, AudioManager audioManager){
        this.gameState = gameState;
        this.audioManager = audioManager;
    }
    public void startMenu(Stage primaryStage) {

        VBox menuLayout = new VBox(10);
        menuLayout.setStyle("-fx-alignment: center;");

        backgroundImage = utility.ResourceLoader.loadImage("/background/backGroundNinjaIsland.jpg");
        BackgroundImage bg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        menuLayout.setBackground(new Background(bg));

        //titolo
        Label titleLabel = new Label("NINJA ISLAND");

        titleLabel.setFont(new Font("Papyrus", 60));
        titleLabel.setStyle("-fx-text-fill: #ffd700;");  // Colore nero
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        // pulsanti
        Button newGameButton = new Button("Nuova Partita");
        Button loadGameButton = new Button("Continua");
        Button settingsButton = new Button("Impostazioni");
        Button exitButton = new Button("Esci");

        newGameButton.setStyle("-fx-font-size: 20px; -fx-background-color: white;");
        loadGameButton.setStyle("-fx-font-size: 20px; -fx-background-color: white;");
        settingsButton.setStyle("-fx-font-size: 20px; -fx-background-color: white;");
        exitButton.setStyle("-fx-font-size: 20px; -fx-background-color: white;");

        menuLayout.getChildren().addAll(titleLabel, newGameButton, loadGameButton, settingsButton, exitButton);

        newGameButton.setOnAction(e -> {
            startNewGame(primaryStage);
        });

        loadGameButton.setOnAction(e -> {
            loadGame(primaryStage);
        });

        settingsButton.setOnAction(e ->{
           settings();
        });

        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        Scene menuScene = new Scene(menuLayout, 640, 640);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    // Metodo per avviare una nuova partita
    private void startNewGame(Stage primaryStage) {

        Application app = new Application();
        try {
            app.startGame(primaryStage, null, audioManager);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadGame(Stage primaryStage){
        Application app = new Application();
        try {
            app.loadGame(primaryStage, gameState, audioManager);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void settings(){
        Stage settingStage = new Stage();
        new GameSettings(settingStage, audioManager);
        settingStage.show();
    }
    public void goToMainMenu(Stage primaryStage) {
        startMenu(primaryStage);
    }
}
