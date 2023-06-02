package xyz.acturusnetwork.cerispis.listeners;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.manager.*;

public class JoinQuitListener implements Listener {
    SurvivalCore plugin = SurvivalCore.getInstance();

    @EventHandler
    public void a(PlayerJoinEvent e) {
        Player  p = (Player) e.getPlayer();
        boolean firstJoin = !p.hasPlayedBefore();
        Objective objective = new Objective(p);
        Level level = new Level(p);
        Economy economy = new Economy(p);
        UpgraderManager upgraderManager = new UpgraderManager(p);

        if (firstJoin) {
            level.setLevelData();
            objective.setObjectiveData();
            objective.setNewObjective("Get your own kits.");
            economy.setCoinsData();
            upgraderManager.setData();
            Chat.sendMessage(p, "&eWelcome to the Acturus Network Survival. Please talk to the &b&lLeo &f&eto know what you have to do first.");
        } else if (plugin.getPlayerConfig().getString(p.getUniqueId().toString()) == null) {
            level.setLevelData();
            economy.setCoinsData();
            objective.setObjectiveData();
            objective.setNewObjective("Get your own kits.");
            upgraderManager.setData();
        }

        e.setJoinMessage("");
        if (objective.onObjective()) {
            p.sendMessage(Chat.color("&aYou have undone objective. Please type /objective to check what's your objective right now."));
        }
    }
}
