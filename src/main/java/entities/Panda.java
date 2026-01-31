package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import maps.World;
import utility.ResourceLoader;

public class Panda extends Entity{

    Rectangle2D domain;
    public Panda(int startX, int startY, int width, int height, int speed, int health, World world){
        super(startX, startY, width, height, speed, health, world);

        this.direction = 1;
        this.isMoving = false;
        this.currentFrame = 0;
        this.animationCounter = 0;
        this.isDamaged = false;
        this.power = 20;
        this.domain = new Rectangle2D(x-120, y-120, 240, 240);
        loadImages();
    }
    public void loadImages(){
        images = new Image[20];
        images[0] = ResourceLoader.loadImage("/enemies/00_Panda.png");
        images[1] = ResourceLoader.loadImage("/enemies/04_Panda.png");
        images[2] = ResourceLoader.loadImage("/enemies/08_Panda.png");
        images[3] = ResourceLoader.loadImage("/enemies/12_Panda.png");
        images[4] = ResourceLoader.loadImage("/enemies/01_Panda.png");
        images[5] = ResourceLoader.loadImage("/enemies/05_Panda.png");
        images[6] = ResourceLoader.loadImage("/enemies/09_Panda.png");
        images[7] = ResourceLoader.loadImage("/enemies/13_Panda.png");
        images[8] = ResourceLoader.loadImage("/enemies/02_Panda.png");
        images[9] = ResourceLoader.loadImage("/enemies/06_Panda.png");
        images[10] = ResourceLoader.loadImage("/enemies/10_Panda.png");
        images[11] = ResourceLoader.loadImage("/enemies/14_Panda.png");
        images[12] = ResourceLoader.loadImage("/enemies/03_Panda.png");
        images[13] = ResourceLoader.loadImage("/enemies/07_Panda.png");
        images[14] = ResourceLoader.loadImage("/enemies/11_Panda.png");
        images[15] = ResourceLoader.loadImage("/enemies/15_Panda.png");
    }

    public void update(Player player){
        updateAction(player);
        updateFrames();
        animation();
    }
    public void updateAction(Player player){
        if(isDamaged){
            recoil(player);
        }
        else if (domain.intersects(player.hitbox)) {
            int metaX = player.getX();
            int metaY = player.getY();

            if (x > metaX) {
                direction = 2;
            } else if (x < metaX) {
                direction = 3;
            }

            if (y > metaY) {
                direction = 0;
            } else if (y < metaY) {
                direction = 1;
            }

            move(player);
        }
    }
    private void updateFrames() {


        frames = new Image[4];

        switch (direction) {
            case 1 -> {
                frames[0] = images[4];
                frames[1] = images[5];
                frames[2] = images[6];
                frames[3] = images[7];
            }
            case 0 -> {
                frames[0] = images[8];
                frames[1] = images[9];
                frames[2] = images[10];
                frames[3] = images[11];
            }
            case 2 -> {
                frames[0] = images[12];
                frames[1] = images[13];
                frames[2] = images[14];
                frames[3] = images[15];
            }
            case 3 -> {
                frames[0] = images[0];
                frames[1] = images[1];
                frames[2] = images[2];
                frames[3] = images[3];
            }
        }
    }
    private void animation() {
        animationCounter++;
        int animationSpeed = 30;
        if (animationCounter >= animationSpeed) {
            currentFrame++;
            animationCounter = 0;
        }

        if (currentFrame >= frames.length) {
            currentFrame = 0;
        }
    }
    public void move(Player player) {
        if (direction == 1 && checkLimits(x, y + speed, player)) {
            y += speed;

        } else if (direction == 0 && checkLimits(x, y - speed, player)) {
            y -= speed;

        } else if (direction == 2 && checkLimits(x - speed, y, player)) {
            x -= speed;

        } else if (direction == 3 && checkLimits(x + speed, y, player)) {
            x += speed;
        }
        hitbox = newHitbox(x, y);
        domain = new Rectangle2D(x-80, y-80 ,200, 200);
    }

    public void recoil(Player player){
        if (direction == 1 && checkLimits(x, y - speed, player)) {
            y -= 1;

        } else if (direction == 0 && checkLimits(x, y + speed, player)) {
            y += 1;

        } else if (direction == 2 && checkLimits(x + speed, y, player)) {
            x += 1;

        } else if (direction == 3 && checkLimits(x - speed, y, player)) {
            x -= 1;
        }
        hitbox = newHitbox(x, y);
        domain = new Rectangle2D(x-80, y-80 ,200, 200);

    }
    @Override
    public void takeDamage(int amount){
        if(!isDamaged){
            health -= amount;
            isDamaged = true;
            colorAdjust.setHue(-0.5);
            damageTime = System.nanoTime();
        }
    }
    public boolean checkLimits(int nextX, int nextY, Player player) {

        Rectangle2D nextHitbox = new Rectangle2D(
                nextX,
                nextY,
                width,
                height
        );

        return world.getCurrentMap().isWalkable(nextHitbox, this, player);
    }
    @Override
    public void draw(GraphicsContext gc){
        long damageDuration = 500_000_000;
        if (isDamaged && (System.nanoTime() - damageTime < damageDuration)) {
            gc.setEffect(colorAdjust);
        } else {
            isDamaged = false;
            colorAdjust.setHue(0);
            gc.setEffect(null);
        }
        gc.drawImage(frames[currentFrame], x, y, width, height);
        gc.setEffect(null);
    }

}
