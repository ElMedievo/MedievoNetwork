package org.elmedievo.medievoutils.util;

import org.bukkit.ChatColor;

public class Generic {

    public static String CONSOLE_PREFIX = ChatColor.GOLD + "❖" + ChatColor.DARK_AQUA + "Console";
    public static String BROADCAST_PREFIX = ChatColor.WHITE + "[" + ChatColor.GOLD + "Broadcast" + ChatColor.WHITE + "] " + ChatColor.RESET;

    public static String PM_PREFIX = ChatColor.WHITE + "[" + ChatColor.GOLD + "MSG" + ChatColor.WHITE + "] ";

    public static String WARNING_ICON = ChatColor.YELLOW + "⚠ ";

    private static String chatErrorPrefix = ChatColor.YELLOW + "⚠ " + ChatColor.RED;
    private static String consoleErrorPrefix = ChatColor.RED + "";
    public static String
            NO_GAMEMODE_MATCHED = chatErrorPrefix + "No gamemode matched query.",
            NO_PLAYERS_MATCHED = chatErrorPrefix + "No players matched query.",
            GENERIC_SYNTAX_ERROR = chatErrorPrefix + "Syntax error.",
            TOO_MANY_ARGS = chatErrorPrefix + "Too many arguments.",
            TOO_FEW_ARGS = chatErrorPrefix + "Too few arguments.",
            NO_CONSOLE = consoleErrorPrefix + "You must be a player to execute this command.",
            NO_PERMISSION = chatErrorPrefix + "You do not have permission.",
            INVALID_COORDINATES = chatErrorPrefix + "Invalid coordinates.",
            INVALID_TIME_PERIOD = chatErrorPrefix + "Invalid time period!",
            NOBODY_TO_REPLY_TO = chatErrorPrefix + "You have nobody to reply to.",
            ALREADY_RESTARTING = chatErrorPrefix + "A server restart is already queued!",
            KIT_STATUS_HELP = "\n" + ChatColor.RED + "The status of the kits can only be set to " + ChatColor.YELLOW + "TRUE " + ChatColor.RED + "or " + ChatColor.YELLOW + "FALSE",
            KIT_IS_ALREADY_TRUE = chatErrorPrefix + "The kits are already activated!",
            KIT_IS_ALREADY_FALSE = chatErrorPrefix + "The kits are already deactivated!",
            KIT_STATUS_ENABLED = WARNING_ICON + "Kits have been enabled!",
            KIT_STATUS_DISABLED = WARNING_ICON + "Kits have been disabled!",
            NO_AVAILABLE_KITS = chatErrorPrefix + "There is not kit available.",
            KITS_DISABLED = chatErrorPrefix + "Currently the kits are disabled.",
            KIT_DOESNT_EXIST = chatErrorPrefix + "That kit doesn't exist. Type " + ChatColor.YELLOW + "/kits " + ChatColor.RED + "to see all available kits.";

    private static String successPrefix = ChatColor.GREEN + "";
    public static String
            FLY_TOGGLED_ON = ChatColor.YELLOW + "You can now fly!",
            FLY_TOGGLED_ON_OTHER = ChatColor.YELLOW + "Fly mode enabled for " + ChatColor.RESET,
            FLY_TOGGLED_OFF = ChatColor.YELLOW + "You can no longer fly!",
            FLY_TOGGLED_OFF_OTHER = ChatColor.YELLOW + "Fly mode disabled for " + ChatColor.RESET,
            LOADED_CONFIG = successPrefix + "Plugin configuration was successfully loaded.",
            RELOADED_SCOREBOARD = successPrefix + "Scoreboard reloaded!",
            COUNTDOWNS_CANCELLED = successPrefix + "All countdowns cancelled!";
}
