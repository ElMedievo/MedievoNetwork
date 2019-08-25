package org.elmedievo.medievoutils.Commands.Kit;

import com.google.common.base.Joiner;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.elmedievo.medievoutils.MedievoUtils;

import java.util.List;

import static org.elmedievo.medievoapi.Kits.CheckKitExistence.getKitsList;
import static org.elmedievo.medievoutils.util.Generic.TOO_MANY_ARGS;
import static org.elmedievo.medievoutils.util.Generic.NO_AVAILABLE_KITS;

public class Kits implements CommandExecutor {

    private final MedievoUtils plugin;

    private Kits(MedievoUtils instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("kits")) {
            if (args.length == 0) {
                if (getKitsList() == null) {
                    commandSender.sendMessage(NO_AVAILABLE_KITS);
                } else {
                    List<String> KitsList = getKitsList();
                    String Kits = Joiner.on(", ").join(KitsList);
                    commandSender.sendMessage(ChatColor.YELLOW + "\n Available kits: " + ChatColor.GREEN + Kits);
                }
            } else {
                commandSender.sendMessage(TOO_MANY_ARGS);
            }
        }
        return true;
    }

    public static void registerKitsCommand() {
        MedievoUtils.instance.getCommand("kits").setExecutor(new Kits(MedievoUtils.instance));
    }

}
