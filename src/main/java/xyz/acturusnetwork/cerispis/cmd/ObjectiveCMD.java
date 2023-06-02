package xyz.acturusnetwork.cerispis.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.acturusnetwork.cerispis.api.ACommand;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.manager.Objective;

public class ObjectiveCMD extends ACommand {

    public ObjectiveCMD() {
        super("objective");
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Chat.color("&cThis command is only for users."));
        }else {
            Objective objective = new Objective(((Player) sender).getPlayer());
            sender.sendMessage(Chat.color("&6Your current objective is&7: &2" + objective.getObjective()));
        }
    }
}
