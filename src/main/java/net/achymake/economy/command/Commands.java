package net.achymake.economy.command;

import net.achymake.economy.Economy;
import net.achymake.economy.command.balance.BalanceCommand;
import net.achymake.economy.command.eco.EcoCommand;
import net.achymake.economy.command.pay.PayCommand;
import net.achymake.economy.command.reload.EconomyCommand;

public class Commands {
    public static void start(Economy plugin){
        plugin.getCommand("economy").setExecutor(new EconomyCommand());
        plugin.getCommand("eco").setExecutor(new EcoCommand());
        plugin.getCommand("balance").setExecutor(new BalanceCommand());
        plugin.getCommand("pay").setExecutor(new PayCommand());
    }
}