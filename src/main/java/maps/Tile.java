package maps;

import javafx.scene.image.Image;
import utility.ResourceLoader;

public class Tile {
    private final Image image;
    private final boolean isWalkable;
    int width = 40;
    int height = 40;

    public Tile(boolean isWalkable, String filepath) {
        this.isWalkable = isWalkable;
        image = ResourceLoader.loadImage(filepath);
    }

    public Image getImage(){
        return image;
    }
    public boolean isWalkable() {
        return isWalkable;
    }

}
