package net.achymake.economy.command.balance;

import net.achymake.economy.api.EconomyProvider;
import net.achymake.economy.config.MessageConfig;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class BalanceCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0){
            MessageConfig.sendMessage(player,MessageFormat.format(MessageConfig.get().getString("command-balance"), EconomyProvider.getFormat(EconomyProvider.getEconomy(player))));
        } else if (args.length == 1) {
            if (player.hasPermission("economy.balance.others")){
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                if (PlayerConfig.exist(offlinePlayer)){
                    MessageConfig.sendMessage(player,MessageFormat.format(MessageConfig.get().getString("command-balance-others"),offlinePlayer.getName(),EconomyProvider.getFormat(EconomyProvider.getEconomy(offlinePlayer))));
                }else{
                    MessageConfig.sendMessage(player,MessageFormat.format(MessageConfig.get().getString("error-target-null"),offlinePlayer.getName()));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            if (sender.hasPermission("economy.balance.others")) {
                for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()) {
                    commands.add(offlinePlayer.getName());
                }
                return commands;
            }
        }
        return commands;
    }
}