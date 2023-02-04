package net.achymake.economy.command.reload;

import net.achymake.economy.config.Files;
import net.achymake.economy.config.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class EconomyCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("reload")){
                Files.reload();
                sender.sendMessage(Message.commandReload());
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            commands.add("reload");
            return commands;
        }
        return commands;
    }
}