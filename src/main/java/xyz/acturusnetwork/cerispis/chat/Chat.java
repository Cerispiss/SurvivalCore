package xyz.acturusnetwork.cerispis.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.acturusnetwork.cerispis.manager.Level;

public class Chat {

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String decolor(String str) {
        return ChatColor.stripColor(color(str));
    }

    public static void sendMessage(Player player, String str) {
        player.sendMessage(color(str));
    }

    public static void levelUpMessage(Player player) {
        Level level = new Level(player);
        sendMessage(player, "&f");
        sendMessage(player, "&eYou are now &a&kU&f &6&lLEVEL UP &a&kU&f");
        sendMessage(player, "&7You're now reached to level &5" + level.getLevel());
        sendMessage(player, "&f");
        sendMessage(player, "&f &7You get your reward.");
        sendMessage(player, "&f");
        sendMessage(player, "&eType /claim to claim your reward");
        sendMessage(player, "&f");
    }

    public static void unlockMessage(Player player, String feature) {
        sendMessage(player, "&bNew Features .");
        sendMessage(player, "&f&l> " + feature);
    }

    public static void objectiveFinish(Player player) {
        sendMessage(player, "&f");
        sendMessage(player, "&a&lYou have finis your objective! We have send you a new objective, type /objective to check your current objective.");
    }
}
