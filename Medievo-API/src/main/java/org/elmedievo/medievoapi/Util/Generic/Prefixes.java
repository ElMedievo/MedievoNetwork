package org.elmedievo.medievoapi.Util.Generic;

import org.bukkit.ChatColor;

public class Prefixes {
    public static String WARNING_ICON = ChatColor.YELLOW + "⚠ ";
    static String chatErrorPrefix = ChatColor.YELLOW + "⚠ " + ChatColor.RED;
    static String consoleErrorPrefix = ChatColor.RED + "";
    static String successPrefix = ChatColor.GREEN + "";

    public static String
            CONSOLE_PREFIX = ChatColor.GOLD + "❖" + ChatColor.DARK_AQUA + "Console" + ChatColor.RESET,
            JOIN_MESSAGE_PREFIX = ChatColor.GREEN + "» " + ChatColor.RESET,
            LEAVE_MESSAGE_PREFIX = ChatColor.RED + "« " + ChatColor.RESET,
            ADMIN_CHAT_PREFIX = ChatColor.WHITE + "[" + ChatColor.GOLD + "A" + ChatColor.RESET + "] ";
    public static String
            JOIN_MESSAGE_SUFFIX = "",
            LEAVE_MESSAGE_SUFFIX = "";
}
