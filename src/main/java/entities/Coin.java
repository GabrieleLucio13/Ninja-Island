package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coin extends Item{
    private int currentFrame;
    private double elapsedTime;
    public Coin(int x, int y)
    {

        super(x, y, "Coin", 20, 20);
        images = new Image[4];
        images[0] = utility.ResourceLoader.loadImage("/objects/00_Coin.png");
        images[1] = utility.ResourceLoader.loadImage("/objects/01_Coin.png");
        images[2] = utility.ResourceLoader.loadImage("/objects/02_Coin.png");
        images[3] = utility.ResourceLoader.loadImage("/objects/03_Coin.png");

        this.currentFrame = 0;
        this.elapsedTime = 0;
    }

    public void update() {
        elapsedTime ++;
        if (elapsedTime >= 20) {
            currentFrame = (currentFrame + 1) % images.length;
            elapsedTime = 0;
        }
    }
    @Override
    public void draw(GraphicsContext gc)
    {
        gc.drawImage(images[currentFrame], x, y, 20, 20);
    }
}
