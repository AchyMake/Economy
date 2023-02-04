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
        config.addDefault("command.reload","Economy reloaded");
        config.addDefault("command.balance.self","Balance: {0}");
        config.addDefault("command.balance.other","{0} balance: {1}");
        config.addDefault("command.eco.add","You added {0} to {1} account");
        config.addDefault("command.eco.remove","You removed {0} from {1} account");
        config.addDefault("command.eco.set","You set {0} to {1} account");
        config.addDefault("command.eco.reset","You reset {0} account");
        config.addDefault("command.pay.sender","You paid {0} {1}");
        config.addDefault("command.pay.target","{0} paid you {1}");
        config.addDefault("error.not-enough-currency","You dont have {0}");
        config.addDefault("error.target-null","{0} has never joined");
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
    public static String commandBalanceSelf(){
        return color(config.getString("command.balance.self"));
    }
    public static String commandBalanceOther(OfflinePlayer offlinePlayer){
        return MessageFormat.format(color(config.getString("command.balance.other")), EconomyProvider.getFormat(EconomyProvider.getEconomy(offlinePlayer)));
    }
    public static String commandEcoAdd(OfflinePlayer offlinePlayer, double amount){
        return MessageFormat.format(color(config.getString("command.add")),offlinePlayer.getName(),EconomyProvider.getFormat(amount));
    }
    public static String commandEcoRemove(OfflinePlayer offlinePlayer, double amount){
        return MessageFormat.format(color(config.getString("command.remove")),offlinePlayer.getName(),EconomyProvider.getFormat(amount));
    }
    public static String commandEcoReset(OfflinePlayer offlinePlayer){
        return MessageFormat.format(color(config.getString("command.reset")),offlinePlayer.getName(),EconomyProvider.getFormat(Config.config.getDouble("settings.starting-balance")));
    }
    public static String commandEcoSet(OfflinePlayer offlinePlayer, double amount){
        return MessageFormat.format(color(config.getString("command.set")),offlinePlayer.getName(),EconomyProvider.getFormat(amount));
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