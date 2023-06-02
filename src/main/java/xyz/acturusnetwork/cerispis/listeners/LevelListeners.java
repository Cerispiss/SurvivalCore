package xyz.acturusnetwork.cerispis.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.events.LevelUpEvent;

public class LevelListeners implements Listener {
    SurvivalCore plugin = SurvivalCore.getInstance();

    @EventHandler
    public void a(LevelUpEvent e) {
        if (e.getLevel().getLevel() == 2) {
            Chat.unlockMessage(e.getPlayer(), "New Area: Mining & Farming Area");
        }
    }

}
