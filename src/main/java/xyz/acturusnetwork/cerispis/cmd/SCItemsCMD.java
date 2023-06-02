package xyz.acturusnetwork.cerispis.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.acturusnetwork.cerispis.api.ACommand;

import static xyz.acturusnetwork.cerispis.chat.Chat.color;
import static xyz.acturusnetwork.cerispis.framework.ItemCreator.createItem;

public class SCItemsCMD extends ACommand {

    public SCItemsCMD() {
        super("scitems");
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color("&cThis command is only for users."));
        }else {
            Player p = (Player) sender;
            Inventory gui = Bukkit.createInventory(null, 27, color("&eItem Creator"));
            ItemStack close = createItem(Material.BARRIER, 1, false, false, "&cClose");

            ItemStack essence = createItem(Material.AMETHYST_SHARD, 1, false, false, "&aCreate Essence");
            ItemStack tools = createItem(Material.DIAMOND_AXE, 1, false, false, "&aCreate Tools & Sword");
            ItemStack armor = createItem(Material.LEATHER_CHESTPLATE, 1, false, false, "&aCreate Armor Set");

            gui.setItem(22, close);
            gui.setItem(10, essence);
            gui.setItem(13, tools);
            gui.setItem(16, armor);

            p.openInventory(gui);
        }
    }

}
