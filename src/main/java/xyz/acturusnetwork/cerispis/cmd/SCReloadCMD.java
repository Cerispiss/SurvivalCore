package xyz.acturusnetwork.cerispis.cmd;

import org.bukkit.command.CommandSender;
import xyz.acturusnetwork.cerispis.api.ACommand;
import xyz.acturusnetwork.cerispis.chat.Chat;

public class SCReloadCMD extends ACommand {

    public SCReloadCMD() {
        super("screload");
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        plugin.getPlayerFile().loadConfig();
        plugin.reloadConfig();
        sender.sendMessage(Chat.color("&6All config has been reloaded."));
    }
}
