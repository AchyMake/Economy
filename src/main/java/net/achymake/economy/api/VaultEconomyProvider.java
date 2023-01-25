package net.achymake.economy.api;

import net.achymake.economy.Economy;
import net.achymake.economy.config.Config;
import net.achymake.economy.config.PlayerConfig;
import net.achymake.economy.settings.Settings;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Collections;
import java.util.List;

public class VaultEconomyProvider implements net.milkbowl.vault.economy.Economy {
    private final Economy eco;
    public VaultEconomyProvider(Economy plugin) {
        this.eco = plugin;
    }
    public boolean isEnabled() {
        return this.eco.isEnabled();
    }
    public String getName() {
        return "Economy";
    }
    public boolean hasBankSupport() {
        return false;
    }
    public int fractionalDigits() {
        return -1;
    }

    public String format(double amount) {
        return Settings.getFormat(amount);
    }
    public String currencyNamePlural() {
        return this.currencyNameSingular();
    }
    public String currencyNameSingular() {
        return Config.get().getString("settings.currency");
    }
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return PlayerConfig.exist(offlinePlayer.getUniqueId());
    }
    public boolean hasAccount(String playerName) {
        return PlayerConfig.exist(Bukkit.getOfflinePlayer(playerName).getUniqueId());
    }
    public boolean hasAccount(String playerName, String worldName) {
        return this.hasAccount(playerName);
    }
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return this.hasAccount(player);
    }
    public double getBalance(OfflinePlayer offlinePlayer) {
        return Settings.getEconomy(offlinePlayer.getUniqueId());
    }
    public double getBalance(String playerName) {
        return Settings.getEconomy(Bukkit.getOfflinePlayer(playerName).getUniqueId());
    }
    public double getBalance(String playerName, String world) {
        return this.getBalance(playerName);
    }
    public double getBalance(OfflinePlayer player, String world) {
        return this.getBalance(player);
    }
    public boolean has(OfflinePlayer offlinePlayer, double amount) {
        return Settings.getEconomy(offlinePlayer.getUniqueId()) >= amount;
    }
    public boolean has(String playerName, double amount) {
        return Settings.getEconomy(Bukkit.getOfflinePlayer(playerName).getUniqueId()) >= amount;
    }
    public boolean has(String playerName, String worldName, double amount) {
        return this.has(playerName, amount);
    }
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return this.has(player, amount);
    }
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
        if (offlinePlayer == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player cannot be null!");
        } else if (amount < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds!");
        } else {
            Settings.removeEconomy(offlinePlayer.getUniqueId(),amount);
            return new EconomyResponse(amount, this.getBalance(offlinePlayer), EconomyResponse.ResponseType.SUCCESS, null);
        }
    }
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (playerName == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player name cannot be null!");
        } else if (amount < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds!");
        } else {
            Settings.removeEconomy(Bukkit.getOfflinePlayer(playerName).getUniqueId(),amount);
            return new EconomyResponse(amount, this.getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, null);
        }
    }
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return this.withdrawPlayer(playerName, amount);
    }
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return this.withdrawPlayer(player, amount);
    }
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
        if (offlinePlayer == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player can not be null.");
        } else if (amount < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot deposit negative funds");
        } else {
            Settings.addEconomy(offlinePlayer.getUniqueId(),amount);
            return new EconomyResponse(amount, this.getBalance(offlinePlayer), EconomyResponse.ResponseType.SUCCESS, null);
        }
    }
    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (playerName == null) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player name can not be null.");
        } else if (amount < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot deposit negative funds");
        } else {
            Settings.addEconomy(Bukkit.getOfflinePlayer(playerName).getUniqueId(),amount);
            return new EconomyResponse(amount, this.getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, null);
        }
    }
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return this.depositPlayer(playerName, amount);
    }
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return this.depositPlayer(player, amount);
    }
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        PlayerConfig.create(offlinePlayer.getUniqueId());
        return true;
    }
    public boolean createPlayerAccount(String playerName) {
        PlayerConfig.create(Bukkit.getOfflinePlayer(playerName).getUniqueId());
        return true;
    }
    public boolean createPlayerAccount(String playerName, String worldName) {
        return this.createPlayerAccount(playerName);
    }
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return this.createPlayerAccount(player);
    }
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse createBank(String name, String player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse deleteBank(String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankBalance(String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankHas(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankWithdraw(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankDeposit(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankOwner(String name, String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankMember(String name, String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public List<String> getBanks() {
        return Collections.emptyList();
    }
}
