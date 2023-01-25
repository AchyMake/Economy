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
        ecoSubCommands.add(new Reload());
        ecoSubCommands.add(new Remove());
        ecoSubCommands.add(new Reset());
        ecoSubCommands.add(new Set());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0){
            for (EcoSubCommand commands : getSubCommands()){
                if (args[0].equals(commands.getName())){
                    commands.perform((Player) sender,args);
                }
            }
        }
        return true;
    }
    public ArrayList<EcoSubCommand> getSubCommands(){
        return ecoSubCommands;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (EcoSubCommand subCommand : getSubCommands()){
                commands.add(subCommand.getName());
            }
            return commands;
        }else if (args.length == 2) {
            for (OfflinePlayer offlinePlayer : sender.getServer().getOfflinePlayers()){
                commands.add(offlinePlayer.getName());
            }
            return commands;
        }else if (args.length == 3) {
            commands.add("50");
            commands.add("100");
            commands.add("1000");
            return commands;
        }
        return commands;
    }
}