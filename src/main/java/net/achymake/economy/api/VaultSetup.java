package net.achymake.economy.api;

import net.achymake.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

public class VaultSetup {
    private static boolean isVaultEnabled() {
        return Bukkit.getPluginManager().getPlugin("Vault") != null;
    }
    public static void setup(Economy plugin){
        if (isVaultEnabled()){
            try {
                Class.forName("net.milkbowl.vault.economy.Economy");
                plugin.getServer().getServicesManager().register(net.milkbowl.vault.economy.Economy.class, new VaultEconomyProvider(plugin), plugin, ServicePriority.Normal);
            } catch (ClassNotFoundException e) {
                Economy.instance.sendMessage(e.getMessage());
            }
        }else{
            plugin.sendMessage("You have to install 'Vault'");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}