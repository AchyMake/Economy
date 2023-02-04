package net.achymake.economy.config;

import net.achymake.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    private static final File file = new File(Economy.instance.getDataFolder(), "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }
}