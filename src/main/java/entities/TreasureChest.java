package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TreasureChest extends Item {
    public TreasureChest(int x, int y) {
        super(x, y, "Chest", 60, 60);
        images = new Image[1];
        images[0] = utility.ResourceLoader.loadImage("/objects/treasure.png");
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(images[0], x, y, width, height);
    }
}

