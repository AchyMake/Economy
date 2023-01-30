package net.achymake.economy.command.eco.sub;

import net.achymake.economy.api.EconomyProvider;
import net.achymake.economy.command.eco.EcoSubCommand;
import net.achymake.economy.config.MessageConfig;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class Reset extends EcoSubCommand {
    @Override
    public String getName() {
        return "reset";
    }

    @Override
    public String getDescription() {
        return "reset eco for player account";
    }

    @Override
    public String getSyntax() {
        return "/eco reset target";
    }
    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 2){
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
            if (PlayerConfig.exist(offlinePlayer)){
                EconomyProvider.resetEconomy(offlinePlayer);
                for (String messages : MessageConfig.get().getStringList("command-eco-reset")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(messages,offlinePlayer.getName(),EconomyProvider.getFormat(EconomyProvider.getEconomy(offlinePlayer)))));
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("error-target-null"),offlinePlayer.getName())));
            }
        }
    }
}