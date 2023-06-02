package xyz.acturusnetwork.cerispis.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.acturusnetwork.cerispis.api.ACommand;

import java.util.ArrayList;
import java.util.Random;

import static xyz.acturusnetwork.cerispis.chat.Chat.color;
import static xyz.acturusnetwork.cerispis.framework.ItemCreator.createItem;

public class SellCMD extends ACommand {
    private static Random rand = new Random();
    public static Integer pricePerUnit;

    public SellCMD() {
        super("sell");
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {

        }else {
            Player p = (Player) sender;
            Inventory gui = Bukkit.createInventory(null, 27, color("&eConfirm Your Sell."));
            int amountItemInHand = p.getInventory().getItemInMainHand().getAmount();

            pricePerUnit = rand.nextInt(1000, 5000);
            pricePerUnit = pricePerUnit + 1;

            priceItem.put(p, pricePerUnit);

            int tenTimes = pricePerUnit * 10;
            int ppnTimesAmount =  pricePerUnit * amountItemInHand;

            Material itemInHand = p.getInventory().getItemInMainHand().getType();
            ArrayList<String> one_lore = new ArrayList<>();
            ArrayList<String> ten_lore = new ArrayList<>();
            ArrayList<String> all_lore = new ArrayList<>();
            one_lore.add(color("&f"));
            one_lore.add(color("\n &7Price per unit: &e" + pricePerUnit + " Gems"));
            one_lore.add(color("\n &f"));
            one_lore.add(color("\n &7Selling: &a1&7x &f" + itemInHand.toString()));
            one_lore.add(color("\b &7You earn: &e" + pricePerUnit) + " Gems");
            one_lore.add(color("\n &f"));
            one_lore.add(color("\n &aClick to confirm."));

            ten_lore.add(color("&f \n &7Price per unit; &e" + pricePerUnit + " Gems"));
            ten_lore.add(color("\n &f"));
            ten_lore.add(color("\n &7Selling: &a10&7x" + itemInHand.toString()));
            ten_lore.add(color("\n &7You earn: &e" + tenTimes + " Gems"));
            ten_lore.add(color("\n &f \n &aClick to confirm."));

            all_lore.add(color("&f \n &7Price per unit: &e" + pricePerUnit + " Gems"));
            all_lore.add(color("\n &f \n &7Selling: &a" + amountItemInHand + "&7x &f" + itemInHand.toString()));
            all_lore.add(color("\n &7You earn: &e" + ppnTimesAmount + " Gems"));
            all_lore.add(color("\n &f \n &aClick to confirm"));

            ItemStack one = createItem(Material.COAL, 1, false, false, color("&aSell 1x Item."), one_lore.toString());
            ItemStack ten = createItem(Material.GOLD_INGOT, 10, false, false, color("&aSell 10x Item."), ten_lore.toString());
            ItemStack all = createItem(Material.DIAMOND, 64, false, false, color("&aSell All Item."), all_lore.toString());

            gui.setItem(10, one);
            gui.setItem(13, ten);
            gui.setItem(16, all);

            p.openInventory(gui);
        }
    }
}
