package xyz.acturusnetwork.cerispis.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.manager.Level;
import xyz.acturusnetwork.cerispis.events.LevelUpEvent;

import java.util.Random;

public class EntityDeathListener implements Listener {
    SurvivalCore plugin = SurvivalCore.getInstance();

    @EventHandler
    public void a(EntityDeathEvent e) {
        Player player = e.getEntity().getKiller();
        EntityType entityType = e.getEntityType();

        Level level = new Level(player);
        if (player != null) {
            if (
                    entityType == EntityType.ZOMBIE |
                            entityType == EntityType.CREEPER |
                            entityType == EntityType.SKELETON |
                            entityType == EntityType.ENDERMAN |
                            entityType == EntityType.ZOMBIE |
                            entityType == EntityType.WITHER_SKELETON |
                            entityType == EntityType.ZOMBIE_VILLAGER |
                            entityType == EntityType.ZOMBIE |
                            entityType == EntityType.PILLAGER |
                            entityType == EntityType.BLAZE |
                            entityType == EntityType.RAVAGER |
                            entityType == EntityType.DROWNED |
                            entityType == EntityType.WITCH |
                            entityType == EntityType.SPIDER |
                            entityType == EntityType.CAVE_SPIDER |
                            entityType == EntityType.SPIDER |
                            entityType == EntityType.HUSK |
                            entityType == EntityType.PIGLIN |
                            entityType == EntityType.MAGMA_CUBE |
                            entityType == EntityType.ELDER_GUARDIAN |
                            entityType == EntityType.GUARDIAN | entityType == EntityType.ZOMBIE |
                            entityType == EntityType.EVOKER |
                            entityType == EntityType.ZOMBIE
            ) {
                Random rand = new Random();
                int amountExp = rand.nextInt(20);
                amountExp = amountExp+1;
                level.addExp(level.getExp() + amountExp);
                Chat.sendMessage(player, "&aYou earned &b" + amountExp + " Survival EXP &afrom killing " + entityType);
            }
        }
    }
}
