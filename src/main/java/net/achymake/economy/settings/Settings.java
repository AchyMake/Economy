package net.achymake.economy.settings;

import net.achymake.economy.Economy;
import net.achymake.economy.config.Config;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class Settings {
    public static double getEconomy(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getDouble("account");
    }
    public static void addEconomy(OfflinePlayer offlinePlayer, double amount){
        File file = new File(Economy.instance.getDataFolder(), "database/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        double newAmount = amount + configuration.getDouble("account");
        try {
            configuration.set("account",newAmount);
            configuration.save(file);
        } catch (IOException e) {
            Economy.instance.sendMessage(e.getMessage());
        }
    }
    public static void removeEconomy(OfflinePlayer offlinePlayer, double amount){
        File file = new File(Economy.instance.getDataFolder(), "database/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        double newAmount = configuration.getDouble("account") - amount;
        try {
            configuration.set("account",newAmount);
            configuration.save(file);
        } catch (IOException e) {
            Economy.instance.sendMessage(e.getMessage());
        }
    }
    public static void setEconomy(OfflinePlayer offlinePlayer, double amount){
        File file = new File(Economy.instance.getDataFolder(), "database/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        try {
            configuration.set("account",amount);
            configuration.save(file);
        } catch (IOException e) {
            Economy.instance.sendMessage(e.getMessage());
        }
    }
    public static void resetEconomy(OfflinePlayer offlinePlayer){
        File file = new File(Economy.instance.getDataFolder(), "database/"+offlinePlayer.getUniqueId()+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        try {
            configuration.set("account",0.0);
            configuration.save(file);
        } catch (IOException e) {
            Economy.instance.sendMessage(e.getMessage());
        }
    }
    public static String getFormat(Double value) {
        String format = Config.get().getString("settings.format");
        DecimalFormat balance = new DecimalFormat(format);
        String formatted = balance.format(value);
        return Config.get().getString("settings.currency")+formatted;
    }
}