package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Item {

    protected int x, y, width, height;
    protected String type;
    protected Rectangle2D hitbox;
    protected Image[] images;
    public Item(int x, int y, String type, int width, int height) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle2D(x, y, width, height);
    }

    abstract public void draw(GraphicsContext gc);
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getType() {
        return type;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


    public Rectangle2D getHitbox() {
        return hitbox;
    }
}