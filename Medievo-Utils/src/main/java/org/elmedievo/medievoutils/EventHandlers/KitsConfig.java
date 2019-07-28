package org.elmedievo.medievoutils.EventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.elmedievo.medievoutils.MedievoUtils;

public class KitsConfig implements Listener {

    private final MedievoUtils plugin;

    private KitsConfig(MedievoUtils instance) {
        plugin = instance;
    }

    public static boolean getKitStatus() {
        return MedievoUtils.instance.getConfig().getBoolean("Kit.enable-kits");
    }

    public static void setKitStatus(boolean state) {
        MedievoUtils.instance.getConfig().set("Kit.enable-kits", state);
        MedievoUtils.instance.saveConfig();
    }

    public static void registerKitsConfigEvent() {
        Bukkit.getPluginManager().registerEvents(new KitsConfig(MedievoUtils.instance), MedievoUtils.instance);
    }
}