package net.achymake.economy.listeners.connection;

import net.achymake.economy.Economy;
import net.achymake.economy.version.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoin implements Listener {
    public PlayerJoin(Economy plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLogin (PlayerLoginEvent event){
        Player player = event.getPlayer();
        if (!player.hasPermission("homes.reload"))return;
        UpdateChecker.sendMessage(player);
    }
}