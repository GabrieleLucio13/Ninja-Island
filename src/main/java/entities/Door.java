package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Door extends Item{

    Rectangle2D keyHole;
    public Door(int x, int y){
        super(x, y, "Door", 40, 40);
        images = new Image[1];
        images[0] = utility.ResourceLoader.loadImage("/objects/door.png");

        keyHole = new Rectangle2D(x-5, y-5, width+5, height+5);

    }
    public Rectangle2D getKeyHole(){
        return keyHole;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(images[0], x, y ,width, height);
    }
}
