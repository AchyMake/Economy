package net.achymake.economy;

import net.achymake.economy.api.VaultSetup;
import net.achymake.economy.command.Commands;
import net.achymake.economy.config.Files;
import net.achymake.economy.listeners.Events;
import net.achymake.economy.version.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin {
    public static Economy instance;
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        instance = this;
        VaultSetup.setup(this);
        Files.setup();
        Events.start(this);
        Commands.start(this);
        UpdateChecker.getUpdate(this);
        sendMessage("&aEnabled &f"+this.getName()+ " " +this.getDescription().getVersion());
    }
    @Override
    public void onDisable() {
        sendMessage("&cDisabled &f"+this.getName()+ " " +this.getDescription().getVersion());
    }
    public void sendMessage(String message){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&l[&e"+this.getName()+"&6&l]&r "+message));
    }
}