package xyz.acturusnetwork.cerispis.listeners;

import net.luckperms.api.LuckPerms;
import net.noscape.project.supremetags.SupremeTags;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.manager.Level;


public class PlayerChatListener implements Listener {
    SurvivalCore plugin = SurvivalCore.getInstance();
    private LuckPerms lp;

    public PlayerChatListener(LuckPerms luckPerms) {
        this.lp = luckPerms;
    }

    @EventHandler
    public void a(AsyncPlayerChatEvent e) {
        Player player = (Player) e.getPlayer();
        Level level = new Level(e.getPlayer());

        if (plugin.lp.hasPrefix(player)) {
            e.setFormat(Chat.color("&7[" + level.getLevel() + "&7] &f" + plugin.lp.getPrefix(player) + " " + player.getName() + " &9&l> &f" + e.getMessage()));
        }else {
            e.setFormat(Chat.color("&7[" + level.getLevel() + "&7] &f" + player.getName() + " &9&l> &7" + e.getMessage()));
        }

        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 2.0F, 1.0F);
    }
}
