package net.achymake.economy.api;

import net.achymake.economy.Economy;
import org.bukkit.plugin.ServicePriority;

public class VaultSetup {
    private static boolean setupEconomy(Economy plugin) {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        try {
            Class.forName("net.milkbowl.vault.economy.Economy");
            plugin.getServer().getServicesManager().register(net.milkbowl.vault.economy.Economy.class, new VaultEconomyProvider(plugin), plugin, ServicePriority.Normal);
        } catch (ClassNotFoundException e) {
            Economy.instance.sendMessage(e.getMessage());
        }
        return true;
    }
    public static void setup(Economy plugin){
        if (!setupEconomy(plugin)){
            plugin.getLogger().severe(String.format("[%s] - no Vault found!", plugin.getName()));
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}