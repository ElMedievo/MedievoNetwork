package org.elmedievo.medievo.Commands.Ranks.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help {
    public static void displayRankHelpMenuToPlayer(Player player) {
        player.sendMessage("\n" + ChatColor.RED + "########## " + ChatColor.AQUA + ChatColor.BOLD + "RANK HELP" + ChatColor.RESET + ChatColor.RED + " ##########" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/rank add 'player' 'rank'" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/rank remove 'player' 'rank'" + "\n"
                + ChatColor.RED + "###############################" + "\n"
        );
    }
}
