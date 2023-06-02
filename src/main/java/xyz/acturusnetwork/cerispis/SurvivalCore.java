package xyz.acturusnetwork.cerispis;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.acturusnetwork.cerispis.api.ACommand;
import xyz.acturusnetwork.cerispis.api.depend.LP;
import xyz.acturusnetwork.cerispis.cmd.*;
import xyz.acturusnetwork.cerispis.files.PlayerConfig;
import xyz.acturusnetwork.cerispis.listeners.*;
import xyz.acturusnetwork.cerispis.scoreboard.SScoreboard;

import java.lang.reflect.Field;
import java.util.Arrays;

public class SurvivalCore extends JavaPlugin implements Listener {
    private static SurvivalCore instance;
    private static SimpleCommandMap scm;
    private static SimplePluginManager spm;
    public BossBar bossBar;
    private PlayerConfig playerConfig;
    public LP lp;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        setAllConfig();

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        LuckPerms api = null;
        if (provider != null) {
            api = provider.getProvider();
            lp = new LP(api);
        }


        setSCM();
        registerCommands(new UpgraderCMD());
        registerCommands(new LevelCMD());
        registerCommands(new CoinsCMD());
        registerCommands(new ObjectiveCMD());
        registerCommands(new SCReloadCMD());
        registerCommands(new SellCMD());
        registerCommands(new SCItemsCMD());

        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new LevelListeners(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(api), this);
        getServer().getPluginManager().registerEvents(new SScoreboard(), this);
        getServer().getPluginManager().registerEvents(new ProcessCommandListener(), this);
        getServer().getPluginManager().registerEvents(new AureliumLevelUpListener(), this);

    }

    @Override
    public void onDisable() {
        getPlayerFile().saveConfig();
        getLogger().info("GoodBye :)");
    }

    public static SurvivalCore getInstance() {
        return instance;
    }

    private void setAllConfig() {
        playerConfig = new PlayerConfig();
        playerConfig.setConfig();
        playerConfig.saveConfig();
        playerConfig.loadConfig();
    }

    private void registerCommands(ACommand... commands) {
        Arrays.stream(commands).forEach((command) -> {
            scm.register("SurvivalCore", command);
        });
    }

    public void setSCM() {
        spm = (SimplePluginManager)this.getServer().getPluginManager();
        Field field = null;

        try {
            field = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        field.setAccessible(true);

        try {
            scm = (SimpleCommandMap)field.get(spm);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public FileConfiguration getPlayerConfig() {
        return playerConfig.getConfig();
    }

    public PlayerConfig getPlayerFile() {
        return playerConfig;
    }


}
