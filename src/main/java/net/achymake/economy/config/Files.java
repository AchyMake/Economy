package net.achymake.economy.config;

public class Files {
    public static void setup(){
        MessageConfig.setup();
    }
    public static void reload(){
        Config.reload();
        PlayerConfig.reload();
    }
}