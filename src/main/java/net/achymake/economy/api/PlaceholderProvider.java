package net.achymake.economy.api;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.achymake.economy.Economy;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlaceholderProvider extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "economy";
    }
    @Override
    public String getAuthor() {
        return "AchyMake";
    }
    @Override
    public String getVersion() {
        return Economy.instance.getDescription().getVersion();
    }
    @Override
    public boolean canRegister() {
        return true;
    }
    @Override
    public boolean register() {
        return super.register();
    }
    @Override
    public boolean persist() {
        return true;
    }
    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (player == null){
            return "";
        }
        if (params.equals("account")) {
            if (PlayerConfig.get(player).getKeys(false).contains("account")){
                return ChatColor.translateAlternateColorCodes('&',"&a"+EconomyProvider.getFormat(EconomyProvider.getEconomy(player)));
            }else{
                return "null";
            }
        }
        return super.onRequest(player, params);
    }
}