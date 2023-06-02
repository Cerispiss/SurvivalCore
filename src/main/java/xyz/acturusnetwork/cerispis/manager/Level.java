package xyz.acturusnetwork.cerispis.manager;

import org.bukkit.entity.Player;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.events.LevelUpEvent;

public class Level {
    SurvivalCore plugin = SurvivalCore.getInstance();
    private final Player player;

    public Level(Player player) {
        this.player = player;
    }

    public void setLevelData() {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Level", 1);
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Exp", 0);
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".MaxExp", 100);
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".LevelReward", false);
        plugin.getPlayerFile().saveConfig();
        plugin.getLogger().info("Creating data for Level with player name " + player.getName());
    }

    public void setNewLevel(int newLevel, boolean resetExp) {
        if (resetExp) {
            plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Exp", 0);
        }

        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Level", newLevel);
        plugin.getPlayerFile().saveConfig();
    }

    public void updateLevel(boolean resetExp) {
        if (resetExp) {
            plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Exp", 0);
        }

        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Level", getLevel() + 1);
        plugin.getPlayerFile().saveConfig();
    }

    public void addExp(int amountOfExp) {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Exp", amountOfExp);
        plugin.getPlayerFile().saveConfig();

        if (getExp() >= getMaxOfExp()) {
            updateLevel(false);
            Chat.levelUpMessage(player);
            plugin.getServer().getPluginManager().callEvent(new LevelUpEvent(player));
            plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".MaxExp", getMaxOfExp() + 400);
            plugin.getPlayerFile().saveConfig();
        }
    }

    public int getExp() {
        return plugin.getPlayerConfig().getInt(player.getUniqueId().toString() + ".Exp");
    }

    public int getLevel() {
        return plugin.getPlayerConfig().getInt(player.getUniqueId().toString() + ".Level");
    }

    public int getMaxOfExp() {
        return plugin.getPlayerConfig().getInt(player.getUniqueId().toString() + ".MaxExp");
    }
}
