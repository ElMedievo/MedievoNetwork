package org.elmedievo.medievoapi;

import org.bukkit.plugin.java.JavaPlugin;
import org.elmedievo.medievoapi.Database.Connect;

import static org.elmedievo.medievoapi.Ranks.CreateRanksXML.createRanksXMLFile;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.createRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.saveRanksYML;
import static org.elmedievo.medievoapi.Util.CentralBank.generateCentralBank;

public final class MedievoAPI extends JavaPlugin {
    public static Connect sql = new Connect();
    public static MedievoAPI instance;

    @Override
    public void onEnable() {
        instance = this;
        sql.connectSQLDatabase();

        generateCentralBank();
        loadConfigurations();
    }

    @Override
    public void onDisable() {

    }

    private void loadConfigurations() {
        /* Main API config file */
        getConfig().options().copyDefaults(true);
        saveConfig();
        /* Ranks YML Data */
        createRanksYML();
        getRanksYML().options().copyDefaults(true);
        saveRanksYML();
        /* Ranks XML Data */
        createRanksXMLFile();
    }
}
