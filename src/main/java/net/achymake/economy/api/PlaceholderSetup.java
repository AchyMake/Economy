package net.achymake.economy.api;

import net.achymake.economy.Economy;
import org.bukkit.Bukkit;

public class PlaceholderSetup {
    private static boolean isPlaceholderAPIEnabled() {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }
    public static void setup(Economy plugin){
        if (isPlaceholderAPIEnabled()){
            new PlaceholderProvider().register();
        }else{
            plugin.sendMessage("You have to install 'PlaceholderAPI'");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}
