package net.achymake.economy.command.eco.sub;

import net.achymake.economy.api.EconomyProvider;
import net.achymake.economy.command.eco.EcoSubCommand;
import net.achymake.economy.config.Message;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Remove extends EcoSubCommand {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "remove eco from player account";
    }

    @Override
    public String getSyntax() {
        return "/eco remove target eco amount";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3){
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
            if (PlayerConfig.exist(offlinePlayer)){
                double amount = Double.parseDouble(args[2]);
                EconomyProvider.removeEconomy(offlinePlayer,amount);
                player.sendMessage(Message.commandEcoRemove(offlinePlayer,amount));
                player.sendMessage(Message.commandBalanceOther(offlinePlayer));
            }else{
                player.sendMessage(Message.commandErrorTargetNull(args[0]));
            }
        }
    }
}