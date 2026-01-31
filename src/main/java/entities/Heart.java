package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Heart extends Item{
    public Heart(int x, int y)
    {
        super(x, y, "Heart", 20, 20);
        images = new Image[1];
        images[0] = utility.ResourceLoader.loadImage("/objects/Heart.png");
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(images[0], x, y ,width, height);
    }

}
