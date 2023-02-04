package net.achymake.economy.command.eco.sub;

import net.achymake.economy.api.EconomyProvider;
import net.achymake.economy.command.eco.EcoSubCommand;
import net.achymake.economy.config.Message;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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
        if (player.hasPermission("economy.eco.reset")){
            if (args.length == 2){
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                if (PlayerConfig.exist(offlinePlayer)){
                    EconomyProvider.resetEconomy(offlinePlayer);
                    player.sendMessage(Message.commandEcoReset(offlinePlayer));
                    player.sendMessage(Message.commandBalanceOther(offlinePlayer));
                }else{
                    player.sendMessage(Message.commandErrorTargetNull(args[0]));
                }
            }
        }
    }
}