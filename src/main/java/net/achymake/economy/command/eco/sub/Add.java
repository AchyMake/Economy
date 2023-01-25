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

public class Add extends EcoSubCommand {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add eco to player account";
    }

    @Override
    public String getSyntax() {
        return "/eco add player amount";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3){
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
            double amount = Double.parseDouble(args[2]);
            UUID uuid = offlinePlayer.getUniqueId();
            Settings.addEconomy(offlinePlayer.getUniqueId(),amount);
            for (String messages : MessageConfig.get().getStringList("command-eco-add")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(messages,Settings.getFormat(amount),offlinePlayer.getName(),Settings.getFormat(Settings.getEconomy(uuid)))));
            }
        }
    }
}