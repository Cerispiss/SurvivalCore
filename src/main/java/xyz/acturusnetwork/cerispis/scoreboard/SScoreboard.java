package xyz.acturusnetwork.cerispis.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.chat.Chat;
import xyz.acturusnetwork.cerispis.manager.Economy;

public class SScoreboard implements Listener {
    static SurvivalCore plugin = SurvivalCore.getInstance();

    public static void create(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        Objective obj = sb.registerNewObjective("Dummy", "Dummy", Chat.color("&e&lSURVIVAL"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        xyz.acturusnetwork.cerispis.manager.Objective objectives = new xyz.acturusnetwork.cerispis.manager.Objective(player);
        Economy economy = new Economy(player);

        if (plugin.getConfig().getBoolean("OnPrototype") == true) {
            Score info = obj.getScore(Chat.color("&8Survival Prototype V2"));
            info.setScore(12);
        }else {
            Score info = obj.getScore(Chat.color("&aSC-RC V-" + plugin.getDescription().getVersion()));
            info.setScore(12);
        }

        Score blank = obj.getScore(Chat.color("&f &f "));
        blank.setScore(11);

        Score area = obj.getScore(Chat.color("&6&l| &f&e") + player.getWorld().getName());
        area.setScore(10);

        Score objectivetitle = obj.getScore(Chat.color("  &bObjective &l>"));
        objectivetitle.setScore(9);

        Score objective = obj.getScore(Chat.color("   &f&l" + objectives.getObjective()));
        objective.setScore(8);

        Score blank1 = obj.getScore(Chat.color("&f &f &f"));
        blank1.setScore(7);

        Score coins = obj.getScore(Chat.color("&fGems: &e" + economy.getCoins()));
        coins.setScore(6);

        Score blank2 = obj.getScore(Chat.color("&fCoins: &e0 $"));
        blank2.setScore(5);

        Score questMaster = obj.getScore(Chat.color("&f &f &f &f"));
        questMaster.setScore(4);

        Score quest = obj.getScore(Chat.color("&fQuest: &7None."));
        quest.setScore(3);

        Score blank3 = obj.getScore(Chat.color("&f "));
        blank3.setScore(2);

        Score ip = obj.getScore(Chat.color("&eacturusnetwork.xyz"));
        ip.setScore(1);

        player.setScoreboard(sb);
    }

    @EventHandler
    public void a(PlayerJoinEvent e) {
        create(e.getPlayer());

    }
}
