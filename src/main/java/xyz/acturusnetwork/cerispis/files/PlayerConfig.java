package xyz.acturusnetwork.cerispis.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.acturusnetwork.cerispis.SurvivalCore;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {
    SurvivalCore plugin = SurvivalCore.getInstance();
    File file;
    FileConfiguration config;


    public void setConfig() {
        this.file = new File(this.plugin.getDataFolder(), "players.yml");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void loadConfig() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }
}
