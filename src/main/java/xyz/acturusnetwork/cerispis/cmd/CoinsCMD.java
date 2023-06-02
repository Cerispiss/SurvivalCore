package xyz.acturusnetwork.cerispis.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.acturusnetwork.cerispis.api.ACommand;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.manager.Economy;

public class CoinsCMD extends ACommand {

    public CoinsCMD() {
        super("coins");
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Chat.color("&cThis command is only for users."));
        }else{
            Economy economy = new Economy(((Player) sender).getPlayer());
            Chat.sendMessage(((Player) sender).getPlayer(), "&6Your Survival Coins is&7: &e" + economy.getCoins() + " $");
        }
    }
}
