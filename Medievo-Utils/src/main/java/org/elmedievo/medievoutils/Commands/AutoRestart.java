package org.elmedievo.medievoutils.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.elmedievo.medievoutils.MedievoUtils;

import static org.elmedievo.medievoapi.Util.Generic.Messages.*;

public class AutoRestart implements CommandExecutor {

    private final MedievoUtils plugin;

    private AutoRestart(MedievoUtils instance) {
        plugin = instance;
    }

    private boolean inputIsInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
        if (command.getName().equalsIgnoreCase("autorestart") && sender.hasPermission("medievo.utils.autorestart")) {
            if (args.length > 1) {
                sender.sendMessage(TOO_MANY_ARGS);
            } else if (args.length == 0) {
                sender.sendMessage(TOO_FEW_ARGS);
            }
            if (inputIsInt(args[0])) {
                int inputNumber = Integer.parseInt(args[0]);
                MedievoUtils.instance.getConfig().set("General.auto-restart", inputNumber);
                MedievoUtils.instance.saveConfig();
            } else {
                sender.sendMessage(NUMBER_STRING_EXCEPTION);
            }
        }
        return true;
    }

    public static void registerAutoRestartCommand() {
        MedievoUtils.instance.getCommand("autorestart").setExecutor(new AutoRestart(MedievoUtils.instance));
    }
}
