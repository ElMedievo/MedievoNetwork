package org.elmedievo.medievo.Configuration;

import org.elmedievo.medievo.Medievo;

import static org.elmedievo.medievoapi.Util.Generic.Messages.LOADED_CONFIG;
import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;

public class LoadConfig {
    public static void loadConfig() {
        Medievo.instance.getConfig().options().copyDefaults(true);
        Medievo.instance.saveConfig();
        consoleAlert(LOADED_CONFIG);
    }
}
