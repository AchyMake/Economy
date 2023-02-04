package net.achymake.economy.version;

import net.achymake.economy.Economy;
import net.achymake.economy.config.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private final Economy plugin;
    private final int resourceId;
    public UpdateChecker(Economy plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }
    public void getVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId)).openStream();
                Scanner scanner = new Scanner(inputStream);
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                    scanner.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                plugin.sendMessage(e.getMessage());
            }
        });
    }
    public static void getUpdate(Economy plugin){
        if (plugin.getConfig().getBoolean("notify-update.enable")) {
            (new UpdateChecker(plugin, 107261)).getVersion((latest) -> {
                if (plugin.getDescription().getVersion().equalsIgnoreCase(latest)) {
                    plugin.sendMessage("You are using the latest version");
                } else {
                    plugin.sendMessage("New update: " + latest);
                    plugin.sendMessage("Current version: " + plugin.getDescription().getVersion());
                }
            });
        }
    }
    public static void sendMessage(Player player){
        if (Economy.instance.getConfig().getBoolean("notify-update.enable")) {
            (new UpdateChecker(Economy.instance, 107261)).getVersion((latest) -> {
                if (!Economy.instance.getDescription().getVersion().equalsIgnoreCase(latest)) {
                    player.sendMessage(Message.color("&6"+Economy.instance.getName()+" Update:"));
                    player.sendMessage(Message.color("&6new release: &f" + latest));
                    player.sendMessage(Message.color("&6current: &f" + Economy.instance.getDescription().getVersion()));
                }
            });
        }
    }
}