package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Throwable extends Item {
    private final int currentDirection;
    private int attackPower;
    private Rectangle2D hitbox;

    public Throwable(int x, int y, int direction) {
        super(x, y, "Throwable", 40, 10);

        images = new Image[4];
        images[0]= utility.ResourceLoader.loadImage("/objects/kunai3.png");
        images[1]= utility.ResourceLoader.loadImage("/objects/kunai1.png");
        images[2]= utility.ResourceLoader.loadImage("/objects/kunai2.png");
        images[3]= utility.ResourceLoader.loadImage("/objects/kunai.png");

        this.attackPower = 5;
        this.hitbox = new Rectangle2D(x, y, width, height);
        this.currentDirection = direction;

    }
    public void update()
    {
        int speed = 4;
        switch (currentDirection) {
            case 0 -> y -= speed;
            case 1 -> y += speed;
            case 2 -> x -= speed;
            case 3 -> x += speed;
        }
        hitbox = new Rectangle2D(x,y,width, height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(currentDirection == 0){
            gc.drawImage(images[0], x, y,10, 40);
        }
        else if(currentDirection == 1){
            gc.drawImage(images[1], x, y,10, 40);
        }
        else if(currentDirection == 2){
            gc.drawImage(images[2], x, y,40, 10);
        }
        else{
            gc.drawImage(images[3], x, y,40, 10);}
    }

    public Rectangle2D getHitbox() {
        return hitbox;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void upgrade() {
        attackPower = attackPower + 7;
    }
}