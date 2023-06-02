package xyz.acturusnetwork.cerispis.api.depend;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

public class LP {
    LuckPerms luckPerms;

    public LP(LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    public String getPrefix(Player player) {
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        String prefix = user.getCachedData().getMetaData().getPrefix();

        if (prefix == null) {
            return "";
        }else {
            return prefix;
        }
    }

    public boolean hasPrefix(Player player) {
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        String prefix = user.getCachedData().getMetaData().getPrefix();

        if (prefix != null) {
            return true;
        }else {
            return false;
        }
    }
}
