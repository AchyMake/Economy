package net.achymake.economy.config;

import net.achymake.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerConfig {
    public static boolean exist(UUID uuid){
        return new File(Economy.instance.getDataFolder(), "database/"+uuid+".yml").exists();
    }
    public static void create(UUID uuid){
        File folder = new File(Economy.instance.getDataFolder(), "database");
        File file = new File(Economy.instance.getDataFolder(), "database/"+uuid+".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!exist(uuid)){
            try {
                config.set("name", Bukkit.getOfflinePlayer(uuid).getName());
                config.set("account", 0.0);
                config.options().copyDefaults(true);
                config.save(file);
            } catch (IOException e) {
                Economy.instance.sendMessage(e.getMessage());
            }
        }else{
            if (config.getString("name") == null){
                try {
                    config.set("name", Bukkit.getOfflinePlayer(uuid).getName());
                    config.save(file);
                } catch (IOException e) {
                    Economy.instance.sendMessage(e.getMessage());
                }
            } else if (!config.getString("name").equals(Bukkit.getOfflinePlayer(uuid).getName())) {
                try {
                    config.set("name", Bukkit.getOfflinePlayer(uuid).getName());
                    config.save(file);
                } catch (IOException e) {
                    Economy.instance.sendMessage(e.getMessage());
                }
            }
        }
    }
    public static FileConfiguration get(UUID uuid){
        File file = new File(Economy.instance.getDataFolder(), "database/"+uuid+".yml");
        return YamlConfiguration.loadConfiguration(file);
    }
    public static void reload(){
        for (OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()){
            File configFile = new File(Economy.instance.getDataFolder(), "database/"+offlinePlayer.getUniqueId()+".yml");
            if (configFile.exists()){
                try {
                    YamlConfiguration.loadConfiguration(configFile).load(configFile);
                } catch (IOException | InvalidConfigurationException e) {
                    Economy.instance.sendMessage(e.getMessage());
                }
            }
        }
    }
}