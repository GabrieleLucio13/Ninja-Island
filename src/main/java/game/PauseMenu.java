package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PauseMenu {
    public void render(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 60));
        gc.fillText("PAUSA", 220, 250);

        gc.setFill(Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 20));
        gc.fillText("V : Cambia il Volume", 220, 370);

        gc.setFill(Color.RED);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 20));
        gc.fillText("P : riprendi il gioco", 220, 400);

        gc.setFill(Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 20));
        gc.fillText("Esc: tornare al menu", 220, 430);

        gc.setFill(Color.BLUE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 20));
        gc.fillText("<< WASD : muoviti>>", 420, 30);
        gc.fillText("<<SPACE : attacca>>", 420, 60);
    }
}
