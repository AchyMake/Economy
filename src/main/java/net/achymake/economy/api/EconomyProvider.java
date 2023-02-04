package net.achymake.economy.api;

import net.achymake.economy.config.Config;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.OfflinePlayer;

import java.text.DecimalFormat;

public class EconomyProvider {
    public static double getEconomy(OfflinePlayer offlinePlayer){
        return PlayerConfig.get(offlinePlayer).getDouble("account");
    }
    public static void addEconomy(OfflinePlayer offlinePlayer, double amount){
        double newAmount = amount + getEconomy(offlinePlayer);
        PlayerConfig.setDouble(offlinePlayer,"account",newAmount);
    }
    public static void removeEconomy(OfflinePlayer offlinePlayer, double amount){
        double newAmount = getEconomy(offlinePlayer) - amount;
        PlayerConfig.setDouble(offlinePlayer,"account",newAmount);
    }
    public static void setEconomy(OfflinePlayer offlinePlayer, double amount){
        PlayerConfig.setDouble(offlinePlayer,"account",amount);
    }
    public static void resetEconomy(OfflinePlayer offlinePlayer){
        PlayerConfig.setDouble(offlinePlayer,"account",Config.config.getDouble("settings.starting-balance"));
    }
    public static String getFormat(Double value) {
        String format = Config.config.getString("settings.format");
        DecimalFormat balance = new DecimalFormat(format);
        String formatted = balance.format(value);
        return Config.config.getString("settings.currency")+formatted;
    }
}