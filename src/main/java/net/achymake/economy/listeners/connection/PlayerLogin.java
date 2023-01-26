package net.achymake.economy.listeners.connection;

import net.achymake.economy.Economy;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {
    public PlayerLogin(Economy plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin (PlayerLoginEvent event){
        Player player = event.getPlayer();
        PlayerConfig.create(player);
    }
}