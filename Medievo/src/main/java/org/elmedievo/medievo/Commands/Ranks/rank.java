package org.elmedievo.medievo.Commands.Ranks;

import org.bukkit.configuration.ConfigurationSection;
import org.elmedievo.medievo.Commands.TabComplete.RanksTabComplete;
import org.elmedievo.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elmedievo.medievoapi.Ranks.CreateRanksYML;

import java.util.UUID;

import static org.elmedievo.medievo.Commands.Ranks.Methods.Help.displayRankHelpMenuToPlayer;
import static org.elmedievo.medievoapi.Ranks.Methods.CheckRanksExistance.rankExists;
import static org.elmedievo.medievoapi.Ranks.Methods.RankAdd.addRank;
import static org.elmedievo.medievoapi.Ranks.Methods.RankRemove.removeRank;
import static org.elmedievo.medievoapi.Util.Generic.Messages.*;
import static org.elmedievo.medievoapi.Util.Generic.Prefixes.CONSOLE_PREFIX;
import static org.elmedievo.medievoapi.Util.Generic.Prefixes.WARNING_ICON;

public class rank implements CommandExecutor {

    private final Medievo plugin;

    private rank(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rank") && sender.hasPermission("medievo.rank")) {
            if (args.length == 3) {
                String givenPlayerName = args[1];
                String givenRank = args[2];

                UUID givenUuid = getPlayerUuidByName(givenPlayerName);
                if (givenUuid != null) {
                    if (rankExists(givenRank)) {
                        String giver;
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            giver = player.getDisplayName();
                        } else {
                            giver = CONSOLE_PREFIX;
                        }
                        switch (args[0]) {
                            case "add":
                                addRank(givenUuid, givenRank);
                                sender.sendMessage(ChatColor.GREEN + "Rank successfully added");
                                if (Bukkit.getPlayer(givenUuid).isOnline()) Bukkit.getPlayer(givenUuid).sendMessage(ChatColor.GREEN + "You have been granted the " + ChatColor.AQUA + givenRank + ChatColor.GREEN + " rank by: " + ChatColor.RESET + giver);
                                break;
                            case "remove":
                                removeRank(givenUuid, givenRank);
                                sender.sendMessage(ChatColor.RED + "Rank successfully removed");
                                if (Bukkit.getPlayer(givenUuid).isOnline()) Bukkit.getPlayer(givenUuid).sendMessage(ChatColor.RED + "You have been demoted from " + ChatColor.AQUA + givenRank + ChatColor.RED + " by: " + ChatColor.RESET + giver);
                                break;
                            default:
                                sender.sendMessage(GENERIC_SYNTAX_ERROR + RANK_COMMAND_ERROR);
                                break;
                        }
                    } else {
                        sender.sendMessage(WARNING_ICON + ChatColor.AQUA + givenRank + ChatColor.RED + " is not a rank.");
                    }
                } else {
                    sender.sendMessage(WARNING_ICON + ChatColor.DARK_AQUA + givenPlayerName + ChatColor.RED + " is currently Offline or is not a valid player.");
                }
            } else if (args.length == 1) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    switch (args[0]) {
                        case "help":
                            displayRankHelpMenuToPlayer(player);
                            break;
                        default:
                            sender.sendMessage(TOO_FEW_ARGS + RANK_COMMAND_ERROR);
                            break;
                    }
                }
            } else if (args.length <= 2) {
                sender.sendMessage(TOO_FEW_ARGS + RANK_COMMAND_ERROR);
            } else {
                sender.sendMessage(TOO_MANY_ARGS + RANK_COMMAND_ERROR);
            }

        } else {
            sender.sendMessage(NO_PERMISSION);
        }
        return true;
    }

    public static void registerRankCommand() {
        Medievo.instance.getCommand("rank").setExecutor(new rank(Medievo.instance));
        Medievo.instance.getCommand("rank").setTabCompleter(new RanksTabComplete(Medievo.instance));
    }

    private static UUID getPlayerUuidByName(String name){
        if (Bukkit.getPlayer(name) != null) return Bukkit.getPlayer(name).getUniqueId();
        ConfigurationSection players = CreateRanksYML.getRanksYML().getConfigurationSection("players");
        assert players != null;
        for (String uuid : players.getKeys(false)) {
            if (players.getString(uuid + ".name") == null) continue;
            if (players.getString(uuid + ".name").equalsIgnoreCase(name)) return UUID.fromString(uuid);
        }
        return null;
    }

}
