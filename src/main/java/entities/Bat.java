package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import maps.World;
import utility.ResourceLoader;

public class Bat extends Entity{
    public Bat(int startX, int startY, int width, int height, int speed, int health, World world, int direction){
        super(startX, startY, width, height, speed, health, world);

        this.direction = direction;
        this.isMoving = false;
        this.currentFrame = 0;
        this.animationCounter = 0;
        this.isDamaged = false;
        this.power = 10;
        loadImages();
    }
    public void loadImages(){
        images = new Image[16];
        images[0] = ResourceLoader.loadImage("/enemies/00_Bat.png");
        images[1] = ResourceLoader.loadImage("/enemies/04_Bat.png");
        images[2] = ResourceLoader.loadImage("/enemies/08_Bat.png");
        images[3] = ResourceLoader.loadImage("/enemies/12_Bat.png");
        images[4] = ResourceLoader.loadImage("/enemies/01_Bat.png");
        images[5] = ResourceLoader.loadImage("/enemies/05_Bat.png");
        images[6] = ResourceLoader.loadImage("/enemies/09_Bat.png");
        images[7] = ResourceLoader.loadImage("/enemies/13_Bat.png");
        images[8] = ResourceLoader.loadImage("/enemies/02_Bat.png");
        images[9] = ResourceLoader.loadImage("/enemies/06_Bat.png");
        images[10] = ResourceLoader.loadImage("/enemies/10_Bat.png");
        images[11] = ResourceLoader.loadImage("/enemies/14_Bat.png");
        images[12] = ResourceLoader.loadImage("/enemies/03_Bat.png");
        images[13] = ResourceLoader.loadImage("/enemies/07_Bat.png");
        images[14] = ResourceLoader.loadImage("/enemies/11_Bat.png");
        images[15] = ResourceLoader.loadImage("/enemies/15_Bat.png");

    }

    public void update(Player player){
        updateAction(player);
        updateFrames();
        animation();
    }
    public void updateAction(Player player){
        animationTimer++;
        if(animationTimer > 150){
            switch (direction) {
                case 1 -> direction = 0;
                case 0 -> direction = 1;
                case 2 -> direction = 3;
                case 3 -> direction = 2;
            }
            animationTimer = 0;
        }
        move();
        if(hitbox.intersects(player.hitbox)){
            player.takeDamage(power);
        }
    }
    private void updateFrames() {
        frames = new Image[4];
        switch (direction) {
            case 1 -> {
                frames[0] = images[0];
                frames[1] = images[1];
                frames[2] = images[2];
                frames[3] = images[3];
            }
            case 0 -> {
                frames[0] = images[4];
                frames[1] = images[5];
                frames[2] = images[6];
                frames[3] = images[7];
            }
            case 2 -> {
                frames[0] = images[8];
                frames[1] = images[9];
                frames[2] = images[10];
                frames[3] = images[11];
            }
            case 3 -> {
                frames[0] = images[12];
                frames[1] = images[13];
                frames[2] = images[14];
                frames[3] = images[15];
            }
        }
    }
    private void animation() {
        animationCounter++;
        int animationSpeed = 30;
        if (animationCounter >= animationSpeed) {
            currentFrame++;  // Passa al frame successivo
            animationCounter = 0;
        }

        if (currentFrame >= frames.length) {
            currentFrame = 0;
        }
    }
    public void move() {
        if (direction == 1) {
            y += speed;

        } else if (direction == 0) {
            y -= speed;

        } else if (direction == 2) {
            x -= speed;

        } else if (direction == 3) {
            x += speed;
        }
        hitbox = newHitbox(x, y);
    }
    @Override
    public void takeDamage(int amount){
        if(!isDamaged){
            health -= amount;
            isDamaged = true;
            colorAdjust.setHue(-1.0);
            damageTime = System.nanoTime();
        }
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
