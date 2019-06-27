package org.elmedievo.medievoapi.Ranks;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static org.elmedievo.medievoapi.Util.Generic.Messages.*;
import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;
import static org.elmedievo.medievoapi.Util.Methods.Utility.GetPluginFolder.getAPIFolder;

public class CreateRanksYML {
    private static File ranksDataYMLFile;
    private static FileConfiguration ranksDataYMLConfiguration;

    public static void createRanksYML() {
        try {
            generateRanksDataYML();
            if (!ranksDataYMLFile.exists()) {
                consoleAlert(RANKS_DATA_FILE_NOT_FOUND);
                ranksDataYMLFile.createNewFile();
            } else {
                consoleAlert(RANKS_DATA_FILE_FOUND);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            consoleAlert(RANKS_DATA_CANNOT_CREATE);
        }
    }

    private static void generateRanksDataYML() {
        ranksDataYMLFile = new File(getAPIFolder(), "ranks.yml");
        ranksDataYMLConfiguration = YamlConfiguration.loadConfiguration(ranksDataYMLFile);
    }

    public static FileConfiguration getRanksYML() {
        return ranksDataYMLConfiguration;
    }

    public static void saveRanksYML() {
        try {
            ranksDataYMLConfiguration.save(ranksDataYMLFile);
        } catch (IOException exception) {
            exception.printStackTrace();
            consoleAlert(RANKS_DATA_CANNOT_SAVE);
        }
    }

    public static void reloadRanksYML() {
        ranksDataYMLConfiguration = YamlConfiguration.loadConfiguration(ranksDataYMLFile);
    }
}
