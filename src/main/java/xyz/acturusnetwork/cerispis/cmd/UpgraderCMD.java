package xyz.acturusnetwork.cerispis.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.acturusnetwork.cerispis.api.ACommand;
import xyz.acturusnetwork.cerispis.framework.Upgrader;
import xyz.acturusnetwork.cerispis.manager.Economy;
import xyz.acturusnetwork.cerispis.manager.UpgraderManager;

import java.util.ArrayList;

import static xyz.acturusnetwork.cerispis.chat.Chat.color;
import static xyz.acturusnetwork.cerispis.framework.ItemCreator.createItem;

public class UpgraderCMD extends ACommand {
    public static ItemStack ATTACK_ICON;
    public static ItemStack HEARTH_ICON;
    public static ItemStack HEAL_ICON;

    public UpgraderCMD() {
        super("upgrade");
        setAliases("upgrader");
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color("&cThis command is only for users."));
        }else{
            Player player = (Player) sender;
            UpgraderManager upgraderManager = new UpgraderManager(player);
            Economy economy = new Economy(player);

            Inventory gui = Bukkit.createInventory(null, 27, color("&eUpgrader."));

            ArrayList<String> attack_lore = new ArrayList<>();
            attack_lore.add(color("\n &f"));
            attack_lore.add(color("\n &7Coins Spent: &a" + upgraderManager.getCoinsSpent(Upgrader.ATTACK_DAMAGE)));
            attack_lore.add(color("\n &7Level: &a" + upgraderManager.getLevel(Upgrader.ATTACK_DAMAGE)));
            attack_lore.add(color("\n &f"));
            attack_lore.add(color("\n &8When Leveled Up &7(To " + upgraderManager.getNextLevel(Upgrader.ATTACK_DAMAGE) + ")"));
            attack_lore.add(color("\n &7 +3% Attack Damage Update"));
            attack_lore.add(color("\n &f"));
            if (economy.getCoins() != 0) {
                if (upgraderManager.getLevel(Upgrader.ATTACK_DAMAGE) < 12) {
                    attack_lore.add(color("\n &aClick to upgrade this."));
                }else {
                    attack_lore.add(color("\n &cYou're already at the max of the level."));
                }
            }else {
                attack_lore.add(color("\n &cYou don't have enough coins to purchase this."));
            }

            ArrayList<String> hearth_lore = new ArrayList<>();
            hearth_lore.add(color("\n &f"));
            hearth_lore.add(color("\n &7Coins Spent: &a" + upgraderManager.getCoinsSpent(Upgrader.HEARTH)));
            hearth_lore.add(color("\n &7Level: &a" + upgraderManager.getLevel(Upgrader.HEARTH)));
            hearth_lore.add(color("\n &f"));
            hearth_lore.add(color("\n &8When Leveled Up &7(To " + upgraderManager.getNextLevel(Upgrader.HEARTH) + ")"));
            hearth_lore.add(color("\n &7 +3% Attack Damage Update"));
            hearth_lore.add(color("\n &f"));
            if (economy.getCoins() != 0) {
                if (upgraderManager.getLevel(Upgrader.HEARTH) < 12) {
                    hearth_lore.add(color("\n &aClick to upgrade this."));
                }else {
                    hearth_lore.add(color("\n &cYou're already at the max of the level."));
                }
            }else {
                hearth_lore.add(color("\n &cYou don't have enough coins to purchase this."));
            }

            ArrayList<String> heal_lore = new ArrayList<>();
            heal_lore.add(color("&f"));
            heal_lore.add(color("\n &7Coins Spent: &a" + upgraderManager.getCoinsSpent(Upgrader.HEAL)));
            heal_lore.add(color("\n &7Level: &a" + upgraderManager.getLevel(Upgrader.HEAL)));
            heal_lore.add(color("\n &f"));
            heal_lore.add(color("\n &8When Leveled Up &7(To " + upgraderManager.getNextLevel(Upgrader.HEAL) + ")"));
            heal_lore.add(color("\n &7 +3% Attack Damage Update"));
            heal_lore.add(color("\n &f"));
            if (economy.getCoins() != 0) {
                if (upgraderManager.getLevel(Upgrader.HEAL  ) < 12) {
                    heal_lore.add(color("\n &aClick to upgrade this."));
                }else {
                    heal_lore.add(color("\n &cYou're already at the max of the level."));
                }
            }else {
                heal_lore.add(color("\n &cYou don't have enough coins to purchase this."));
            }

            // Items
            ATTACK_ICON = createItem(Material.IRON_SWORD, 1, false, false, "&aAttack Damage &eLv. " + upgraderManager.getLevel(Upgrader.ATTACK_DAMAGE), attack_lore.toString());
            HEARTH_ICON = createItem(Material.REDSTONE, 1, false, false, "&aHearth Amount &eLv. " + upgraderManager.getLevel(Upgrader.HEARTH), hearth_lore.toString());
            HEAL_ICON = createItem(Material.GOLDEN_APPLE, 1, false, false, "&aHeal Speed &eLv. " + upgraderManager.getLevel(Upgrader.HEAL), heal_lore.toString());

            int slot = 10;
            gui.setItem(10, ATTACK_ICON);
            gui.setItem(13, HEARTH_ICON);
            gui.setItem(16, HEAL_ICON);

            player.openInventory(gui);
        }
    }
}
