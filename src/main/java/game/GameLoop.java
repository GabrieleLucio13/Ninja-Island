package game;

import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import maps.World;
import sounds.AudioManager;
import sounds.GameMusic;
import maps.Shop;

public class GameLoop {
    GameMusic backgroundMusic, bossbattleMusic;
    AudioManager audioManager;
    private long lastUpdateTime = 0;
    private AnimationTimer loop;
    private Player player;
    private World world;
    private GameUI ui;
    private Shop shop;
    private double canvasWidth;
    private double canvasHeight;
    private boolean isPaused, isOver, youWon = false;
    GameMenu menu;
    GameState gameState;
    PauseMenu pause = new PauseMenu();
    GameOver gameOver = new GameOver();
    WinMenu winMenu = new WinMenu();
    public GameLoop(Player player, World world, GameUI ui, AudioManager audioManager, double canvasWidth, double canvasHeight) {

        this.player = player;
        this.world = world;
        this.ui = ui;
        this.audioManager = audioManager;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        shop = new Shop(player);
        gameState = new GameState(player, world);
        backgroundMusic = audioManager.getMusicTrack(0);
        bossbattleMusic = audioManager.getMusicTrack(1);
    }
    public void start(GraphicsContext gc) {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(lastUpdateTime > 0) {
                    double maxDeltaTime = 0.0333;
                    double deltaTime = (now - lastUpdateTime) / 1_000_000_000.0;
                    if(deltaTime > maxDeltaTime) {
                        deltaTime = maxDeltaTime;
                    }
                    updateGame();
                    renderGame(gc);
                }
                lastUpdateTime = now;
            }
        };

        loop.start();
        backgroundMusic.playMusic();
    }
    public void updateGame() {
        if(!isPaused && !isOver && !youWon)
        {
            player.update(player);
            world.getCurrentMap().update(player);

            if(world.getCurrentMap() == world.getBossMap()){
                backgroundMusic.stop();
                bossbattleMusic.playMusic();
            }

            if(world.getCurrentMap() != world.getBossMap()){
                bossbattleMusic.stop();
                backgroundMusic.playMusic();
            }

            if(player.getHealth()<=0){
                backgroundMusic.stop();
                bossbattleMusic.stop();
                isOver = true;
            }

            if(world.getCurrentMapX() == 1 && world.getCurrentMapY() == 2){
                backgroundMusic.stop();
                bossbattleMusic.stop();
                youWon = true;
            }

        }
    }
    public void renderGame(GraphicsContext gc) {

        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        world.getCurrentMap().draw(gc);

        if(player.getHealth()>0){
            player.draw(gc);
        }

        if(isPaused) {
            pause.render(gc);
        }
        if(isOver) {
            gameOver.render(gc);
            player.deathAnimation(gc);
            loop.stop();
        }
        if(youWon){
            winMenu.render(gc);
            player.winAnimation(gc);
            loop.stop();
        }
        else{
            ui.draw(gc);
        }
    }
    public void switchPaused() {
        isPaused = !isPaused;
    }
    public void showShop(){
        ShopMenu menu = new ShopMenu(shop);
        menu.display();
    }
    public void volumeSettings() {
        if(isPaused)
        {
            Stage settingStage = new Stage();
            new GameSettings(settingStage, audioManager);
            settingStage.show();
        }
    }
    public void exit(Stage stage) {
        if(isPaused)
        {
            loop.stop();
            backgroundMusic.stop();
            bossbattleMusic.stop();
            menu = new GameMenu(gameState, audioManager);
            menu.goToMainMenu(stage);
        }
        else if(isOver || youWon)
        {
            backgroundMusic.stop();
            menu = new GameMenu(null, audioManager);
            menu.goToMainMenu(stage);
        }
    }
}
