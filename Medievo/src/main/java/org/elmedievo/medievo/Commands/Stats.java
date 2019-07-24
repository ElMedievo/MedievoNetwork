package org.elmedievo.medievo.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Medievo;

import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.UUID;

import static org.elmedievo.medievoapi.Database.Entries.PlayerEntry.playerExistsInDatabase;
import static org.elmedievo.medievoapi.Database.Getters.PlayerDeaths.getPlayerDeaths;
import static org.elmedievo.medievoapi.Database.Getters.PlayerKilled.getPlayerKilled;
import static org.elmedievo.medievoapi.Database.Getters.PlayerKills.getPlayerKills;
import static org.elmedievo.medievoapi.Util.Generic.Messages.*;
import static org.elmedievo.medievoapi.Util.Methods.Utility.PlayerIsOnline.evalOnlinePlayer;

public class Stats implements CommandExecutor {

    private final Medievo plugin;

    private Stats(Medievo instance) {
        plugin = instance;
    }

    @SuppressWarnings("deprecation")
    private static void displayPlayerStatsToPlayer(Player playerReceiver, String statsPlayerName) {
        String playerDisplayedName = evalOnlinePlayer(statsPlayerName);
        UUID statsPlayerNameUUID = Bukkit.getOfflinePlayer(statsPlayerName).getUniqueId();

        if (statsPlayerNameUUID == null) {
            playerReceiver.sendMessage(INVALID_PLAYER_NAME);
            return;
        }

        if (playerExistsInDatabase(statsPlayerNameUUID)) {
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            decimalFormat.setRoundingMode(RoundingMode.UP);

            int kills = getPlayerKills(statsPlayerNameUUID);
            int deaths = getPlayerDeaths(statsPlayerNameUUID);
            int killed = getPlayerKilled(statsPlayerNameUUID);
            float kd = (float) kills / deaths;
            float kk = (float) kills / killed;

            if (Float.isNaN(kd)) {
                kd = 0;
            }
            if (Float.isNaN(kk)) {
                kk = 0;
            }

            playerReceiver.sendMessage(ChatColor.RED + "----- " + playerDisplayedName + ChatColor.AQUA  + ChatColor.AQUA + "'s stats" + ChatColor.RED  + " -----" + "\n"
                    + ChatColor.AQUA + "Kills: " + ChatColor.GREEN + kills + "\n"
                    + ChatColor.AQUA + "Deaths: " + ChatColor.DARK_RED + deaths + "\n"
                    + ChatColor.AQUA + "K/D: " + ChatColor.DARK_AQUA + decimalFormat.format(kd) + "\n"
                    + ChatColor.AQUA + "K/K: " + ChatColor.DARK_AQUA + decimalFormat.format(kk) + "\n"
                    + ChatColor.RED + "-----------------------"
            );
        } else {
            playerReceiver.sendMessage(PLAYER_NOT_FOUND);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("stats") && sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                displayPlayerStatsToPlayer(player, player.getName());
            } else if (args.length == 1) {
                displayPlayerStatsToPlayer(player, args[0]);
            } else {
                sender.sendMessage(TOO_MANY_ARGS);
            }
        } else {
            sender.sendMessage(NO_CONSOLE);
        }
        return true;
    }

    public static void registerStatsCommand() {
        Medievo.instance.getCommand("stats").setExecutor(new Stats(Medievo.instance));
    }
}
