package org.elmedievo.medievoapi;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.elmedievo.medievoapi.Database.Connect;

import static org.elmedievo.medievoapi.Ranks.CreateRanksXML.createRanksXMLFile;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.createRanksYML;
import static org.elmedievo.medievoapi.Kits.CreateKitsXML.createKitsXMLFile;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.saveRanksYML;
import static org.elmedievo.medievoapi.Util.CentralBank.generateCentralBank;
import static org.elmedievo.medievoapi.Util.Generic.Messages.LOADED_CONFIG;
import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;

public final class MedievoAPI extends JavaPlugin {
    public static Connect sql = new Connect();
    public static MedievoAPI instance;

    @Override
    public void onEnable() {
        instance = this;
        consoleAlert(ChatColor.AQUA + "---------------------->" + " Medievo-API " + "<----------------------");
        sql.connectSQLDatabase();
        generateCentralBank();
        loadConfigurations();
        consoleAlert(ChatColor.AQUA + "-----------------------------------------------------------");
    }

    @Override
    public void onDisable() {
        consoleAlert(ChatColor.AQUA + "---------------------->" + " Medievo-API " + "<----------------------");
        consoleAlert(ChatColor.RED + "Medievo-API has been successfully disabled.");
        consoleAlert(ChatColor.AQUA + "-----------------------------------------------------------");
    }

    private void loadConfigurations() {
        /* Main API config file */
        getConfig().options().copyDefaults(true);
        saveConfig();
        consoleAlert(LOADED_CONFIG);
        /* Ranks YML Data */
        createRanksYML();
        getRanksYML().options().copyDefaults(true);
        saveRanksYML();
        /* Ranks XML Data */
        createRanksXMLFile();
        /* Kits XML Data */
        createKitsXMLFile();
    }
}
