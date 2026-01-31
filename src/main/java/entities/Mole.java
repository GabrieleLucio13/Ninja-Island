package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import maps.World;
import utility.ResourceLoader;


public class Mole extends Entity{
        private Pebble pebble;
        public Mole(int startX, int startY, int width, int height, int speed, int health, World world, int direction){
            super(startX, startY, width, height, speed, health, world);

            this.direction = direction;
            this.isMoving = false;
            this.currentFrame = 0;
            this.animationCounter = 0;
            this.isDamaged = false;
            this.power = 5;
            loadImages();
        }
        public void loadImages(){
            images = new Image[8];
            images[0] = ResourceLoader.loadImage("/enemies/00_Mole.png");
            images[1] = ResourceLoader.loadImage("/enemies/01_Mole.png");
            images[2] = ResourceLoader.loadImage("/enemies/02_Mole.png");
            images[3] = ResourceLoader.loadImage("/enemies/03_Mole.png");
            images[4] = ResourceLoader.loadImage("/enemies/04_Mole.png");
            images[5] = ResourceLoader.loadImage("/enemies/05_Mole.png");
            images[6] = ResourceLoader.loadImage("/enemies/06_Mole.png");
            images[7] = ResourceLoader.loadImage("/enemies/07_Mole.png");

        }

        public void update(Player player){
            updateAction();
            updatePebble(player);
            updateFrames();
            animation();
        }
        public void updateAction(){
            animationTimer++;
            if(animationTimer > 170){
                pebble = new Pebble(x, y);
                animationTimer = 0;
            }
        }
        public void updatePebble(Player player){
            if(pebble!=null) {
                pebble.update(direction);
                if(pebble.getHitbox().intersects(player.getHitbox())){
                    player.takeDamage(pebble.getAttackPower());
                    pebble = null;
                }
                else if(pebble.getX() > 640 || pebble.getY() >640 || pebble.getX() < 0 || pebble.getY() <0){
                    pebble = null;
                }
            }
        }
        private void updateFrames() {
            frames = new Image[2];
            switch (direction) {
                case 1 -> {
                    frames[0] = images[0];
                    frames[1] = images[4];}
                case 0 -> {
                    frames[0] = images[1];
                    frames[1] = images[5];
                }
                case 2 -> {
                    frames[0] = images[2];
                    frames[1] = images[6];
                }
                case 3 -> {
                    frames[0] = images[3];
                    frames[1] = images[7];
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

            if(pebble != null){
                pebble.draw(gc);
            }
        }
}

