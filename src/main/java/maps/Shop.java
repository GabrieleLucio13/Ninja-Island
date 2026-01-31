package maps;

import entities.Player;
import entities.Sword;
import entities.Throwable;

public class Shop {
    private int swordUpgradeCost = 20;
    private int kunaiUpgradeCost = 15;
    private int healthUpgradeCost = 20;
    private int swordUpgradeCount;
    private int kunaiUpgradeCount;
    private int healthUpgradeCount = 0;
    private Player player;
    private Sword sword;
    private Throwable kunai;

    public Shop(Player player) {
        this.player = player;
        this.sword = player.getSword();
        this.kunai = player.getKunai();
    }

    public boolean buySwordUpgrade() {
        if (player.getCoinPlayer() >= swordUpgradeCost && swordUpgradeCount < 3) {
            player.decreaseCoins(swordUpgradeCost);
            sword.upgrade();
            swordUpgradeCount++;
            return true;
        }
        return false;
    }

    public boolean buyKunaiUpgrade() {
        if (player.getCoinPlayer() >= kunaiUpgradeCost && kunaiUpgradeCount < 3) {
            player.decreaseCoins(kunaiUpgradeCost);
            kunai.upgrade();
            player.increaseAmmoCapacity();
            kunaiUpgradeCount++;
            return true;
        }
        return false;
    }

    public boolean buyHealthUpgrade() {
        if (player.getCoinPlayer() >= healthUpgradeCost && healthUpgradeCount < 3) {
            player.decreaseCoins(healthUpgradeCost);
            player.increaseHealth();
            healthUpgradeCount++;
            return true;
        }
        return false;
    }

    public int getSwordUpgradeCost() {
        return swordUpgradeCost;
    }
    public int getSwordUpgradeCount() {
        return swordUpgradeCount;
    }
    public int getKunaiUpgradeCost() {
        return kunaiUpgradeCost;
    }
    public int getKunaiUpgradeCount() {
        return kunaiUpgradeCount;
    }
    public int getHealthUpgradeCost() {
        return healthUpgradeCost;
    }
    public int getHealthUpgradeCount() {
        return healthUpgradeCount;
    }
}
