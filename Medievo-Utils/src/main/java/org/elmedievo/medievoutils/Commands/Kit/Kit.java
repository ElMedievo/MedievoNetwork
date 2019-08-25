package org.elmedievo.medievoutils.Commands.Kit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elmedievo.medievoutils.MedievoUtils;

import static org.elmedievo.medievoapi.Kits.CooldownKits.CheckKitsCooldown;
import static org.elmedievo.medievoapi.Kits.CheckKitExistence.kitExists;
import static org.elmedievo.medievoutils.EventHandlers.KitsConfig.*;
import static org.elmedievo.medievoutils.util.Generic.*;

public class Kit implements CommandExecutor {

    private final MedievoUtils plugin;

    private Kit(MedievoUtils instance) { plugin = instance; }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("kit") && commandSender.hasPermission("medievo.utils.kit")){
            Player player = (Player) commandSender;
            if (args.length == 1) {
                if (getKitStatus()){                                                // Kits enabled
                    if (kitExists(args[0])) {                                       // Kit exists
                        CheckKitsCooldown(player, args[0]);                         // Check cooldown
                    } else {                                                        // Kit doesn't exist
                        commandSender.sendMessage(KIT_DOESNT_EXIST);
                    }
                } else {                                                            // Kits disabled
                    commandSender.sendMessage(KITS_DISABLED);
                }
            } else if (args.length == 2) {                                          // Set kit status
                if (args[0].equalsIgnoreCase("status") && commandSender.hasPermission("medievo.utils.changekitstatus")) {
                    if (args[1].equalsIgnoreCase("true")){
                        if (getKitStatus()) {
                            commandSender.sendMessage(KIT_IS_ALREADY_TRUE);
                        } else {
                            setKitStatus(true);
                            commandSender.sendMessage(KIT_STATUS_ENABLED);
                        }
                    } else if (args[1].equalsIgnoreCase("false")) {
                        if (!getKitStatus()){
                            commandSender.sendMessage(KIT_IS_ALREADY_FALSE);
                        } else {
                            setKitStatus(false);
                            commandSender.sendMessage(KIT_STATUS_DISABLED);
                        }
                    } else {
                        commandSender.sendMessage(GENERIC_SYNTAX_ERROR + KIT_STATUS_HELP);
                    }
                } else {
                    commandSender.sendMessage(NO_PERMISSION);
                }
            } else if (args.length > 2) {
                commandSender.sendMessage(TOO_MANY_ARGS);
            } else {
                commandSender.sendMessage(TOO_FEW_ARGS);
            }
        } else {
            commandSender.sendMessage(NO_PERMISSION);
        }
        return true;
    }
    public static void registerKitCommand() {
        MedievoUtils.instance.getCommand("kit").setExecutor(new Kit(MedievoUtils.instance));
    }
}
