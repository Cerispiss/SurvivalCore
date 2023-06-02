package xyz.acturusnetwork.cerispis.listeners;

import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.api.event.SkillLevelUpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.acturusnetwork.cerispis.SurvivalCore;
import xyz.acturusnetwork.cerispis.manager.Level;
import xyz.acturusnetwork.cerispis.manager.Objective;

import java.util.Random;

import static xyz.acturusnetwork.cerispis.chat.Chat.color;

public class AureliumLevelUpListener implements Listener {
    private SurvivalCore plugin = SurvivalCore.getInstance();

    @EventHandler
    public void a(SkillLevelUpEvent e) {
        Player p = (Player) e.getPlayer();
        Level level = new Level(p);
        Objective objective = new Objective(p);

        Random rand = new Random();
        int a = rand.nextInt(30);
        a = a + 1;

        level.addExp(a);
        p.sendMessage(color("&aYou earned &b" + a + " Survival EXP &afrom Reach Skill Level."));
    }
}
