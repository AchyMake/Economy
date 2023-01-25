package net.achymake.economy.command.eco.sub;

import net.achymake.economy.command.eco.EcoSubCommand;
import net.achymake.economy.config.MessageConfig;
import net.achymake.economy.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.UUID;

public class Set extends EcoSubCommand {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "set eco to player account";
    }

    @Override
    public String getSyntax() {
        return "/eco set target amount";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3){
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
            UUID uuid = offlinePlayer.getUniqueId();
            double amount = Double.parseDouble(args[2]);
            Settings.setEconomy(offlinePlayer.getUniqueId(),amount);
            for (String messages : MessageConfig.get().getStringList("command-eco-set")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(messages,Settings.getFormat(amount),offlinePlayer.getName(),Settings.getFormat(Settings.getEconomy(uuid)))));
            }
        }
    }
}