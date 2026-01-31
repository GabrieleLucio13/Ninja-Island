package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import maps.World;

public abstract class Entity {

    protected int x, y;
    protected int width;
    protected int height;
    protected Rectangle2D hitbox;
    protected int speed;
    protected int power;
    protected int health;
    protected int direction;
    protected boolean isDamaged, isMoving;
    protected Image[] images;
    protected Image[] frames;
    protected int currentFrame = 0;
    protected int animationCounter = 0;
    protected int animationTimer;
    protected long damageTime;
    protected final ColorAdjust colorAdjust;
    protected World world;

    public Entity(int x, int y, int width, int height, int speed, int health, World world) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.health = health;
        this.world = world;
        this.hitbox = new Rectangle2D(x, y, width, height);
        this.colorAdjust = new ColorAdjust();
    }
    abstract public void takeDamage(int amount);
    abstract public void update(Player player);
    abstract public void draw(GraphicsContext gc);
    public Rectangle2D newHitbox(int x, int y) {
        return new Rectangle2D(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getHealth(){
        return health;
    }
    public Rectangle2D getHitbox() {
        return hitbox;
    }
    public int getPower(){
        return power;
    }
    public boolean isDamaged() {
        return isDamaged;
    }

}
