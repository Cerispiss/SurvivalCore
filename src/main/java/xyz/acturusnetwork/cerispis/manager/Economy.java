package xyz.acturusnetwork.cerispis.manager;

import org.bukkit.entity.Player;
import xyz.acturusnetwork.cerispis.SurvivalCore;

public class Economy {
    SurvivalCore plugin = SurvivalCore.getInstance();
    private final Player player;

    public Economy(Player player) {
        this.player = player;
    }

    public void setCoinsData() {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Coins", 10);
        plugin.getPlayerFile().saveConfig();
        plugin.getLogger().info("Creating data for Economy with player name " + player.getName());
    }

    public void setCoins(int coins) {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Coins", coins);
        plugin.getPlayerFile().saveConfig();
    }

    public void addCoins(int coins) {
        setCoins(getCoins() + coins);
    }

    public void removeCoins(int coins) {
        if (getCoins() == 0) {
            plugin.getLogger().info("Cannot remove player coins.");
        }else {
            plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Coins", getCoins()-coins);
            plugin.getPlayerFile().saveConfig();
        }
    }

    public int getCoins() {
        return plugin.getPlayerConfig().getInt(player.getUniqueId().toString() + ".Coins");
    }

    public boolean hasCoins(){
        return getCoins() > 0;
    }
}
