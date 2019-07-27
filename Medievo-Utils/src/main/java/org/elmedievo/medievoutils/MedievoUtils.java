package org.elmedievo.medievoutils;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;
import static org.elmedievo.medievoutils.BukkitTasks.scheduleAutoRestart;
import static org.elmedievo.medievoutils.BukkitTasks.setGameParams;
import static org.elmedievo.medievoutils.Commands.Message.MessageReplyQueue.loadMessageReplyQueue;
import static org.elmedievo.medievoutils.Configuration.LoadConfig.loadConfig;
import static org.elmedievo.medievoutils.Scoreboard.Scoreboard.loadScoreboardParams;
import static org.elmedievo.medievoutils.util.CommandRegistry.registerCommands;
import static org.elmedievo.medievoutils.util.EventRegistry.registerEvents;

public final class MedievoUtils extends JavaPlugin {

    public static MedievoUtils instance;

    @Override
    public void onEnable() {
        instance = this;
        consoleAlert(ChatColor.AQUA + "---------------------->" + " Medievo-Utils " + "<----------------------");
        registerCommands();
        registerEvents();
        loadMessageReplyQueue();
        setGameParams();
        loadScoreboardParams();
        loadConfig();
        scheduleAutoRestart();
        consoleAlert(ChatColor.AQUA + "-------------------------------------------------------------");
    }

    @Override
    public void onDisable() {
        consoleAlert(ChatColor.AQUA + "---------------------->" + " Medievo-Utils " + "<----------------------");
        consoleAlert(ChatColor.RED + "Medievo-Utils has been successfully disabled.");
        consoleAlert(ChatColor.AQUA + "-------------------------------------------------------------");
    }
}
