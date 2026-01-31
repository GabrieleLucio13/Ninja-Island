package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import maps.World;
import utility.ResourceLoader;

public class AoEEnemy extends Entity {


    private enum State {
        IDLE,
        CHARGING,
        ATTACKING
    }

    private State currentState;
    private long chargingCooldown = 1000;
    private long attackDuration = 300;
    private long stateStartTime;
    private int attackRange = 100;
    private Rectangle2D attackHitbox;

    public AoEEnemy(int x, int y, int width, int height, int speed, int health, World world) {
        super(x, y, width, height, speed, health, world);

        this.power = 20;
        this.currentState = State.IDLE;

        hitbox = new Rectangle2D(x+40, y+40, width-40, height-40);
        attackHitbox = new Rectangle2D(x - attackRange / 2, y - attackRange / 2, width + attackRange, height + attackRange);
        loadImages();
    }

    private void loadImages() {
        images = new Image[5];
        images[0] = ResourceLoader.loadImage("/enemies/00_Hit.png");
        images[1] = ResourceLoader.loadImage("/enemies/04_Hit.png");
        images[2] = ResourceLoader.loadImage("/enemies/01_Hit.png");
        images[3] = ResourceLoader.loadImage("/enemies/02_Hit.png");
        images[4] = ResourceLoader.loadImage("/enemies/03_Hit.png");
    }

    @Override
    public void update(Player player){
        updateAction(player);
        updateFrames();
        animation();
    }



    public void updateAction(Player player) {

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - stateStartTime;


        switch (currentState) {
            case IDLE -> {
                if (player.getHitbox().intersects(attackHitbox)) {
                    currentState = State.CHARGING;
                    stateStartTime = currentTime;
                }
            }
            case CHARGING -> {
                if (elapsedTime > chargingCooldown) {
                    currentState = State.ATTACKING;
                    stateStartTime = currentTime;
                }
            }
            case ATTACKING -> {
                if (elapsedTime >= attackDuration) {
                    currentState = State.IDLE;
                    stateStartTime = currentTime;
                }
                if (player.getHitbox().intersects(attackHitbox)) {
                        player.takeDamage(80);
                }
                health = -1;
            }
        }
    }

    private void updateFrames() {
        switch (currentState) {
            case IDLE -> {
                frames = new Image[2];
                frames[0] = images[0];
                frames[1] = images[1];
            }
            case CHARGING -> {
                frames = new Image[4];
                frames[0] = images[1];
                frames[1] = images[4];
                frames[2] = images[3];
                frames[3] = images[2];
            }
            case ATTACKING -> {
                frames = new Image[1];
                frames[0] = images[2];
            }
        }
    }
    public void animation(){
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

    @Override
    public void takeDamage(int amount){
        if(!isDamaged){
            health -= amount;
            isDamaged = true;
            colorAdjust.setHue(-0.5);
            damageTime = System.nanoTime();
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(frames[currentFrame], x, y, width, height);

        if (currentState == State.CHARGING) {
            gc.setFill(Color.RED);
            gc.setGlobalAlpha(0.2);
            gc.fillRect(x - attackRange / 2, y - attackRange / 2, width + attackRange, height + attackRange);
            gc.setGlobalAlpha(1);
        }
    }
}
