package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Sword{
    private Image[] swordImages;
    private int attackPower;
    private Rectangle2D hitbox;
    public Sword(int attackPower) {

        this.attackPower = attackPower;

        swordImages = new Image[4];
        swordImages[0] = utility.ResourceLoader.loadImage("/objects/sword1.png");
        swordImages[1] = utility.ResourceLoader.loadImage("/objects/sword2.png");
        swordImages[2] = utility.ResourceLoader.loadImage("/objects/sword3.png");
        swordImages[3] = utility.ResourceLoader.loadImage("/objects/sword4.png");

    }
    public void update(int x, int y, int direction){

        switch (direction) {
            case 0 -> // Su
                    hitbox = new Rectangle2D(x + 2, y - 20, 16,24);
            case 1 -> // Giù
                    hitbox = new Rectangle2D( x, y + 32,16,24);
            case 2 -> // Sinistra
                    hitbox = new Rectangle2D( x-20, y+20 ,24,16);
            case 3 -> // Destra
                    hitbox = new Rectangle2D( x + 38, y + 20,24,16);
        }
    }
    public int getAttackPower() {
        return attackPower;
    }
    public Image getImageUp() {
        return swordImages[0];
    }

    public Image getImageDown() {
        return swordImages[1];
    }

    public Image getImageLeft() {
        return swordImages[2];
    }

    public Image getImageRight() {
        return swordImages[3];
    }

    public Rectangle2D getHitbox(){
        return hitbox;
    }
    public void setHitbox(Rectangle2D hitbox){
        this.hitbox = hitbox;
    }

    public void upgrade() {
        attackPower = attackPower+5;
    }
}
