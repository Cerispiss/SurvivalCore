package xyz.acturusnetwork.cerispis.api;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xyz.acturusnetwork.cerispis.SurvivalCore;

import java.util.*;
import java.util.stream.Collectors;

public abstract class ACommand extends Command implements PluginIdentifiableCommand {
    CommandSender sender2;
    public static SurvivalCore plugin = SurvivalCore.getInstance();
    String cmdName;
    private ArrayList<Player> fly = new ArrayList();
    private boolean reg = false;
    public static Map<Player, Integer> priceItem = new HashMap<>();

    protected ACommand(String name) {
        super(name);
    }

    protected void setAliases(String... aliases) {
        if (aliases != null | aliases.length > 0) {
            this.setAliases((List) Arrays.stream(aliases).collect(Collectors.toList()));
        }

    }

    public Plugin getPlugin() {
        return plugin;
    }

    public abstract void run(CommandSender sender, String label, String[] args);

    public boolean execute(CommandSender sender, String label, String[] args) {
        this.sender2 = sender;
        this.run(sender, label, args);
        return true;
    }
}
