package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WinMenu {
    public void render(GraphicsContext gc) {

        gc.setFill(Color.GOLD);
        gc.setFont(new javafx.scene.text.Font("Papyrus", 60));
        gc.fillText("HAI VINTO", 100, 250);

        gc.setFill(Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 20));
        gc.fillText("ESC : Torna al menu ", 220, 350);

    }
}
