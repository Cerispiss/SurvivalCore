package xyz.acturusnetwork.cerispis.manager;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.chat.Chat;

import java.util.List;

public class Objective {
    SurvivalCore plugin = SurvivalCore.getInstance();
    private final Player player;

    public Objective(Player player) {
        this.player = player;
    }

    public void setObjectiveData() {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".CurrentObjective", null);
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".DoneObnjective", 0);
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".PreviousObjective", null);
        plugin.getPlayerFile().saveConfig();
        plugin.getLogger().info("Creating data for objective with player name " + player.getName());
    }

    public void setNewObjective(String newObjective) {
        if (plugin.getPlayerConfig().getString(player.getUniqueId().toString() + ".CurrentObjective") != null) {
            plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".PreviousObjective", null);
        }

        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".PreviousObjective", getObjective());
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".CurrentObjective", newObjective);
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".DoneObjective", getAllDoneObjective() + 1);
        plugin.getPlayerFile().saveConfig();

    }

    public int getAllDoneObjective() {
        return plugin.getPlayerConfig().getInt(player.getUniqueId().toString() + ".DoneObjective");
    }

    public boolean isOnObjective(String objective) {
        return plugin.getPlayerConfig().getString(player.getUniqueId().toString() + ".CurrentObjective") == objective;
    }

    public boolean onObjective() {
        return getObjective() != "";
    }

    public String getObjective() {
        return plugin.getPlayerConfig().getString(player.getUniqueId().toString() + ".CurrentObjective");
    }
}
