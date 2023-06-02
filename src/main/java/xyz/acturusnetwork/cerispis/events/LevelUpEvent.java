package xyz.acturusnetwork.cerispis.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import xyz.acturusnetwork.cerispis.manager.Level;

public class LevelUpEvent extends Event {
    private final Player player;
    private final Level level;
    private static final HandlerList handlers = new HandlerList();

    public LevelUpEvent(Player player) {
        this.player = player;
        this.level = new Level(player);
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Level getLevel() {
        return this.level;
    }

}
