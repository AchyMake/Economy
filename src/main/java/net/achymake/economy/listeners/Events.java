package net.achymake.economy.listeners;

import net.achymake.economy.Economy;
import net.achymake.economy.listeners.connection.PlayerJoin;
import net.achymake.economy.listeners.connection.PlayerLogin;

public class Events {
    public static void start(Economy plugin){
        new PlayerJoin(plugin);
        new PlayerLogin(plugin);
    }
}