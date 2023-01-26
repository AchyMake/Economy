package net.achymake.economy.config;

import net.achymake.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {
    public static boolean exist(OfflinePlayer offlinePlayer){
        return new File(Economy.instance.getDataFolder(), "database/"+offlinePlayer.getUniqueId()+".yml").exists();
    }
    public static void create(OfflinePlayer offlinePlayer){
        File folder = new File(Economy.instance.getDataFolder(), "database");
        File file = new File(Economy.instance.getDataFolder(), "database/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!exist(offlinePlayer)){
            try {
                config.set("name", offlinePlayer.getName());
                config.set("account", 0.0);
                config.options().copyDefaults(true);
                config.save(file);
            } catch (IOException e) {
                Economy.instance.sendMessage(e.getMessage());
            }
        }else{
            if (config.getString("name") == null){
                try {
                    config.set("name", offlinePlayer.getName());
                    config.save(file);
                } catch (IOException e) {
                    Economy.instance.sendMessage(e.getMessage());
                }
            } else if (!config.getString("name").equals(offlinePlayer.getName())) {
                try {
                    config.set("name", offlinePlayer.getName());
                    config.save(file);
                } catch (IOException e) {
                    Economy.instance.sendMessage(e.getMessage());
                }
            }
        }
    }
    public static FileConfiguration get(OfflinePlayer offlinePlayer){
        File file = new File(Economy.instance.getDataFolder(), "database/"+offlinePlayer.getUniqueId()+".yml");
        return YamlConfiguration.loadConfiguration(file);
    }
    public static void reload(Server server){
        for (OfflinePlayer offlinePlayer : server.getOfflinePlayers()){
            File configFile = new File(Economy.instance.getDataFolder(), "database/"+offlinePlayer.getUniqueId()+".yml");
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);
            if (configFile.exists()){
                try {
                    configuration.load(configFile);
                } catch (IOException | InvalidConfigurationException e) {
                    Economy.instance.sendMessage(e.getMessage());
                }
            }
        }
    }
}