package game;

import entities.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameUI {
    private Player player;
    private Image keyImage;
    private Image ammoImage;
    private Image coinImage;

    public GameUI(Player player) {
        this.player = player;

        keyImage = utility.ResourceLoader.loadImage("/objects/key.png");
        ammoImage = utility.ResourceLoader.loadImage("/objects/kunai.png");
        coinImage = utility.ResourceLoader.loadImage("/objects/00_Coin.png");
    }


    public void draw(GraphicsContext gc) {
        drawHealth(gc);
        drawKey(gc);
        drawAmmo(gc);
        drawCoin(gc);

        gc.setFill(Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 15));
        gc.fillText("(P) : metti in pausa", 500, 625);
    }

    public void drawHealth(GraphicsContext gc)
    {
        gc.setFill(Color.RED);
        gc.fillRect(10, 10, player.getHealth() * 2, 20); // Ridimensiona in base alla salute

        gc.setFill(Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans Ms", 20));
        gc.fillText("HP: " + player.getHealth(), 10, 50);
    }

    public void drawKey(GraphicsContext gc)
    {

        gc.drawImage(keyImage, 10, 580, 40, 40);

        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans Ms", 30));
        gc.fillText(":" + player.getKeyPlayer(), 60, 610);
    }

    public void drawAmmo(GraphicsContext gc)
    {
        gc.drawImage(ammoImage, 10, 75, 80, 20);

        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans Ms", 20));
        gc.fillText(":" + player.getAmmo(), 95, 95);

        gc.setFont(new javafx.scene.text.Font("Comic Sans Ms", 20));
        gc.fillText("(k) per lanciare", 10, 125);
    }

    public void drawCoin(GraphicsContext gc)
    {

        gc.drawImage(coinImage, 100, 594, 20, 20);

        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Arial", 20));
        gc.fillText(":" + player.getCoinPlayer(), 120, 610);

        gc.setFill(Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 15));
        gc.fillText("(B) : apri shop", 90, 625);
    }


    public void drawMiniMap(GraphicsContext gc){

    }
}
