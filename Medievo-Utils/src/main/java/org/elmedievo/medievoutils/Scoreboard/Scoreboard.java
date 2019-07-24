package org.elmedievo.medievoutils.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.elmedievo.medievoutils.MedievoUtils;

import java.util.ArrayList;

import static org.elmedievo.medievoapi.Database.Getters.ClanAlfonsos.getClanAlfonsos;
import static org.elmedievo.medievoapi.Database.Getters.PlayerClan.getPlayerClan;
import static org.elmedievo.medievoutils.Configuration.ReloadConfig.reloadConfig;

public class Scoreboard implements Listener {
    private final MedievoUtils plugin;

    private Scoreboard(MedievoUtils instance) {
        plugin = instance;
    }

    private static String rawScoreboardTitle;
    private static String rawScoreboardWeb;
    private static String coloredTitle;
    private static String coloredWeb;

    public static void loadScoreboardParams() {
        rawScoreboardTitle = MedievoUtils.instance.getConfig().getString("Scoreboard.title");
        rawScoreboardWeb = MedievoUtils.instance.getConfig().getString("Scoreboard.server-web");
        coloredTitle = ChatColor.translateAlternateColorCodes('&', rawScoreboardTitle);
        coloredWeb = ChatColor.translateAlternateColorCodes('&', rawScoreboardWeb);
    }

    private void createScoreboard(Player player) {
        ScoreboardAPI scoreboard = ScoreboardAPI.createScore(player);
        scoreboard.setTitle(coloredTitle);
        reloadConfig();
    }

    public static void updateScoreboard(Player player) {
        reloadConfig();
        if (ScoreboardAPI.hasScore(player)) {
            ScoreboardAPI Score = ScoreboardAPI.getByPlayer(player);
            ArrayList<String> ScoreList = new ArrayList<>();
            ScoreList.add(" ");
            ScoreList.add(ChatColor.WHITE + "Server: " + ChatColor.WHITE + "[" + ChatColor.GOLD + "US" + ChatColor.WHITE + "]" + ChatColor.RESET);
            ScoreList.add(" ");
            ScoreList.add(ChatColor.WHITE + "Nick: " + player.getDisplayName() + ChatColor.RESET);
            ScoreList.add(" ");
            ScoreList.add(ChatColor.WHITE + "Clan: ");
            ScoreList.add(ChatColor.GREEN + " »" + ChatColor.WHITE + " Name: " + ChatColor.AQUA + getPlayerClan(player.getUniqueId()));
            ScoreList.add(ChatColor.GREEN + " »" + ChatColor.WHITE + " Alfonsos: " + ChatColor.GOLD + "£" +getClanAlfonsos(getPlayerClan(player.getUniqueId())));
            ScoreList.add(" ");
            ScoreList.add(org.elmedievo.medievoutils.Scoreboard.Scoreboard.coloredWeb);
            Score.setSlotsFromList(ScoreList);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        createScoreboard(player);
        new BukkitRunnable() {
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    updateScoreboard(player);
                }
            }
        }.runTaskTimer(plugin, 0, 10);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public static void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (ScoreboardAPI.hasScore(player)) {
            ScoreboardAPI.removeScore(player);
        }
    }

    public static void registerScoreboardEvents() {
        Bukkit.getPluginManager().registerEvents(new Scoreboard(MedievoUtils.instance), MedievoUtils.instance);
    }
}
