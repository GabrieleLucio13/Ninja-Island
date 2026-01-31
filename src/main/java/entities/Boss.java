package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import maps.World;
import utility.ResourceLoader;

import java.util.Random;

public class Boss extends Entity{

    private final Random random = new Random();
    double boxX, boxY, boxWidth, boxHeight;
    Rectangle2D rightAttack, leftAttack, centralAttack, currentAttack;
    private BossState currentState;
    private long stateStartTime;
    private long idleDuration = 1000;
    private long chargeDuration = 700;
    private long attackDuration = 300;

    public enum BossState {
        IDLE,
        CHARGING,
        ATTACKING,
    }
    public Boss(int startX, int startY, int width, int height, int speed, int health, World world){
        super(startX, startY, width, height, speed, health, world);

        this.direction = 1;
        this.isMoving = false;
        this.currentFrame = 0;
        this.animationCounter = 0;

        this.currentState = BossState.IDLE;
        this.stateStartTime = System.currentTimeMillis();

        this.isDamaged = false;
        this.power = 10;

        boxX = x + 41;
        boxY = y + 170;
        boxWidth = 218;
        boxHeight = 69;

        createHitbox();
        loadImages();
    }

    private void createHitbox() {
        hitbox = new Rectangle2D(boxX, boxY, boxWidth, boxHeight);
        leftAttack = new Rectangle2D(boxX, boxY+72, boxWidth/2, boxHeight+300);
        rightAttack = new Rectangle2D(boxX+boxWidth/2 , boxY+72, boxWidth/2 , boxHeight+300);
        centralAttack = new Rectangle2D(boxX, boxY+22, boxWidth, boxHeight+50);

    }

    public void loadImages(){
        images = new Image[21];
        images[0] = ResourceLoader.loadImage("/Boss/00_Idle.png");
        images[1] = ResourceLoader.loadImage("/Boss/01_Idle.png");
        images[2] = ResourceLoader.loadImage("/Boss/02_Idle.png");
        images[3] = ResourceLoader.loadImage("/Boss/03_Idle.png");
        images[4] = ResourceLoader.loadImage("/Boss/04_Idle.png");
        images[5] = ResourceLoader.loadImage("/Boss/05_Idle.png");
        images[6] = ResourceLoader.loadImage("/Boss/00_ChargeLeft.png");
        images[7] = ResourceLoader.loadImage("/Boss/01_ChargeLeft.png");
        images[8] = ResourceLoader.loadImage("/Boss/02_ChargeLeft.png");
        images[9] = ResourceLoader.loadImage("/Boss/00_ChargeRight.png");
        images[10] = ResourceLoader.loadImage("/Boss/01_ChargeRight.png");
        images[11] = ResourceLoader.loadImage("/Boss/02_ChargeRight.png");
        images[12] = ResourceLoader.loadImage("/Boss/00_AttackLeft.png");
        images[13] = ResourceLoader.loadImage("/Boss/01_AttackLeft.png");
        images[14] = ResourceLoader.loadImage("/Boss/02_AttackLeft.png");
        images[15] = ResourceLoader.loadImage("/Boss/03_AttackLeft.png");
        images[16] = ResourceLoader.loadImage("/Boss/00_AttackRight.png");
        images[17] = ResourceLoader.loadImage("/Boss/01_AttackRight.png");
        images[18] = ResourceLoader.loadImage("/Boss/02_AttackRight.png");
        images[19] = ResourceLoader.loadImage("/Boss/03_AttackRight.png");
        images[20] = ResourceLoader.loadImage("/Boss/01_Charge.png");

        for (int i = 0; i < images.length; i++) {
            if (images[i] == null) {
                System.err.println("IMMAGINE NULLA index=" + i);
            }
        }
    }

    public void update(Player player){
        updateAction(player);
        updateFrames();
        animation();
    }
    public void updateAction(Player player){

        if(health < 100){
            chargeDuration = 500;
        }
        if(health < 50){
            idleDuration = 500;
        }

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - stateStartTime;

        switch (currentState) {
            case IDLE:
                if (elapsedTime > idleDuration) {
                    currentState = BossState.CHARGING;
                    stateStartTime = currentTime;
                    int i = random.nextInt(3);
                    if(i == 1){
                        currentAttack = rightAttack;
                    }
                    else if(i == 2){
                        currentAttack = leftAttack;
                    }
                    else{
                        currentAttack = centralAttack;
                    }
                }
                break;

            case CHARGING:
                if (elapsedTime > chargeDuration) {
                    currentState = BossState.ATTACKING;
                    stateStartTime = currentTime;
                }
                break;

            case ATTACKING:
                if (elapsedTime > attackDuration) {
                    currentState = BossState.IDLE;
                    stateStartTime = currentTime;
                    if(currentAttack.intersects(player.getHitbox())){
                        player.takeDamage(40);
                    }
                }
                break;
        }
    }

    private void updateFrames() {

        switch (currentState) {
            case IDLE -> {
                frames = new Image[6];

                frames[0] = images[0];
                frames[1] = images[1];
                frames[2] = images[2];
                frames[3] = images[3];
                frames[4] = images[4];
                frames[5] = images[5];
            }
            case CHARGING -> {
                frames = new Image[3];
                if(currentAttack == leftAttack){
                    frames[0] = images[6];
                    frames[1] = images[7];
                    frames[2] = images[8];
                }
                else if(currentAttack == rightAttack){
                    frames[0] = images[9];
                    frames[1] = images[10];
                    frames[2] = images[11];
                }
                else if(currentAttack == centralAttack){
                    frames[0] = images[9];
                    frames[1] = images[20];
                    frames[2] = images[20];
                }
            }
            case ATTACKING -> {
                frames = new Image[4];
                if(currentAttack == leftAttack){
                    frames[0] = images[12];
                    frames[1] = images[13];
                    frames[2] = images[14];
                    frames[3] = images[15];
                }
                else if(currentAttack == rightAttack){
                    frames[0] = images[16];
                    frames[1] = images[17];
                    frames[2] = images[18];
                    frames[3] = images[19];
                }
                else if(currentAttack == centralAttack){
                    frames[0] = images[12];
                    frames[1] = images[13];
                    frames[2] = images[16];
                    frames[3] = images[17];
                }
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

        drawHITBOX(gc);
        drawBossUI(gc);
    }

    private void drawBossUI(GraphicsContext gc) {
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(90, 50, health*1.5, 20);

        gc.setFill(Color.WHITE);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 20));
        gc.fillText("Gilgamesh, il difensore ", 225, 100);

    }

    private void drawHITBOX(GraphicsContext gc) {
        if(currentState == BossState.CHARGING){
            gc.setFill(Color.RED);
            gc.setGlobalAlpha(0.2);
            if(currentAttack == rightAttack){
                gc.fillRect(boxX+boxWidth/2, boxY+72, boxWidth/2, boxHeight+300);
            }
            else if (currentAttack == leftAttack) {
                gc.fillRect(boxX, boxY+72, boxWidth/2, boxHeight+300);
            }
            else if(currentAttack == centralAttack){
                gc.fillRect(boxX, boxY+72, boxWidth, boxHeight+50);
            }
            gc.setGlobalAlpha(1);
        }
    }


}

