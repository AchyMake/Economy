package net.achymake.economy;

import net.achymake.economy.api.PlaceholderSetup;
import net.achymake.economy.api.VaultSetup;
import net.achymake.economy.command.Commands;
import net.achymake.economy.config.Files;
import net.achymake.economy.listeners.Events;
import net.achymake.economy.version.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin {
    public static Economy instance;
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        instance = this;
        VaultSetup.setup(this);
        PlaceholderSetup.setup(this);
        Files.setup();
        Events.start(this);
        Commands.start(this);
        UpdateChecker.getUpdate(this);
        sendMessage("Enabled "+this.getName()+ " " +this.getDescription().getVersion());
    }
    @Override
    public void onDisable() {
        sendMessage("Disabled "+this.getName()+ " " +this.getDescription().getVersion());
    }
    public void sendMessage(String message){
        getServer().getConsoleSender().sendMessage("["+this.getName()+"] "+message);
    }
}