package xyz.acturusnetwork.cerispis.cmd;

import org.bukkit.command.CommandSender;
import xyz.acturusnetwork.cerispis.api.ACommand;

public class TestCMD extends ACommand {

    public TestCMD() {
        super("test");
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        sender.sendMessage("test fjewfjewfjwio");
        sender.sendMessage("ferfoi");
    }
}
