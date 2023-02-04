package net.achymake.economy.config;

import net.achymake.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {
    public static boolean exist(OfflinePlayer offlinePlayer){
        return new File(Economy.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml").exists();
    }
    public static void create(OfflinePlayer offlinePlayer){
        File folder = new File(Economy.instance.getDataFolder(), "userdata");
        File file = new File(Economy.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!file.exists()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set("name", offlinePlayer.getName());
            config.set("account", Config.config.getDouble("settings.starting-balance"));
            config.options().copyDefaults(true);
            try {
                config.save(file);
            } catch (IOException e) {
                Economy.instance.sendMessage(e.getMessage());
            }
        }else{
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            if (!config.getString("name").equals(offlinePlayer.getName())) {
                config.set("name", offlinePlayer.getName());
                try {
                    config.save(file);
                } catch (IOException e) {
                    Economy.instance.sendMessage(e.getMessage());
                }
            }
        }
    }
    public static void setDouble(OfflinePlayer offlinePlayer,String type, double value){
        File file = new File(Economy.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set(type,value);
        try {
            configuration.save(file);
        } catch (IOException e) {
            Economy.instance.sendMessage(e.getMessage());
        }
    }
    public static FileConfiguration get(OfflinePlayer offlinePlayer){
        return YamlConfiguration.loadConfiguration(new File(Economy.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml"));
    }
    public static void reload(){
        for (OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()){
            File file = new File(Economy.instance.getDataFolder(), "userdata/"+offlinePlayer.getUniqueId()+".yml");
            if (file.exists()){
                FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                try {
                    configuration.load(file);
                } catch (IOException | InvalidConfigurationException e) {
                    Economy.instance.sendMessage(e.getMessage());
                }
            }
        }
    }
}