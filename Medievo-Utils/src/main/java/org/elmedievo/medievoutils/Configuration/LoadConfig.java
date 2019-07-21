package org.elmedievo.medievoutils.Configuration;


import org.elmedievo.medievoutils.MedievoUtils;

import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;
import static org.elmedievo.medievoutils.util.Generic.LOADED_CONFIG;

public class LoadConfig {
    public static void loadConfig() {
        MedievoUtils.instance.getConfig().options().copyDefaults(true);
        MedievoUtils.instance.saveConfig();
        consoleAlert(LOADED_CONFIG);
    }
}
