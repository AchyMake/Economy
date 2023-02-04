package net.achymake.economy.command.pay;

import net.achymake.economy.api.EconomyProvider;
import net.achymake.economy.config.Message;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PayCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 2) {
                Player player = (Player) sender;
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                if (PlayerConfig.exist(offlinePlayer)){
                    double amount = Double.parseDouble(args[1]);
                    if (amount <= EconomyProvider.getEconomy(player)){
                        EconomyProvider.addEconomy(offlinePlayer,amount);
                        EconomyProvider.removeEconomy(player,amount);
                        if (offlinePlayer.isOnline()){
                            Player target = player.getServer().getPlayerExact(args[0]);
                            player.sendMessage(Message.commandPaySender(target,amount));
                            target.sendMessage(Message.commandPayTarget(player,amount));
                        }else{
                            player.sendMessage(Message.commandPaySender(offlinePlayer,amount));
                        }
                    }else{
                        player.sendMessage(Message.errorNotEnoughCurrency(amount));
                    }
                }else{
                    player.sendMessage(Message.commandErrorTargetNull(args[0]));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()){
                commands.add(offlinePlayer.getName());
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