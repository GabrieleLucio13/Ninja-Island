package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Key extends Item{
    public Key(int x, int y)
    {
        super(x, y, "Key", 32, 32);
        images = new Image[1];
        images[0] = utility.ResourceLoader.loadImage("/objects/key.png");
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(images[0], x, y ,width, height);
    }

}