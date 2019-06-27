package org.elmedievo.medievo;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import static org.elmedievo.medievo.Commands.Market.Methods.CreateMarketData.createMarketDataYML;
import static org.elmedievo.medievo.Commands.TabComplete.Resources.LoadResources.loadTabCompleteArguments;
import static org.elmedievo.medievo.Configuration.LoadConfig.loadConfig;
import static org.elmedievo.medievo.EventHandlers.BlockBreak.loadSmelterMaterials;
import static org.elmedievo.medievo.Queues.CreateQueues.createQueues;
import static org.elmedievo.medievo.util.CentralBank.generateCentralBank;
import static org.elmedievo.medievo.util.CommandRegistry.registerCommands;
import static org.elmedievo.medievo.util.EventRegistry.registerEvents;
import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;
import static org.elmedievo.medievoapi.Util.Methods.Console.consoleLog;

public final class Medievo extends JavaPlugin {

    public static Medievo instance;

    private void loadConfigurationManager() {
        createMarketDataYML();
    }

    @Override
    public void onEnable() {
        instance = this;
        consoleAlert(ChatColor.GREEN + "-----" + " Medievo " + "-----");
        loadConfig();
        registerCommands();
        loadTabCompleteArguments();
        registerEvents();
        loadConfigurationManager();
        createQueues();
        loadSmelterMaterials();
        generateCentralBank();
        consoleAlert(ChatColor.GREEN + "-------------------");
    }

    @Override
    public void onDisable() {
        consoleLog("The Medieval plugin has been disabled");
    }
}
