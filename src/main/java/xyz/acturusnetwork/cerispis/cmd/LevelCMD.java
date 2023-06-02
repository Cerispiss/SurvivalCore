package xyz.acturusnetwork.cerispis.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.acturusnetwork.cerispis.api.ACommand;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.manager.Level;

public class LevelCMD extends ACommand {

    public LevelCMD() {
        super("level");
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Chat.color("&cThis command is only for users."));
        }else {
            Level level = new Level(((Player) sender).getPlayer());
            sender.sendMessage(Chat.color("&6Your Survival level is&7: &2" + level.getLevel() + " Level With " + level.getExp() + " Experienced"));
        }
    }
}
