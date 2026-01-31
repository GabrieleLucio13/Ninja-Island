package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pebble extends Item{
    private final int attackPower;
    private Rectangle2D hitbox;

    public Pebble(int x, int y) {
        super(x, y, "Pebble", 20, 20);

        images = new Image[1];
        images[0]= utility.ResourceLoader.loadImage("/objects/pebble.png");

        this.attackPower = 10;
        this.hitbox = new Rectangle2D(x, y, width, height);

    }
    public void update(int direction)
    {
        int speed = 2;
        switch (direction) {
            case 0 -> y -= speed;
            case 1 -> y += speed;
            case 2 -> x -= speed;
            case 3 -> x += speed;
        }
        hitbox = new Rectangle2D(x,y,width, height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(images[0], x, y,width, height);
    }

    public Rectangle2D getHitbox() {
        return hitbox;
    }

    public int getAttackPower() {
        return attackPower;
    }
}
