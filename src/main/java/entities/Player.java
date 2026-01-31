package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import maps.World;
import sounds.AudioManager;
import sounds.SoundEffect;
import utility.ResourceLoader;

import javafx.geometry.Rectangle2D;

public class Player extends Entity {
    private PlayerState currentState;
    private Rectangle2D feetHitbox;
    private boolean upPressed, downPressed, rightPressed, leftPressed, kPressed, spacePressed = false;
    private int maxHealth, maxAmmo;
    private int keyPlayer, coinPlayer, ammo;
    private Throwable kunai;
    private final Sword sword;
    private double attackAnimationTime = 0;
    private final double maxAttackAnimationTime = 30;
    private SoundEffect pickUpCoin, pickUpHeart, pickUpKunai, useWeapon, hit, damaged, opendoor, gameOver, win;
    public enum PlayerState {
        IDLE,
        MOVING,
        THROWING,
        ATTACKING,
    }
    public Player(int startX, int startY, int width, int height, int speed, int health, World world, AudioManager audioManager) {
        super(startX, startY, width, height, speed, health, world);

        this.direction = 1;
        this.currentState = PlayerState.IDLE;
        this.ammo = 0;
        this.maxHealth = health;
        this.maxAmmo = 5;
        this.kunai = null;
        this.sword = new Sword(5);


        loadSound(audioManager);
        loadFrames();
        updateHitboxes();
    }
    public void loadSound(AudioManager audioManager){
        useWeapon = audioManager.getSoundEffect(0);
        pickUpCoin = audioManager.getSoundEffect(2);
        pickUpHeart = audioManager.getSoundEffect(1);
        pickUpKunai = audioManager.getSoundEffect(5);
        hit = audioManager.getSoundEffect(3);
        damaged = audioManager.getSoundEffect(4);
        opendoor = audioManager.getSoundEffect(6);
        gameOver = audioManager.getSoundEffect(7);
        win = audioManager.getSoundEffect(8);
    }
    public void loadFrames() {
        images = new Image[18];
        images[0] = ResourceLoader.loadImage("/Player/IdleDown.png");
        images[1] = ResourceLoader.loadImage("/Player/IdleUp.png");
        images[2] = ResourceLoader.loadImage("/Player/IdleLeft.png");
        images[3] = ResourceLoader.loadImage("/Player/IdleRight.png");
        images[4] = ResourceLoader.loadImage("/Player/walkDown1.png");
        images[5] = ResourceLoader.loadImage("/Player/walkDown2.png");
        images[6] = ResourceLoader.loadImage("/Player/walkUp1.png");
        images[7] = ResourceLoader.loadImage("/Player/walkUp2.png");
        images[8] = ResourceLoader.loadImage("/Player/walkLeft1.png");
        images[9] = ResourceLoader.loadImage("/Player/walkLeft2.png");
        images[10] = ResourceLoader.loadImage("/Player/walkRight1.png");
        images[11] = ResourceLoader.loadImage("/Player/walkRight2.png");
        images[12] = ResourceLoader.loadImage("/Player/attackDown.png");
        images[13] = ResourceLoader.loadImage("/Player/attackUp.png");
        images[14] = ResourceLoader.loadImage("/Player/attackLeft.png");
        images[15] = ResourceLoader.loadImage("/Player/attackRight.png");
        images[16] = ResourceLoader.loadImage("/Player/layDown.png");
        images[17] = ResourceLoader.loadImage("/Player/victory.png");
    }
    @Override
    public void update(Player player) {

        updateAction();
        updateWeapon();
        setFrames();
        animation();
    }
    public void checkPosition(){
        System.out.println(x);
        System.out.println(y);
    }
    private void updateHitboxes() {
        // hitbox corpo
        hitbox = new Rectangle2D(x, y, width, height);
        feetHitbox = new Rectangle2D(
                x + width / 4,        // centrata
                y + height - 8,       // in basso
                width / 2,            // metà larghezza
                8                     // altezza piedi
        );
    }
    public void updateWeapon(){
        if(kunai!=null) {
            kunai.update();
            if(world.getCurrentMap().checkEnemy(kunai.getHitbox(), kunai.getAttackPower())){
                hit.play();
                kunai = null;
            }
            else if(kunai.getX() > 640 || kunai.getY() >640 || kunai.getX() < 0 || kunai.getY() <0){
                kunai = null;
            }
        }
    }
    private void updateAction() {
        switch (currentState) {
            case IDLE -> {
                if (upPressed || downPressed || leftPressed || rightPressed) {
                    currentState = PlayerState.MOVING;

                } else if (kPressed && ammo > 0 && kunai == null) {
                    startThrowing();
                } else if (spacePressed){
                    startAttacking();
                }
            }
            case MOVING -> {
                if (upPressed) {
                    direction = 0;
                }
                else if (downPressed) {
                    direction = 1;
                }
                else if (leftPressed) {
                    direction = 2;
                } else if(rightPressed) {
                    direction = 3;
                }

                move();
                if ((!upPressed && !downPressed && !leftPressed && !rightPressed)) {
                    currentState = PlayerState.IDLE;
                } else if (kPressed && ammo > 0 && kunai == null) {
                    startThrowing();
                } else if(spacePressed){
                    startAttacking();
                }
            }
            case THROWING -> {
                attackAnimationTime --;
                if (attackAnimationTime <= 0) {
                    currentState = PlayerState.IDLE;
                    kPressed = false;
                }
            }
            case ATTACKING -> {
                attackAnimationTime --;
                if (attackAnimationTime <= 0) {
                    currentState = PlayerState.IDLE;
                    spacePressed = false;
                }
            }
        }
    }
    public void setFrames() {

        if (currentState == PlayerState.IDLE) {
            if (direction == 1) {
                frames = new Image[1];
                frames[0] = images[0];
            }
            if (direction == 0) {
                frames = new Image[1];
                frames[0] = images[1];
            }
            if (direction == 2) {
                frames = new Image[1];
                frames[0] = images[2];
            }
            if (direction == 3) {
                frames = new Image[1];
                frames[0] = images[3];
            }
        } else if(currentState == PlayerState.MOVING) {
            if (direction == 1) {
                frames = new Image[2];
                frames[0] = images[4];
                frames[1] = images[5];
            }
            if (direction == 0) {
                frames = new Image[2];
                frames[0] = images[6];
                frames[1] = images[7];
            }
            if (direction == 2) {
                frames = new Image[2];
                frames[0] = images[8];
                frames[1] = images[9];
            }
            if (direction == 3) {
                frames = new Image[2];
                frames[0] = images[10];
                frames[1] = images[11];
            }
        }
        else if(currentState == PlayerState.THROWING || currentState == PlayerState.ATTACKING) {
            if (direction == 1) {
                frames = new Image[1];
                frames[0] = images[12];
            }
            if (direction == 0) {
                frames = new Image[1];
                frames[0] = images[13];
            }
            if (direction == 2) {
                frames = new Image[1];
                frames[0] = images[14];
            }
            if (direction == 3) {
                frames = new Image[1];
                frames[0] = images[15];
            }
        }
    }

    //azioni
    public void move() {
        if (direction == 1 && checkLimits(x, y + speed)) {
            y += speed;
            world.getCurrentMap().checkItem(this);
            if (y > 600) {
                world.changeMap(0, 1);
                y = 10;
            }
        } else if (direction == 0 && checkLimits(x, y - speed)) {
            y -= speed;
            world.getCurrentMap().checkItem(this);
            if (y < 20) {
                world.changeMap(0, -1);
                y = 600;
            }
        } else if (direction == 2 && checkLimits(x - speed, y)) {
            x -= speed;
            world.getCurrentMap().checkItem(this);
            if (x < 20) {
                world.changeMap(-1, 0);
                x = 600;
            }
        } else if (direction == 3 && checkLimits(x + speed, y)) {
            x += speed;
            world.getCurrentMap().checkItem(this);
            if (x > 600) {
                world.changeMap(1, 0);
                x = 10;
            }
        }
        updateHitboxes();
    }
    public boolean checkLimits(int nextX, int nextY) {

        Rectangle2D nextFeetHitbox = new Rectangle2D(
                nextX + width / 4,
                nextY + height - 8,
                width / 2,
                8
        );

        return world.getCurrentMap().isWalkable(nextFeetHitbox, this, null);
    }

    private void startThrowing() {
        if (currentState != PlayerState.THROWING && ammo>0) {
            useWeapon.play();
            currentState = PlayerState.THROWING;
            attackAnimationTime = maxAttackAnimationTime;
            if(kunai == null) {
                kunai = new Throwable(x, y, direction);
                ammo--;

            }
        }
        if(ammo<0){
            ammo = 0;
        }
    }
    public void startAttacking() {
        if(currentState != PlayerState.ATTACKING){
            sword.update(x, y, direction);
            useWeapon.play();
            currentState = PlayerState.ATTACKING;
            attackAnimationTime = maxAttackAnimationTime;
            if(world.getCurrentMap().checkEnemy(sword.getHitbox(), sword.getAttackPower())){
                hit.play();
            };
        }
        sword.setHitbox(null);
    }
    @Override
    public void takeDamage(int amount){
        if(!isDamaged){
            damaged.play();
            isDamaged = true;
            health = health - amount;
            colorAdjust.setHue(-0.5);
            damageTime = System.nanoTime();
        }
    }
    public void restoreHealth() {
        pickUpHeart.play();
        health = health + 20;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }
    public void increaseHealth(){
        maxHealth = maxHealth + 25;
        health = maxHealth;
    }
    public void increaseAmmoCapacity(){
        maxAmmo = maxAmmo + 3;
    }
    public void addKey() {
        pickUpCoin.play();
        keyPlayer++;
    }
    public void decreaseKeyCount() {
        opendoor.play();
        keyPlayer--;
    }
    public void addCoin() {
        pickUpCoin.play();
        coinPlayer++;
    }
    public void decreaseCoins(int amount) {
        coinPlayer = coinPlayer - amount;
    }
    public void addThrowable() {
        pickUpKunai.play();
        ammo=ammo+2;
    }
    public void animation() {
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
    public void deathAnimation(GraphicsContext gc) {
        gameOver.play();
        gc.drawImage(images[16], x, y, width, height);
    }
    public void winAnimation(GraphicsContext gc){
        win.play();
        gc.drawImage(images[17], x, y, width, height);
    }
    @Override
    public void draw(GraphicsContext gc) {
        long damageDuration = 1_000_000_000;
        if (isDamaged && (System.nanoTime() - damageTime < damageDuration)) {
            gc.setEffect(colorAdjust);
        } else{
            isDamaged = false;
            gc.setEffect(null);
        }

        gc.drawImage(frames[currentFrame], x, y, width, height);

        if (currentState == PlayerState.ATTACKING) {
            switch (direction) {
                case 0 -> // Su
                        gc.drawImage(sword.getImageUp(), x + 2, y - 20, 16,24);
                case 1 -> // Giù
                        gc.drawImage(sword.getImageDown(), x, y + 32,16,24);
                case 2 -> // Sinistra
                        gc.drawImage(sword.getImageLeft(), x-20, y+20 ,24,16);
                case 3 -> // Destra
                        gc.drawImage(sword.getImageRight(), x + 38, y + 20,24,16);
            }
        }

        gc.setEffect(null);
        if(kunai != null){
            kunai.draw(gc);
        }
    }
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() { return maxHealth;}
    public int getAmmo() {
        return ammo;
    }
    public int getMaxAmmo(){return ammo;}
    public int getKeyPlayer() {
        return keyPlayer;
    }
    public int getCoinPlayer() {
        return coinPlayer;
    }
    public Sword getSword() { return sword; }
    public Throwable getKunai() { return kunai;}

    public boolean isInBossRoom(){
        return world.getCurrentMap() == world.getBossMap();
    }
    public void setUpPressed(boolean up) {
        this.upPressed = up;
    }
    public void setDownPressed(boolean down) {
        this.downPressed = down;
    }
    public void setLeftPressed(boolean left) {
        this.leftPressed = left;
    }
    public void setRightPressed(boolean right) {
        this.rightPressed = right;
    }
    public void setKPressed(boolean k) {
        this.kPressed =k;
    }
    public void setSpacePressed(boolean space) {
        this.spacePressed = space;
    }
}