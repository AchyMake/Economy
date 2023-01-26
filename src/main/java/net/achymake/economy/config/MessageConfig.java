package net.achymake.economy.config;

import net.achymake.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageConfig {
    public static File configFile = new File(Economy.instance.getDataFolder(), "message.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void setup(){
        get().addDefault("command-balance","&6Balance: &a{0}");
        get().addDefault("command-balance-others","&f{0} &6balance: &a{1}");
        get().addDefault("command-pay","&6You paid &f{0} &c{1}");
        get().addDefault("command-pay-target","&f{0}&6 paid you &a{1}");
        List<String> addEconomy = new ArrayList<>();
        addEconomy.add("&6You added &a{0}&6 to &f{1}&6 account");
        addEconomy.add("{1}&6 has now &a{2}");
        get().addDefault("command-eco-add",addEconomy);
        List<String> removeEconomy = new ArrayList<>();
        removeEconomy.add("&6You removed &a{0}&6 from &f{1}&6 account");
        removeEconomy.add("{1}&6 has now &a{2}");
        get().addDefault("command-eco-remove",removeEconomy);
        List<String> setEconomy = new ArrayList<>();
        setEconomy.add("&6You set &a{0}&6 to &f{1}&6 account");
        setEconomy.add("{1}&6 has now &a{2}");
        get().addDefault("command-eco-set",setEconomy);
        List<String> resetEconomy = new ArrayList<>();
        resetEconomy.add("&6You reset &f{0}&6 account");
        resetEconomy.add("{0}&6 has now &a{1}");
        get().addDefault("command-eco-reset",resetEconomy);
        get().addDefault("error-not-enough-currency","&cYou dont have &a{0}");
        get().addDefault("error-target-null","&f{0}&c has never joined");
        get().options().copyDefaults(true);
        save();
    }
    public static FileConfiguration get(){
        return config;
    }
    public static void save(){
        try {
            config.save(configFile);
        } catch (IOException e) {
            Economy.instance.sendMessage(e.getMessage());
        }
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}