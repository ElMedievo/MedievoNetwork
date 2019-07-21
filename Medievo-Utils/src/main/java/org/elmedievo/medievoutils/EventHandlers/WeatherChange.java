package org.elmedievo.medievoutils.EventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.elmedievo.medievoutils.MedievoUtils;

public class WeatherChange implements Listener {
    private final MedievoUtils plugin;

    private WeatherChange(MedievoUtils instance) {
        plugin = instance;
    }

    public static boolean getWeatherLockState() {
        return MedievoUtils.instance.getConfig().getBoolean("General.weather-lock");
    }

    public static void setWeatherLockState(boolean state) {
        MedievoUtils.instance.getConfig().set("General.weather-lock", state);
        MedievoUtils.instance.saveConfig();
    }

    @EventHandler
    public static void onWeatherChange(WeatherChangeEvent event) {
       boolean weatherIsLocked = getWeatherLockState();
        if (weatherIsLocked) {
            event.setCancelled(true);
        }
    }

    public static void registerWeatherChangeEvent() {
        Bukkit.getPluginManager().registerEvents(new WeatherChange(MedievoUtils.instance), MedievoUtils.instance);
    }
}
