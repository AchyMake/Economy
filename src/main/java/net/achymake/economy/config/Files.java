package net.achymake.economy.config;

import org.bukkit.Server;

public class Files {
    public static void setup(){
        MessageConfig.setup();
    }
    public static void reload(Server server){
        Config.reload();
        MessageConfig.reload();
        PlayerConfig.reload(server);
    }
}