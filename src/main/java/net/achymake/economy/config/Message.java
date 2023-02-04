package net.achymake.economy.config;

import net.achymake.economy.Economy;
import net.achymake.economy.api.EconomyProvider;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class Message {
    private static final File file = new File(Economy.instance.getDataFolder(), "message.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    public static void setup(){
        config.addDefault("command.reload","&6Economy reloaded");
        config.addDefault("command.balance.self","&6Balance:&a {0}");
        config.addDefault("command.balance.other","{0} &6balance:&a {1}");
        config.addDefault("command.eco.add","&6You added &a{1}&6 to &f{0}&6 account");
        config.addDefault("command.eco.remove","&6You removed&a {1}&6 from &f{0}&6 account");
        config.addDefault("command.eco.set","&6You set&a {1}&6 to &f{0}&6 account");
        config.addDefault("command.eco.reset","&6You reset &f{0}&6 account");
        config.addDefault("command.pay.sender","&6You paid &f{0}&a {1}");
        config.addDefault("command.pay.target","{0}&6 paid you &a{1}");
        config.addDefault("error.not-enough-currency","&cYou dont have&a {0}");
        config.addDefault("error.target-null","{0}&c has never joined");
        config.options().copyDefaults(true);
        try {
            config.save(file);
        } catch (IOException e) {
            Economy.instance.sendMessage(e.getMessage());
        }
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }
    public static String commandReload(){
        return color(config.getString("command.reload"));
    }
    public static String commandErrorTargetNull(String name){
        return MessageFormat.format(color(config.getString("error.target-null")),name);
    }
    public static String errorNotEnoughCurrency(double amount){
        return MessageFormat.format(color(config.getString("error.not-enough-currency")),EconomyProvider.getFormat(amount));
    }
    public static String commandBalanceSelf(OfflinePlayer offlinePlayer){
        return MessageFormat.format(color(config.getString("command.balance.self")),EconomyProvider.getFormat(EconomyProvider.getEconomy(offlinePlayer)));
    }
    public static String commandBalanceOther(OfflinePlayer offlinePlayer){
        return MessageFormat.format(color(config.getString("command.balance.other")),offlinePlayer.getName(), EconomyProvider.getFormat(EconomyProvider.getEconomy(offlinePlayer)));
    }
    public static String commandEcoAdd(OfflinePlayer offlinePlayer, double amount){
        return MessageFormat.format(color(config.getString("command.eco.add")),offlinePlayer.getName(),EconomyProvider.getFormat(amount));
    }
    public static String commandEcoRemove(OfflinePlayer offlinePlayer, double amount){
        return MessageFormat.format(color(config.getString("command.eco.remove")),offlinePlayer.getName(),EconomyProvider.getFormat(amount));
    }
    public static String commandEcoReset(OfflinePlayer offlinePlayer){
        return MessageFormat.format(color(config.getString("command.eco.reset")),offlinePlayer.getName());
    }
    public static String commandEcoSet(OfflinePlayer offlinePlayer, double amount){
        return MessageFormat.format(color(config.getString("command.eco.set")),offlinePlayer.getName(),EconomyProvider.getFormat(amount));
    }
    public static String commandPaySender(OfflinePlayer offlinePlayer, double amount){
        return MessageFormat.format(color(config.getString("command.pay.sender")),offlinePlayer.getName(),EconomyProvider.getFormat(amount));
    }
    public static String commandPayTarget(OfflinePlayer offlinePlayer, double amount){
        return MessageFormat.format(color(config.getString("command.pay.target")),offlinePlayer.getName(),EconomyProvider.getFormat(amount));
    }
    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }
}