package controls;


import game.GameLoop;
import javafx.scene.Scene;
import entities.Player;
import javafx.stage.Stage;


public class KeyBoard {
    Player player;
    GameLoop loop;
    public KeyBoard(Player player, GameLoop loop) {

        this.player = player;
        this.loop = loop;
    }

    public void setUpControls(Scene scene, Stage stage) {

        scene.setOnKeyPressed(event -> {

            switch (event.getCode()) {
                case W -> player.setUpPressed(true);
                case S -> player.setDownPressed(true);
                case A -> player.setLeftPressed(true);
                case D -> player.setRightPressed(true);
                case K -> player.setKPressed(true);
                case SPACE -> player.setSpacePressed(true);
                case P -> loop.switchPaused();
                case V -> loop.volumeSettings();
                case B -> loop.showShop();
                case ESCAPE -> loop.exit(stage);
                case O -> player.checkPosition();
            }

        });

        scene.setOnKeyReleased(event -> {

            switch (event.getCode()) {
                case W -> player.setUpPressed(false);
                case S -> player.setDownPressed(false);
                case A -> player.setLeftPressed(false);
                case D -> player.setRightPressed(false);
                case K -> player.setKPressed(false);
                case SPACE -> player.setSpacePressed(false);
            }

        });
    }
}
