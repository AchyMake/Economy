package net.achymake.economy.config;

public class Files {
    public static void setup(){
        Message.setup();
    }
    public static void reload(){
        Config.reload();
        Message.reload();
        PlayerConfig.reload();
    }
}