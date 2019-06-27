package org.elmedievo.medievoapi.Util.Methods;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class Console {
    public static void consoleAlert(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(msg);
    }

    public static void consoleLog(String msg) {
        Logger log = Bukkit.getLogger();
        log.info(msg);
    }
}
