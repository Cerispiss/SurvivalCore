package xyz.acturusnetwork.cerispis.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.manager.Objective;

public class ProcessCommandListener implements Listener {

    @EventHandler
    public void a(PlayerCommandPreprocessEvent e) {
        Player p = (Player) e.getPlayer();
        Objective objective = new Objective(p);
        String message = e.getMessage();
        String[] array = message.split(" ");

        if (array[0].equalsIgnoreCase("/kit")) {
            if (objective.isOnObjective("Get your own kits.")) {
                objective.setNewObjective("Type /play");
                Chat.objectiveFinish(p);
            }
        }
    }
}
