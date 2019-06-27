package org.elmedievo.medievo.Commands.Clans;

import org.elmedievo.medievo.Commands.TabComplete.ClanTabComplete;
import org.elmedievo.medievo.Medievo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static org.elmedievo.medievo.Commands.Clans.Methods.Create.foundClanAsPlayer;
import static org.elmedievo.medievo.Commands.Clans.Methods.Deposit.depositGoldIntoClan;
import static org.elmedievo.medievo.Commands.Clans.Methods.Disband.destroyClanAsPlayer;
import static org.elmedievo.medievo.Commands.Clans.Methods.Help.displayClanHelpMenuToPlayer;
import static org.elmedievo.medievo.Commands.Clans.Methods.Info.getClanInfoOther;
import static org.elmedievo.medievo.Commands.Clans.Methods.Info.getClanInfoSelf;
import static org.elmedievo.medievo.Commands.Clans.Methods.Invite.createPlayerToPlayerInvite;
import static org.elmedievo.medievo.Commands.Clans.Methods.Leave.removePlayerFromClan;
import static org.elmedievo.medievo.Commands.Clans.Methods.Remove.playerRemovePlayerFromClan;
import static org.elmedievo.medievo.Commands.Clans.Methods.Withdraw.withdrawGoldFromClan;
import static org.elmedievo.medievo.Commands.Clans.Methods.displayClansList.sendClansListToPlayer;
import static org.elmedievo.medievo.util.Methods.ClansEnabled.clansAreEnabled;
import static org.elmedievo.medievoapi.Util.Generic.Messages.*;

public class clan implements CommandExecutor {

    private final Medievo plugin;

    private clan(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("clan")) {
            if (clansAreEnabled()) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    UUID player_uuid = player.getUniqueId();
                    String player_name = player.getName();
                    if (args.length == 3) {
                        switch (args[0]) {
                            case "withdraw":
                                String material = args[1].toUpperCase();
                                String amount = args[2];
                                withdrawGoldFromClan(player, material, amount);
                                break;
                        }
                    } else if (args.length == 2) {
                        String clanName = args[1];
                        String invited_name = args[1];
                        String removed = args[1];
                        switch (args[0]) {
                            case "create":
                                foundClanAsPlayer(player, clanName);
                                break;
                            case "disband":
                                destroyClanAsPlayer(player, clanName);
                                break;
                            case "invite":
                                createPlayerToPlayerInvite(player_name, invited_name);
                                break;
                            case "setleader":
                                sender.sendMessage(FEATURE_NOT_IMPLEMENTED);
                                break;
                            case "remove":
                                playerRemovePlayerFromClan(player.getName(), removed);
                                break;
                            case "info":
                                getClanInfoOther(player, clanName);
                                break;
                            case "withdraw":
                                String material = args[1].toUpperCase();
                                withdrawGoldFromClan(player, material, "1");
                                break;
                            default:
                                sender.sendMessage(GENERIC_SYNTAX_ERROR + CLANS_COMMAND_ERROR);
                        }
                    } else if (args.length == 1) {
                        switch (args[0]) {
                            case "leave":
                                removePlayerFromClan(player);
                                break;
                            case "help":
                                displayClanHelpMenuToPlayer(player);
                                break;
                            case "deposit":
                                depositGoldIntoClan(player);
                                break;
                            case "info":
                                getClanInfoSelf(player);
                                break;
                            case "list":
                                sendClansListToPlayer(player);
                                break;
                            default:
                                sender.sendMessage(GENERIC_SYNTAX_ERROR + CLANS_COMMAND_ERROR);
                                break;
                        }
                    } else {
                        sender.sendMessage(GENERIC_SYNTAX_ERROR + CLANS_COMMAND_ERROR);
                    }
                } else {
                    sender.sendMessage(NO_CONSOLE);
                }
            } else {
                sender.sendMessage(CLANS_NOT_ENABLED);
            }
        }
        return true;
    }

    public static void registerClanCommand() {
        Medievo.instance.getCommand("clan").setExecutor(new clan(Medievo.instance));
        Medievo.instance.getCommand("clan").setTabCompleter(new ClanTabComplete(Medievo.instance));
    }
}
