package net.achymake.economy.command.eco;

import net.achymake.economy.command.eco.sub.*;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EcoCommand implements CommandExecutor, TabCompleter {
    private ArrayList<EcoSubCommand> ecoSubCommands = new ArrayList<>();

    public EcoCommand(){
        ecoSubCommands.add(new Add());
        ecoSubCommands.add(new Remove());
        ecoSubCommands.add(new Reset());
        ecoSubCommands.add(new Set());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0){
            for (EcoSubCommand commands : ecoSubCommands){
                if (args[0].equals(commands.getName())){
                    commands.perform((Player) sender,args);
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            if (sender.hasPermission("economy.eco.add")){
                commands.add("add");
            }
            if (sender.hasPermission("economy.eco.remove")){
                commands.add("remove");
            }
            if (sender.hasPermission("economy.eco.reset")){
                commands.add("reset");
            }
            if (sender.hasPermission("economy.eco.set")){
                commands.add("set");
            }
            return commands;
        }else if (args.length == 2) {
            if (sender.hasPermission("economy.eco.add")){
                if (args[0].equalsIgnoreCase("add")){
                    for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()){
                        commands.add(offlinePlayer.getName());
                    }
                }
            }
            if (sender.hasPermission("economy.eco.remove")){
                if (args[0].equalsIgnoreCase("remove")){
                    for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()){
                        commands.add(offlinePlayer.getName());
                    }
                }
            }
            if (sender.hasPermission("economy.eco.reset")){
                if (args[0].equalsIgnoreCase("reset")){
                    for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()){
                        commands.add(offlinePlayer.getName());
                    }
                }
            }
            if (sender.hasPermission("economy.eco.set")){
                if (args[0].equalsIgnoreCase("set")){
                    for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()){
                        commands.add(offlinePlayer.getName());
                    }
                }
            }
            return commands;
        }else if (args.length == 3) {
            if (sender.hasPermission("economy.eco.add")){
                if (args[0].equalsIgnoreCase("add")){
                    commands.add("50");
                    commands.add("100");
                    commands.add("1000");
                }
            }
            if (sender.hasPermission("economy.eco.remove")){
                if (args[0].equalsIgnoreCase("remove")){
                    commands.add("50");
                    commands.add("100");
                    commands.add("1000");
                }
            }
            if (sender.hasPermission("economy.eco.set")){
                if (args[0].equalsIgnoreCase("set")){
                    commands.add("50");
                    commands.add("100");
                    commands.add("1000");
                }
            }
            return commands;
        }
        return commands;
    }
}