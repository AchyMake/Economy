package net.achymake.economy.command.eco.sub;

import net.achymake.economy.command.eco.EcoSubCommand;
import net.achymake.economy.config.Files;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Reload extends EcoSubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "set eco to player account";
    }

    @Override
    public String getSyntax() {
        return "/economy reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1){
            Files.reload();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Economy reloaded"));
        }
    }
}