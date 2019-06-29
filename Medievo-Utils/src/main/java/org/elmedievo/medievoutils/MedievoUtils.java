package org.elmedievo.medievoutils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.elmedievo.medievoutils.util.Methods.ConsoleAlerts;

import static org.elmedievo.medievoutils.BukkitTasks.setGameParams;
import static org.elmedievo.medievoutils.Commands.Message.MessageReplyQueue.loadMessageReplyQueue;
import static org.elmedievo.medievoutils.Configuration.LoadConfig.loadConfig;
import static org.elmedievo.medievoutils.Scoreboard.Scoreboard.loadScoreboardParams;
import static org.elmedievo.medievoutils.util.CommandRegistry.registerCommands;
import static org.elmedievo.medievoutils.util.EventRegistry.registerEvents;
/*Test commit*/
public final class MedievoUtils extends JavaPlugin {

    public static MedievoUtils instance;

    @Override
    @SuppressWarnings("deprecation")
    public void onEnable() {
        instance = this;
        ConsoleAlerts.sendConsoleAlert(ChatColor.GREEN + "-----" + " Medievo-Utils " + "-----");
        registerCommands();
        registerEvents();
        loadMessageReplyQueue();
        setGameParams();
        loadScoreboardParams();
        loadConfig();
        ConsoleAlerts.sendConsoleAlert(ChatColor.GREEN + "Medievo-Utils was successfully enabled");
        ConsoleAlerts.sendConsoleAlert(ChatColor.GREEN + "-------------------------");
    }

    @Override
    public void onDisable() {

    }
}
