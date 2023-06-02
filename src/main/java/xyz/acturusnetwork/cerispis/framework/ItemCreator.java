package xyz.acturusnetwork.cerispis.framework;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static xyz.acturusnetwork.cerispis.chat.Chat.color;

public class ItemCreator {

    public static ItemStack createItem(Material material, int amount, boolean glow, boolean unbreaking, String name, String... lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (name != null) {
            meta.setDisplayName(color(name));
        }

        if (lore != null) {
            List<String> list = new ArrayList<>();
            for (String str : lore) {
                list.add(color(str));
            }
            meta.setLore(list);
        }

        if (glow) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
        }

        if (unbreaking) {
            meta.setUnbreakable(true);
        }

        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack[] createArmor(ItemStack helm, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        ItemStack[] armor = new ItemStack[4];
        armor[3] = helm;
        armor[2] = chestplate;
        armor[1] = leggings;
        armor[0] = boots;
        return armor;
    }
}
