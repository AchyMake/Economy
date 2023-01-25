package net.achymake.economy.settings;

import net.achymake.economy.Economy;
import net.achymake.economy.config.Config;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;

public class Settings {
    public static double getEconomy(UUID uuid){
        return PlayerConfig.get(uuid).getDouble("account");
    }
    public static void addEconomy(UUID uuid, double amount){
        File file = new File(Economy.instance.getDataFolder(), "database/"+uuid+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        double newAmount = amount + configuration.getDouble("account");
        try {
            configuration.set("account",newAmount);
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void removeEconomy(UUID uuid, double amount){
        File file = new File(Economy.instance.getDataFolder(), "database/"+uuid+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        double newAmount = configuration.getDouble("account") - amount;
        try {
            configuration.set("account",newAmount);
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setEconomy(UUID uuid, double amount){
        File file = new File(Economy.instance.getDataFolder(), "database/"+uuid+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        try {
            configuration.set("account",amount);
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void resetEconomy(UUID uuid){
        File file = new File(Economy.instance.getDataFolder(), "database/"+uuid+".yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        try {
            configuration.set("account",0.0);
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getFormat(Double value) {
        String format = Config.get().getString("settings.format");
        DecimalFormat balance = new DecimalFormat(format);
        String formatted = balance.format(value);
        return Config.get().getString("settings.currency")+formatted;
    }
}
