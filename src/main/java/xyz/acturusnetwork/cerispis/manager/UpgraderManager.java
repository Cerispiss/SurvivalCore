package xyz.acturusnetwork.cerispis.manager;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.framework.Upgrader;

public class UpgraderManager {
    SurvivalCore plugin = SurvivalCore.getInstance();
    Economy economy;
    private final Player player;

    public UpgraderManager(Player player) {
        this.player = player;
        economy = new Economy(player);
    }

    public void setData() {
        for (Upgrader upgrader : Upgrader.values()) {
            plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Upgrader." + upgrader.name() + ".Level", 0);
            plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Upgrader." + upgrader.name() + ".CoinsSpent", 0);
            plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Upgrader." + upgrader.name() + ".MaxSpent", 60);
            plugin.getPlayerFile().saveConfig();
        }
    }

    public void setCoinsSpent(Upgrader upgrader, int amountOfCoinsSpent) {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Upgrader." + upgrader.name() + ".CoinsSpent", amountOfCoinsSpent);
        plugin.getPlayerFile().saveConfig();
    }

    public void addCoinsSpent(Upgrader upgrader, int amountOfCoinsSpent) {
        setCoinsSpent(upgrader, getCoinsSpent(upgrader) + 1);
        economy.removeCoins(amountOfCoinsSpent);

        if (getCoinsSpent(upgrader) >= getMaxSpent(upgrader)) {
            updateLevel(upgrader);
            setMaxSpent(upgrader);
        }
    }

    public void setMaxSpent(Upgrader upgrader) {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Upgrader." + upgrader.name() + ".MaxSpent", getMaxSpent(upgrader) + 60);
    }

    public void updateLevel(Upgrader upgrader) {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Upgrader." + upgrader.name() + ".Level", getLevel(upgrader) + 1);
        plugin.getPlayerFile().saveConfig();

        if (upgrader == Upgrader.ATTACK_DAMAGE) {
            double get = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(get + 0.2F);
        } else if (upgrader == Upgrader.HEARTH) {
            double get = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(get + 0.2F);
        } else if (upgrader == Upgrader.HEAL) {

        }
    }

    public int getCoinsSpent(Upgrader upgrader) {
        return plugin.getPlayerConfig().getInt(player.getUniqueId().toString() + ".Upgrader." + upgrader.name() + ".CoinsSpent");
    }

    public int getMaxSpent(Upgrader upgrader) {
        return plugin.getPlayerConfig().getInt(player.getUniqueId().toString() + ".Upgrader." + upgrader.name() + ".MaxSpent");
    }

    public int getLevel(Upgrader upgrader) {
        return plugin.getPlayerConfig().getInt(player.getUniqueId().toString() + ".Upgrader." + upgrader.name() + ".Level");
    }

    public int getNextLevel(Upgrader upgrader) {
        return getLevel(upgrader) + 1;
    }

}
