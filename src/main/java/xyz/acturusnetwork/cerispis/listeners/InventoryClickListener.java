package xyz.acturusnetwork.cerispis.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.api.ACommand;
import xyz.acturusnetwork.cerispis.framework.Upgrader;
import xyz.acturusnetwork.cerispis.manager.Economy;
import xyz.acturusnetwork.cerispis.manager.UpgraderManager;
import xyz.acturusnetwork.cerispis.scoreboard.SScoreboard;

import static xyz.acturusnetwork.cerispis.chat.Chat.color;

public class InventoryClickListener implements Listener {
    SurvivalCore plugin = SurvivalCore.getInstance();

    @EventHandler
    public void a(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(color("&eUpgrader"))) {
            UpgraderManager upgraderManager = new UpgraderManager(player);
            Economy economy = new Economy(player);
            switch (e.getCurrentItem().getType()) {
                case IRON_SWORD:
                    if (economy.getCoins() != 0 || upgraderManager.getLevel(Upgrader.ATTACK_DAMAGE) == 12) {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 2.0F, 1.0F);
                    }

                    upgraderManager.addCoinsSpent(Upgrader.ATTACK_DAMAGE, 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 2.0F, 1.0F);
                    break;
                case REDSTONE:
                    if (economy.getCoins() != 0 || upgraderManager.getLevel(Upgrader.HEARTH) == 12) {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 2.0F, 1.0F);
                    }

                    upgraderManager.addCoinsSpent(Upgrader.HEARTH, 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 2.0F, 1.0F);
                    break;
                case GOLDEN_APPLE:
                    if (economy.getCoins() != 0 || upgraderManager.getLevel(Upgrader.HEAL) == 12) {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 2.0F, 1.0F);
                    }

                    upgraderManager.addCoinsSpent(Upgrader.HEAL, 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 2.0F, 1.0F);
                    break;
            }

            SScoreboard.create(player);
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(color("&eItem Creator"))) {
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(color("&eConfirm Your Sell."))) {
            Material itemInHand = player.getInventory().getItemInMainHand().getType();
            Economy economy = new Economy(player);

            int amountInHand = player.getInventory().getItemInMainHand().getAmount();
            ItemStack ten = new ItemStack(itemInHand, 10);
            ItemStack all = new ItemStack(itemInHand, amountInHand);
            if (!itemInHand.isAir()) {
                switch (e.getCurrentItem().getType()){
                    case COAL:
                        player.getInventory().remove(itemInHand);
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);

                        economy.addCoins(ACommand.priceItem.get(player));
                        player.sendMessage(color("&aYou have sell &a1&7x &f" + itemInHand.toString()));
                        player.sendMessage(color("&aYou earned &b" + ACommand.priceItem.get(player) + " Survival Gems &afrom Selling."));
                        break;
                    case GOLD_INGOT:
                        player.getInventory().remove(ten);
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);

                        economy.addCoins(ACommand.priceItem.get(player));
                        player.sendMessage(color("&aYou have sell &a10&7x &f" + itemInHand.toString()));
                        player.sendMessage(color("&aYou earned &b" + ACommand.priceItem.get(player) + " Survival Gems &afrom Selling."));
                        break;
                    case DIAMOND:
                        player.getInventory().remove(all);
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.0F);

                        economy.addCoins(ACommand.priceItem.get(player));
                        player.sendMessage(color("&aYou have sell &a" + amountInHand + "&7x &f" + itemInHand.toString()));
                        player.sendMessage(color("&aYou earned &b" + ACommand.priceItem.get(player) + " Survival Gems &afrom Selling."));
                        break;
                }
            }else {
                player.sendMessage(color("&cYou cannot sell Air."));
            }

            SScoreboard.create(player);
            player.closeInventory();
            e.setCancelled(true);
        }
    }

}
