package net.achymake.economy.command.pay;

import net.achymake.economy.config.MessageConfig;
import net.achymake.economy.config.PlayerConfig;
import net.achymake.economy.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class PayCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                double amount = Double.parseDouble(args[1]);
                if (amount <= Settings.getEconomy(player.getUniqueId())){
                    Settings.addEconomy(target.getUniqueId(),amount);
                    Settings.removeEconomy(player.getUniqueId(),amount);
                    if (target.isOnline()){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command-pay"),target.getName(), Settings.getFormat(amount))));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command-pay-target"),player.getName(), Settings.getFormat(amount))));
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFormat.format(MessageConfig.get().getString("command-pay"),target.getName(), Settings.getFormat(amount))));
                    }
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageFormat.format(MessageConfig.get().getString("error-not-enough-currency"),Settings.getFormat(amount))));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (Player players : Bukkit.getOnlinePlayers()){
                commands.add(players.getName());
            }
            return commands;
        }else if (args.length == 2) {
            commands.add("50");
            commands.add("100");
            commands.add("1000");
            return commands;
        }
        return commands;
    }
}