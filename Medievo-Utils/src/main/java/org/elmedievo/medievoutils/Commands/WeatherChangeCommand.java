package org.elmedievo.medievoutils.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.elmedievo.medievoutils.MedievoUtils;

import static org.elmedievo.medievoapi.Util.Generic.Messages.*;

public class WeatherChangeCommand implements CommandExecutor {

    private final MedievoUtils plugin;

    private WeatherChangeCommand(MedievoUtils instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("lockweather") && commandSender.hasPermission("medievo.utils.lockweather")) {
            if (args.length == 0) {
                boolean weatherIsLocked = org.elmedievo.medievoutils.EventHandlers.WeatherChange.getWeatherLockState();
                if (weatherIsLocked) {
                    org.elmedievo.medievoutils.EventHandlers.WeatherChange.setWeatherLockState(false);
                    commandSender.sendMessage(TIME_LOCK_OFF);
                } else {
                    org.elmedievo.medievoutils.EventHandlers.WeatherChange.setWeatherLockState(true);
                    commandSender.sendMessage(TIME_LOCK_ON);
                }
            } else {
                commandSender.sendMessage(TOO_MANY_ARGS);
            }
        } else {
            commandSender.sendMessage(NO_PERMISSION);
        }
        return true;
    }

    public static void registerWeatherChangeCommand() {
        MedievoUtils.instance.getCommand("lockweather").setExecutor(new WeatherChangeCommand(MedievoUtils.instance));
    }
}
