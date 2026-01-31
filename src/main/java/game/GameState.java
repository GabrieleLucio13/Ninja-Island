package game;

import entities.Player;
import maps.World;


public class GameState {
    private Player player;
    private World world;

    public GameState(Player player, World world) {

        this.player = player;
        this.world = world;
    }
    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }
}
