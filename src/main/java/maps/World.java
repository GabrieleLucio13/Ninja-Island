package maps;

import entities.*;
import entities.Throwable;
import java.util.ArrayList;
import java.util.List;

public class World {
    private GameMap[][] maps;
    private int currentMapX;
    private int currentMapY;


    public World(int x, int y) {

        maps = new GameMap[x][y];

        loadMap();

        // Inizia nella mappa (0, 0) -> Boss battle (1, 3)
        currentMapX = 0;
        currentMapY = 0;
    }
    public void loadMap() {
        maps[0][0] = new GameMap("/map/map1.txt", createItems(0,0), createEnemies(0,0));
        maps[1][0] = new GameMap("/map/map2.txt", createItems(1,0), createEnemies(1,0));
        maps[2][0] = new GameMap("/map/map3.txt", createItems(2,0), createEnemies(2,0));
        maps[3][0] = new GameMap("/map/map4.txt", createItems(3,0), createEnemies(3,0));
        maps[4][0] = new GameMap("/map/map5.txt", createItems(4,0), createEnemies(4,0));
        maps[0][1] = new GameMap("/map/map16.txt", createItems(0,1), createEnemies(0,1));
        maps[1][1] = new GameMap("/map/map15.txt", createItems(1,1), createEnemies(1,1));
        maps[2][1] = new GameMap("/map/map14.txt", createItems(2,1), createEnemies(2,1));
        maps[3][1] = new GameMap("/map/map13.txt", createItems(3,1), createEnemies(3,1));
        maps[4][1] = new GameMap("/map/map6.txt", createItems(4,1), createEnemies(4,1));
        maps[0][2] = new GameMap("/map/map17.txt", createItems(0,2), createEnemies(0,2));
        maps[1][2] = new GameMap("/map/map25.txt", createItems(1,2), createEnemies(1,2));
        maps[2][2] = new GameMap("/map/map23.txt", createItems(2,2), createEnemies(2,2));
        maps[3][2] = new GameMap("/map/map12.txt", createItems(3,2), createEnemies(3,2));
        maps[4][2] = new GameMap("/map/map7.txt", createItems(4,2), createEnemies(4,2));
        maps[0][3] = new GameMap("/map/map18.txt", createItems(0,3), createEnemies(0,3));
        maps[1][3] = new GameMap("/map/map24.txt", createItems(1,3), createEnemies(1,3));
        maps[2][3] = new GameMap("/map/map22.txt", createItems(2,3), createEnemies(2,3));
        maps[3][3] = new GameMap("/map/map11.txt", createItems(3,3), createEnemies(3,3));
        maps[4][3] = new GameMap("/map/map8.txt", createItems(4,3), createEnemies(4,3));
        maps[0][4] = new GameMap("/map/map19.txt", createItems(0,4), createEnemies(0,4));
        maps[1][4] = new GameMap("/map/map20.txt", createItems(1,4), createEnemies(1,4));
        maps[2][4] = new GameMap("/map/map21.txt", createItems(2,4), createEnemies(2,4));
        maps[3][4] = new GameMap("/map/map10.txt", createItems(3,4), createEnemies(3,4));
        maps[4][4] = new GameMap("/map/map9.txt", createItems(4,4), createEnemies(4,4));


    }
    private List<Item> createItems(int x, int y) {
        List<Item> items = new ArrayList<>();
        if(x == 0 && y == 0){
            items.add(new Heart(250, 500));
            items.add(new Coin(110, 570));
            items.add(new Coin(130, 570));
            items.add(new Coin(150, 570));
            items.add(new Coin(170, 570));
        }
        if(x == 1 && y == 0){
            items.add(new Heart(156, 190));
            items.add(new Coin(180, 520));
            items.add(new Coin(200, 520));
            items.add(new Coin(160, 520));
            items.add(new Coin(320, 546));
            items.add(new Coin(320, 526));
            items.add(new Coin(320, 566));
            items.add(new Throwable(462, 528, 3));

        }
        if(x == 2 && y == 0){
            items.add(new Heart(500, 500));
            items.add(new Throwable(214, 474, 3));
            items.add(new Coin(520, 560));
            items.add(new Coin(500, 560));
            items.add(new Coin(480, 560));
        }
        if(x == 3 && y == 0){
            items.add(new Heart(500, 500));
            items.add(new Throwable(344, 510, 3));
            items.add(new Throwable(3444, 550, 3));
            items.add(new Coin(514, 560));
            items.add(new Coin(326, 274));
            items.add(new Coin(326, 350));
            items.add(new Coin(200, 358));
            items.add(new Coin(200, 280));
            items.add(new Coin(500, 144));

        }
        if(x == 4 && y == 0){
            items.add(new Coin(280, 470));
            items.add(new Coin(280, 490));
            items.add(new Coin(280, 510));
            items.add(new Heart(476, 152));
            items.add(new Heart(240, 230));
            items.add(new Throwable(344, 550, 3));
        }
        if(x == 0 && y == 1){
            items.add(new Heart(500, 500));
            items.add(new Coin(120, 284));
            items.add(new Coin(120, 304));
            items.add(new Coin(120, 324));
            items.add(new Key(280, 340));
            items.add(new Door(462, 600));
            items.add(new Door(462, 560));
            items.add(new Door(462, 520));
        }
        if(x == 1 && y == 1){
            items.add(new Heart(80, 500));
            items.add(new Coin(150, 520));
            items.add(new Coin(130,520));
            items.add(new Coin(110, 520));
            items.add(new Coin(306, 230));
            items.add(new Coin(326, 230));
            items.add(new Throwable(498, 510, 3));
            items.add(new Key(300, 520));
        }
        if(x == 2 && y == 1){
            items.add(new Heart(500, 500));
            items.add(new Throwable(286, 230, 3));
            items.add(new Coin(222, 340));
            items.add(new Coin(346,340));
            items.add(new Coin(242, 340));
        }
        if(x == 3 && y == 1){
            items.add(new Heart(500, 500));
            items.add(new Throwable(362, 510, 3));
            items.add(new Coin(560, 236));
            items.add(new Coin(560,256));
            items.add(new Coin(560, 276));
        }
        if(x == 4 && y == 1){
            items.add(new Coin(280, 512));
            items.add(new Coin(200, 210));
            items.add(new Coin(200, 230));
            items.add(new Coin(200, 250));
            items.add(new Heart(200, 190));
            items.add(new Heart(200, 280));
            items.add(new Throwable(362, 510, 3));
        }
        if(x == 0 && y == 2){
            items.add(new Heart(520, 560));
            items.add(new Key(258, 408));
            items.add(new Coin(370, 560));
            items.add(new Coin(390,560));
            items.add(new Coin(410, 560));
            items.add(new Coin(360, 80));
            items.add(new Coin(340, 80));
            items.add(new Coin(320, 80));
            items.add(new Throwable(72, 346, 3));
        }
        if(x == 1 && y == 2){
            items.add(new TreasureChest(280, 100));
        }
        if(x == 2 && y == 2){
            items.add(new Heart(120, 238));
            items.add(new Heart(440, 238));
            items.add(new Key(304, 132));
            items.add(new Throwable(278, 392, 3));
            items.add(new Coin(80, 110));
            items.add(new Coin(100, 110));
            items.add(new Coin(120, 110));
            items.add(new Coin(442, 80));
            items.add(new Coin(482, 80));
            items.add(new Coin(520, 80));
            items.add(new Coin(520, 120));
            items.add(new Coin(520, 160));
            items.add(new Coin(442, 120));
            items.add(new Coin(442, 160));
            items.add(new Coin(482, 160));
            items.add(new Throwable(482, 118, 3));
        }
        if(x == 3 && y == 2){
            items.add(new Heart(200, 360));
            items.add(new Heart(264, 520));
            items.add(new Coin(166, 518));
            items.add(new Coin(186,518));
            items.add(new Coin(206, 518));
            items.add(new Coin(560, 32));
            items.add(new Coin(540, 32));
            items.add(new Throwable(180, 156, 3));
        }
        if(x == 4 && y == 2){
            items.add(new Heart(400, 350));
            items.add(new Heart(500, 560));
            items.add(new Coin(160, 190));
            items.add(new Coin(366, 190));
            items.add(new Coin(480, 564));
            items.add(new Coin(480, 554));
            items.add(new Key(258, 382));
            items.add(new Throwable(300, 100, 3));

        }
        if(x == 0 && y == 3){
            items.add(new Heart(80, 230));
            items.add(new Coin(100, 230));
            items.add(new Coin(120, 230));
            items.add(new Coin(160, 400));
            items.add(new Coin(360, 400));
            items.add(new Throwable(266, 392, 3));
        }
        if(x == 1 && y == 3){
            items.add(new Heart(160, 290));
            items.add(new Heart(440, 290));
            items.add(new Heart(160, 370));
            items.add(new Heart(440, 370));
            items.add(new Heart(160, 560));
            items.add(new Heart(440, 560));
            items.add(new Throwable(160, 450, 3));
            items.add(new Throwable(440, 450, 3));
            items.add(new Door(300, 20));
        }
        if(x == 2 && y == 3){
            items.add(new Heart(240, 34));
            items.add(new Coin(220, 34));
            items.add(new Coin(200, 34));
            items.add(new Coin(180, 34));
            items.add(new Coin(520, 380));
            items.add(new Coin(520, 400));
            items.add(new Coin(520, 420));
        }
        if(x == 3 && y == 3){
            items.add(new Heart(76, 200));
            items.add(new Coin(560, 280));
            items.add(new Coin(560, 260));
            items.add(new Coin(560, 240));
            items.add(new Coin(120, 30));
            items.add(new Coin(56, 200));
            items.add(new Coin(96, 200));
        }
        if(x == 4 && y == 3){
            items.add(new Heart(500, 500));
            items.add(new Coin(280, 310));
            items.add(new Coin(200, 394));
            items.add(new Coin(40, 480));
            items.add(new Coin(40,460));
            items.add(new Coin(40, 440));
            items.add(new Coin(480, 560));
            items.add(new Coin(460, 560));
            items.add(new Door(320,600));
        }
        if(x == 0 && y == 4){
            items.add(new Heart(360, 30));
            items.add(new Throwable(562, 520, 3));
            items.add(new Coin(516, 472));
            items.add(new Coin(536, 472));
            items.add(new Coin(556, 472));
        }
        if(x == 1 && y == 4){
            items.add(new Heart(326, 242));
            items.add(new Coin(300, 500));
            items.add(new Coin(320, 500));
            items.add(new Coin(280, 500));
            items.add(new Coin(306, 242));
            items.add(new Coin(286, 242));
            items.add(new Door(300,10));
            items.add(new Door(300,50));
            items.add(new Throwable(500, 500, 3));

        }
        if(x == 2 && y == 4){
            items.add(new Heart(500, 500));
            items.add(new Coin(200, 188));
            items.add(new Coin(200, 240));
            items.add(new Throwable(224, 406, 3));
            items.add(new Coin(460, 500));
            items.add(new Coin(480, 500));
        }
        if(x == 3 && y == 4){
            items.add(new Heart(80, 480));
            items.add(new Heart(308, 400));
            items.add(new Coin(268, 400));
            items.add(new Coin(308, 400));
            items.add(new Coin(36, 310));
            items.add(new Coin(36, 330));
            items.add(new Throwable(480, 416, 3));
            items.add(new Throwable(294, 120, 3));
        }
        if(x == 4 && y == 4){
            items.add(new Heart(500, 500));
            items.add(new Heart(40, 474));
            items.add(new Coin(80, 150));
            items.add(new Coin(80, 170));
            items.add(new Coin(80, 190));
            items.add(new Throwable(440, 432, 3));
            items.add(new Key(560, 486));
        }
        return items;
    }
    private List<Entity> createEnemies(int x, int y) {
        List<Entity> enemies = new ArrayList<>();
        if(x == 0 && y == 0){
            enemies.add(new Slime(250, 500, 32, 32, 1, 30, this));
            enemies.add(new Slime(200, 200, 32, 32, 1, 30, this));
            enemies.add(new Slime(300, 300, 32, 32, 1, 30, this));
            enemies.add(new Slime(500, 250, 32, 32, 1, 30, this));
        }
        if(x == 1 && y == 0){
            enemies.add(new Slime(156, 190,32, 32, 1, 30, this));
            enemies.add(new Slime(552, 126,32, 32, 1, 30, this));
            enemies.add(new Slime(482, 498,32, 32, 1, 30, this));
            enemies.add(new Bat(266, 422, 32, 32, 2, 15, this, 2));
            enemies.add(new Bat(462, 528, 32, 32, 2, 15, this, 3));
        }
        if(x == 2 && y == 0){
            enemies.add(new Slime(86, 162,32, 32, 1, 30, this));
            enemies.add(new Slime(86, 472,32, 32, 1, 30, this));
            enemies.add(new Slime(500, 500,32, 32, 1, 30, this));
            enemies.add(new Bat(422, 172, 32, 32, 2, 15, this, 2));
            enemies.add(new Bat(254, 412, 32, 32, 2, 15, this, 2));
        }
        if(x == 3 && y == 0){
            enemies.add(new Slime(500, 500,32, 32, 1, 30, this));
            enemies.add(new Slime(82, 150,32, 32, 1, 30, this));
            enemies.add(new Bat(82, 416, 32, 32, 2, 15, this, 3));
            enemies.add(new Bat(370, 172, 32, 32, 2, 15, this, 2));
            enemies.add(new Bat(392, 520, 32, 32, 2, 15, this, 2));
        }
        if(x == 4 && y == 0){
            enemies.add(new Slime(480, 460,32, 32, 1, 30, this));
            enemies.add(new Slime(262, 334,32, 32, 1, 30, this));
            enemies.add(new Slime(98, 518,32, 32, 1, 30, this));
        }
        if(x == 0 && y == 1){
            enemies.add(new Slime(500, 460,32, 32, 1, 30, this));
            enemies.add(new Panda(280, 340,40, 40, 1, 70, this));
            enemies.add(new Bat(276, 230, 32, 32, 2, 15, this, 1));
        }
        if(x == 1 && y == 1){
            enemies.add(new Slime(500, 500,32, 32, 1, 30, this));
            enemies.add(new Slime(126, 292,32, 32, 1, 30, this));
            enemies.add(new Bat(428, 242, 32, 32, 2, 15, this, 2));
            enemies.add(new AoEEnemy(302, 470, 40, 40, 0, 20, this));
            enemies.add(new AoEEnemy(302, 370, 40, 40, 0, 20, this));
        }
        if(x == 2 && y == 1){
            enemies.add(new Slime(500, 500,32, 32, 1, 30, this));
            enemies.add(new AoEEnemy(280, 476, 40, 40, 0, 20, this));
            enemies.add(new AoEEnemy(280, 340, 40, 40, 0, 20, this));
        }
        if(x == 3 && y == 1){
            enemies.add(new Slime(500, 500,32, 32, 1, 30, this));
            enemies.add(new Slime(162, 280,32, 32, 1, 30, this));
            enemies.add(new AoEEnemy(280, 340, 40, 40, 0, 20, this));
        }
        if(x == 4 && y == 1){
            enemies.add(new Slime(460, 460,32, 32, 1, 30, this));
            enemies.add(new Slime(198, 98,32, 32, 1, 30, this));
            enemies.add(new Slime(88, 538,32, 32, 1, 30, this));
            enemies.add(new Mole(285, 440, 32, 32, 0, 50, this, 0));
            enemies.add(new Mole(355, 440, 32, 32, 0, 50, this, 0));
            enemies.add(new Mole(64, 190, 32, 32, 2, 15, this, 1));
        }

        if(x == 0 && y == 2){
            enemies.add(new Slime(280, 188,32, 32, 1, 30, this));
            enemies.add(new Mole(72, 424, 32, 32, 0, 50, this, 0));
            enemies.add(new Mole(400, 430, 32, 32, 0, 50, this, 2));
            enemies.add(new Mole(6438, 472, 32, 32, 2, 15, this, 1));
            enemies.add(new Bat(120, 552, 32, 32, 2, 20, this, 3));
        }
        if(x == 2 && y == 2){
            enemies.add(new Slime(520, 414,32, 32, 1, 30, this));
            enemies.add(new Bat(160, 432, 32, 32, 2, 20, this, 3));
            enemies.add(new Bat(432, 554, 32, 32, 2, 20, this, 2));
            enemies.add(new Panda(278, 392,40, 40, 1, 70, this));
        }
        if(x == 3 && y == 2){
            enemies.add(new Slime(308, 520,32, 32, 1, 30, this));
            enemies.add(new Bat(160, 436, 32, 32, 2, 20, this, 2));
            enemies.add(new Mole(200, 356, 32, 32, 0, 50, this, 0));
            enemies.add(new Mole(400, 200, 32, 32, 0, 50, this, 2));
            enemies.add(new Mole(560,80, 32, 32, 0, 50, this, 2));
            enemies.add(new Bat(324, 512,32, 32, 1, 30, this, 0));
            enemies.add(new Mole(340, 520,32, 32, 0, 50, this, 2));

        }
        if(x == 4 && y == 2){
            enemies.add(new Bat(480, 422,32, 32, 1, 30, this, 2));
            enemies.add(new Slime(52, 194,32, 32, 1, 30, this));
            enemies.add(new Mole(244, 200, 32, 32, 0, 50, this, 1));
            enemies.add(new Mole(284, 200, 32, 32, 0, 50, this, 1));
            enemies.add(new Bat(66, 380, 32, 32, 2, 25, this, 3));
            enemies.add(new Panda(258, 410,40, 40, 1, 70, this));


        }
        if(x == 0 && y == 3){
            enemies.add(new Panda(264, 316,40, 40, 1, 70, this));
            enemies.add(new Mole(124, 310, 32, 32, 0, 50, this, 0));
            enemies.add(new Bat(600, 160, 32, 32, 2, 25, this, 2));
            enemies.add(new Bat(400, 260, 32, 32, 2, 25, this, 3));
            enemies.add(new Bat(400,360, 32, 32, 2, 25, this, 3));
            enemies.add(new Bat(600, 460, 32, 32, 2, 25, this, 2));
        }
        if(x == 1 && y == 3){
            enemies.add(new Boss(170, 0,300, 300, 0, 300, this));

        }
        if(x == 2 && y == 3){
            enemies.add(new Slime(110, 108,32, 32, 1, 30, this));
            enemies.add(new Slime(222, 528,32, 32, 1, 30, this));
            enemies.add(new Slime(296, 270,32, 32, 1, 30, this));
            enemies.add(new Bat(220,216, 32, 32, 2, 25, this, 2));
            enemies.add(new Bat(400, 416, 32, 32, 2, 25, this, 0));
            enemies.add(new AoEEnemy(386, 66, 40, 40, 0, 20, this));
        }
        if(x == 3 && y == 3){
            enemies.add(new Slime(162, 280,32, 32, 1, 30, this));
            enemies.add(new Slime(498, 280,32, 32, 1, 30, this));
            enemies.add(new Bat(100, 200, 32, 32, 2, 25, this, 2));
            enemies.add(new Bat(278, 360, 32, 32, 2, 25, this, 2));
            enemies.add(new Bat(560, 196, 32, 32, 2, 25, this, 1));
            enemies.add(new Mole(512, 470, 32, 32, 0, 20, this, 2));
            enemies.add(new Mole(206, 70, 32, 32, 0, 20, this, 3));

        }
        if(x == 4 && y == 3){
            enemies.add(new Slime(460, 460,32, 32, 1, 30, this));
            enemies.add(new Slime(118, 456,32, 32, 1, 30, this));
            enemies.add(new Bat(404, 400, 32, 32, 2, 25, this, 2));
            enemies.add(new Bat(118, 312, 32, 32, 2, 25, this, 3));
            enemies.add(new Bat(356, 152, 32, 32, 2, 25, this, 2));
        }
        if(x == 0 && y == 4){
            enemies.add(new Slime(486, 112,32, 32, 1, 30, this));
            enemies.add(new Slime(100, 260,32, 32, 1, 30, this));
            enemies.add(new Bat(128, 470, 32, 32, 2, 15, this, 2));
            enemies.add(new Mole(478, 516, 32, 32, 0, 50, this, 2));
            enemies.add(new Mole(82, 520, 32, 32, 0, 50, this, 3));
            enemies.add(new Mole(260, 254, 32, 32, 0, 50, this, 1));
        }
        if(x == 1 && y == 4){
            enemies.add(new Slime(500, 500,32, 32, 1, 30, this));
            enemies.add(new AoEEnemy(300, 300, 40, 40, 0, 20, this));
            enemies.add(new Bat(100, 300, 32, 32, 2, 15, this, 3));
            enemies.add(new Bat(300, 400, 32, 32, 2, 15, this, 2));
            enemies.add(new Panda(100, 500,40, 40, 1, 70, this));
        }
        if(x == 2 && y == 4){
            enemies.add(new Slime(500, 500,32, 32, 1, 30, this));
            enemies.add(new Mole(150, 300, 32, 32, 0, 50, this, 0));
            enemies.add(new Mole(320, 36, 32, 32, 0, 50, this, 1));
            enemies.add(new Mole(200, 78, 32, 32, 0, 50, this, 2));
            enemies.add(new Mole(404, 482, 32, 32, 0, 50, this, 3));
        }
        if(x == 3 && y == 4){
            enemies.add(new Slime(500, 460,32, 32, 1, 30, this));
            enemies.add(new Mole(160, 396, 32, 32, 0, 50, this, 3));
            enemies.add(new Mole(40, 510, 32, 32, 0, 50, this, 0));
            enemies.add(new Bat(326, 560, 32, 32, 2, 15, this, 1));
            enemies.add(new Slime(326, 560,32, 32, 1, 30, this));
            enemies.add(new Slime(162, 48,32, 32, 1, 30, this));
            enemies.add(new Bat(348, 274, 32, 32, 2, 15, this, 2));
        }
        if(x == 4 && y == 4){
            enemies.add(new Slime(274, 246,32, 32, 1, 30, this));
            enemies.add(new Slime(132, 374,32, 32, 1, 30, this));
            enemies.add(new Mole(420, 418, 32, 32, 0, 50, this, 0));
            enemies.add(new Bat(192, 108, 32, 32, 2, 15, this, 3));
            enemies.add(new Panda(530, 524,40, 40, 1, 70, this));
        }
        return enemies;
    }

    public void changeMap(int dx, int dy) {
        int newMapX = currentMapX + dx;
        int newMapY = currentMapY + dy;

        if (newMapX >= 0 && newMapX < maps.length && newMapY >= 0 && newMapY < maps[0].length) {
            currentMapX = newMapX;
            currentMapY = newMapY;
        }
    }
    public GameMap getBossMap(){
        return maps[1][3];
    }
    public GameMap getCurrentMap() {
        return maps[currentMapX][currentMapY];
    }

    public int getCurrentMapX() {
        return currentMapX;
    }

    public int getCurrentMapY(){
        return currentMapY;
    }
}
