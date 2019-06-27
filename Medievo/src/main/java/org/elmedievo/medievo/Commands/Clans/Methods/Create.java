package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Objects;

import static org.elmedievo.medievoapi.Database.Entries.ClanEntry.clanExistsInSQLDatabase;
import static org.elmedievo.medievoapi.Database.Entries.ClanEntry.createClanInSQLDatabase;
import static org.elmedievo.medievoapi.Database.Getters.PlayerClan.getPlayerClan;
import static org.elmedievo.medievoapi.Database.Setters.PlayerClan.setPlayerClan;
import static org.elmedievo.medievoapi.Util.Generic.Messages.CLAN_NAME_TOO_LONG;
import static org.elmedievo.medievoapi.Util.Generic.Prefixes.WARNING_ICON;

public class Create {
    public static void foundClanAsPlayer(Player founder, String clanName) {
        if (clanName.toCharArray().length > 16) {
            founder.sendMessage(CLAN_NAME_TOO_LONG);
            return;
        }
        try {
            String playerActualClan = getPlayerClan(founder.getUniqueId());
            if (!clanExistsInSQLDatabase(clanName)) {
                if (Objects.requireNonNull(playerActualClan).equals("none")) {
                    createClanInSQLDatabase(clanName, founder.getUniqueId(), founder.getName());
                    setPlayerClan(founder.getUniqueId(), clanName);
                    founder.sendMessage(ChatColor.GREEN + "You have successfully founded the " + ChatColor.AQUA + clanName + ChatColor.GREEN + " clan!");
                    Bukkit.broadcastMessage(founder.getDisplayName() + ChatColor.GREEN + " has founded the clan: " + ChatColor.AQUA + clanName);
                } else {
                    founder.sendMessage(WARNING_ICON + ChatColor.RED + "You already are in clan!");
                }
            } else {
                founder.sendMessage(WARNING_ICON + ChatColor.AQUA + clanName + ChatColor.RED + " already exists!");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
