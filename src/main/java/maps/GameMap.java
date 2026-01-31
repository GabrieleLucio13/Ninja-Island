package maps;

import entities.*;
import entities.Throwable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GameMap {
    private int[][] mapData;
    private Tile[] tiles;
    private List<Item> items;
    private List<Entity> enemies;
    private List<Rectangle2D> wallTiles;

    public GameMap(String mapResourcePath, List<Item> items, List<Entity> enemies) {

        this.items = items;
        this.enemies = enemies;

        mapData = loadMap(mapResourcePath, 16, 16);

        loadTiles();
        createHitbox();
    }

    private static int[][] loadMap(String resourcePath, int rows, int cols) {

        int[][] map = new int[rows][cols];

        try (
                InputStream is = GameMap.class.getResourceAsStream(resourcePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))
        ) {
            if (is == null) {
                throw new RuntimeException("Mappa NON trovata: " + resourcePath);
            }

            String line;
            int row = 0;

            while ((line = br.readLine()) != null && row < rows) {
                String[] tokens = line.split(" ");

                for (int col = 0; col < tokens.length && col < cols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
                row++;
            }

        } catch (Exception e) {
            System.err.println("Errore nel caricamento della mappa: " + resourcePath);
            e.printStackTrace();
        }

        return map;
    }
    private void loadTiles() {
        // Carica le immagini dei tile
        tiles = new Tile[29];
        tiles[0] = new Tile(true,"/Tiles/sand.png");
        tiles[1] = new Tile(false,"/Tiles/rockSand.png");
        tiles[2] = new Tile(false,"/Tiles/treeSand.png");
        tiles[3] = new Tile(false,"/Tiles/waterTiles.png");
        tiles[4] = new Tile(false,"/Tiles/sandWater3.png");
        tiles[5] = new Tile(false,"/Tiles/sandWater4.png");
        tiles[6] = new Tile(false,"/Tiles/sandWater5.png");
        tiles[7] = new Tile(false,"/Tiles/treeGrass.png");
        tiles[8] = new Tile(true,"/Tiles/grassTiles.png");
        tiles[9] = new Tile(false,"/Tiles/treeRoot.png");
        tiles[10] = new Tile(false,"/Tiles/sandWater1.png");
        tiles[11] = new Tile(false,"/Tiles/sandWater2.png");
        tiles[12] = new Tile(true,"/Tiles/grassMountain.png");
        tiles[13] = new Tile(false,"/Tiles/rockMountain.png");
        tiles[14] = new Tile(false,"/Tiles/treeMountain.png");
        tiles[15] = new Tile(false,"/Tiles/edge1.png");
        tiles[16] = new Tile(false,"/Tiles/edge2.png");
        tiles[17] = new Tile(false,"/Tiles/edge3.png");
        tiles[18] = new Tile(false,"/Tiles/edgecorner1.png");
        tiles[19] = new Tile(false,"/Tiles/edgecorner2.png");
        tiles[20] = new Tile(false,"/Tiles/sky.png");
        tiles[21] = new Tile(false,"/Tiles/sun.png");
        tiles[22] = new Tile(false,"/Tiles/brokenCol.png");
        tiles[23] = new Tile(false,"/Tiles/statue.png");
        tiles[24] = new Tile(true,"/Tiles/floor.png");
        tiles[25] = new Tile(false,"/Tiles/statue2.png");
        tiles[26] = new Tile(false,"/Tiles/column.png");
        tiles[27] = new Tile(true,"/Tiles/floor2.png");
        tiles[28] = new Tile(true,"/Tiles/ladder.png");
    }
    public void createHitbox(){
        wallTiles = new ArrayList<>();

        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                int tileType = mapData[i][j];
                if (!tiles[tileType].isWalkable()) {
                    int tileX = j * 40;
                    int tileY = i * 40;

                    Rectangle2D tileHitbox = new Rectangle2D(tileX, tileY, 40, 40);
                    wallTiles.add(tileHitbox);
                }
            }
        }
    }
    public boolean isWalkable(Rectangle2D newHitbox, Entity entity, Player player) {

        for (Rectangle2D tileHitbox : wallTiles) {
            if (tileHitbox.intersects(newHitbox)) {
                return false;
            }
        }

        for(Item item : items){
            if(item instanceof Door && item.getHitbox().intersects(newHitbox)){
                return false;
            }
        }

        if(player == null){
            for(Entity enemy : enemies){
                if(newHitbox.intersects(enemy.getHitbox())){
                    entity.takeDamage(enemy.getPower());
                    return false;
                    }
                }
            } else{
           if(newHitbox.intersects(player.getHitbox())){
              player.takeDamage(entity.getPower());
              return false;
           }
            return newHitbox.getMinX() >= 0 &&
                    newHitbox.getMaxX() <= 640 &&
                    newHitbox.getMinY() >= 0 &&
                    newHitbox.getMaxY() <= 640;
        }
        return true;
    }
    public void checkItem(Player player) {
        for(Item item : items)
            {
                Rectangle2D newHitbox;
                if(item instanceof Door){
                    newHitbox = ((Door) item).getKeyHole();
                }
                else{
                    newHitbox = item.getHitbox();
                }
                if(newHitbox.intersects(player.getHitbox())){
                    handleItem(item, player);
                    break;
                }
            }
    }
    public boolean checkEnemy(Rectangle2D swordHitbox, int power) {
        for(Entity enemy: enemies){
            if(swordHitbox.intersects(enemy.getHitbox()) && !enemy.isDamaged()){
                enemy.takeDamage(power);
                return true;
            }
        }
        return false;
    }
    public void handleItem(Item item, Player player){
        switch (item.getType()) {
            case "Heart" -> {
                if (player.getHealth() < player.getMaxHealth()) {
                    player.restoreHealth();
                    items.remove(item);
                }
            }
            case "Key" -> {
                player.addKey();
                items.remove(item);
            }
            case "Coin" -> {
                player.addCoin();
                items.remove(item);
            }
            case "Throwable" -> {
                if (player.getAmmo() < 10) {
                    player.addThrowable();
                    items.remove(item);
                }
            }
            case "Door" ->{
                if(player.getKeyPlayer() > 0){
                    player.decreaseKeyCount();
                    items.remove(item);
                }
            }
        }
    }

    public void update(Player player) {
        for (Item item : items) {
            if (item instanceof Coin) {
                ((Coin) item).update();
            }
        }
        for(Entity enemy : enemies){
            enemy.update(player);
            if(enemy.getHealth() <= 0){
                enemies.remove(enemy);
                enemyDrop(enemy);
                break;
            }
        }
    }
    public void enemyDrop(Entity enemy) {
        if(enemy instanceof Boss ){
            items.add(new Key(300, 300));
        }
        else{
            Random random = new Random();
            int i = random.nextInt(6);
            switch(i){
                case 1 -> items.add(new Heart(enemy.getX(), enemy.getY()));
                case 2 -> items.add(new Coin(enemy.getX(), enemy.getY()));
                case 3 -> items.add(new Throwable(enemy.getX(), enemy.getY(), 3));
            }
        }
    }
    public void renderTile(GraphicsContext gc, int tileType, int x, int y) {
        gc.drawImage(tiles[tileType].getImage(), x, y, tiles[tileType].width, tiles[tileType].height);
    }

    public void draw(GraphicsContext gc) {
        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 16; x++) {
                int tileType = mapData[y][x];
                renderTile(gc, tileType, x  * 40, y * 40);
            }
        }
        for(int i = 0; i<items.size(); ++i)
        {
            items.get(i).draw(gc);
        }
        for(int i = 0; i<enemies.size(); ++i){
            enemies.get(i).draw(gc);
        }
    }
}
