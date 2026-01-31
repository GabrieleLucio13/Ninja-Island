package game;

import controls.KeyBoard;
import entities.Player;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import maps.World;
import sounds.*;


public class Application extends javafx.application.Application {

    private Player player;
    private World world;
    private GameLoop gameLoop;
    private GameUI ui;
    private GameWindow window;
    GameState gameState;
    AudioManager audioManager = new AudioManager();

    @Override
    public void start(Stage primaryStage) throws Exception {

        GameMenu menu = new GameMenu(gameState, audioManager);
        menu.startMenu(primaryStage);
    }
    private void initializeGame(Stage primaryStage, GameState gameState, AudioManager audioManager) {
        if (gameState == null) {
            world = new World(5, 5);
            player = new Player(300, 500, 40, 40, 2, 100, world, audioManager);
        }
        else{
            player = gameState.getPlayer();
            world = gameState.getWorld();
            this.audioManager = audioManager;
        }

        ui = new GameUI(player);

        window = new GameWindow(primaryStage);
        window.setUpWindow();

        gameLoop = new GameLoop(player, world, ui, audioManager, window.getCanvasWidth(), window.getCanvasHeight());

        KeyBoard controls = new KeyBoard(player, gameLoop);
        controls.setUpControls(window.getScene(), primaryStage);

        GraphicsContext gc = window.getCanvas().getGraphicsContext2D();
        gameLoop.start(gc);
    }

    public void startGame(Stage primaryStage, GameState gameState, AudioManager audioManager) {
        initializeGame(primaryStage, gameState, audioManager);
    }
    public void loadGame(Stage primaryStage, GameState gameState, AudioManager audioManager) {
        initializeGame(primaryStage, gameState, audioManager);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
