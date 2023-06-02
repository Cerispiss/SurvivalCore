package xyz.acturusnetwork.cerispis.framework;

import org.bukkit.inventory.ItemStack;
import xyz.acturusnetwork.cerispis.SurvivalCore;

import static xyz.acturusnetwork.cerispis.chat.Chat.decolor;
import static xyz.acturusnetwork.cerispis.cmd.UpgraderCMD.*;

public enum Upgrader {
    ATTACK_DAMAGE("&aAttack Damage", ATTACK_ICON),
    HEARTH("&aHearth Amount", HEARTH_ICON),
    HEAL("&aHeal Speed", HEAL_ICON);


    private SurvivalCore plugin = SurvivalCore.getInstance();
    private String name, rawName;
    private ItemStack icon;

    Upgrader(String name, ItemStack icon) {
        this.name = name;
        this.rawName = decolor(name);

        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getRawName() {
        return rawName;
    }

    public ItemStack getIcon() {
        return icon;
    }
}
