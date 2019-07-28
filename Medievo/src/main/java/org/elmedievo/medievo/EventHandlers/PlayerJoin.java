package org.elmedievo.medievo.EventHandlers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventPriority;
import org.elmedievo.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.elmedievo.medievoapi.Database.Entries.PlayerEntry;
import org.elmedievo.medievoutils.util.Parser;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

import static org.elmedievo.medievo.Queues.CreateQueues.chatQueue;
import static org.elmedievo.medievoapi.Database.Entries.PlayerEntry.registerPlayerInRanksDatabase;
import static org.elmedievo.medievoapi.Database.Entries.PlayerEntry.registerPlayerInSQLDatabase;
import static org.elmedievo.medievoapi.Database.Setters.PlayerName.updatePlayerNameInSQL;
import static org.elmedievo.medievoapi.Ranks.DeliverRanks.deliverRanks;
import static org.elmedievo.medievoapi.Util.Generic.Prefixes.JOIN_MESSAGE_PREFIX;
import static org.elmedievo.medievoapi.Util.Generic.Prefixes.JOIN_MESSAGE_SUFFIX;
import static org.elmedievo.medievoapi.Kits.DeliverKit.giveKit;

public class PlayerJoin implements Listener {

    private final Medievo plugin;

    private PlayerJoin(Medievo instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID player_uuid = player.getUniqueId();
        String player_name = player.getName();
        int gold = 0;

        if (!PlayerEntry.playerExistsInDatabase(event.getPlayer().getUniqueId())) {
            player.teleport(parseLocation());
        }

        registerPlayerInSQLDatabase(player_uuid, player_name, gold, "none");
        updatePlayerNameInSQL(player_uuid, player_name);
        registerPlayerInRanksDatabase(player_uuid, player_name);
        deliverRanks(player);

        event.setJoinMessage(JOIN_MESSAGE_PREFIX +  player.getDisplayName() + JOIN_MESSAGE_SUFFIX);
        chatQueue.putIfAbsent(player.getName(), "global");

        if (!event.getPlayer().hasPlayedBefore()) {
            String firstJoinKit = "Starter";
            giveKit(player, firstJoinKit);
            player.sendMessage(ChatColor.GREEN + "\nYou received the kit " + ChatColor.BLUE + firstJoinKit + ChatColor.GREEN + " for being new on the server!");
        }
    }

    public static void registerPlayerJoinEvent() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(Medievo.instance), Medievo.instance);
    }

    private static Location parseLocation(){
        return Parser.parseLocation(Objects.requireNonNull(Medievo.instance.getConfig().getConfigurationSection("spawn")));
    }

}
