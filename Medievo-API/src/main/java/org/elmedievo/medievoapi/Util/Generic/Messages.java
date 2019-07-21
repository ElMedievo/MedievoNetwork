package org.elmedievo.medievoapi.Util.Generic;

import org.bukkit.ChatColor;

import static org.elmedievo.medievoapi.Util.Generic.Prefixes.chatErrorPrefix;
import static org.elmedievo.medievoapi.Util.Generic.Prefixes.consoleErrorPrefix;
import static org.elmedievo.medievoapi.Util.Generic.Prefixes.successPrefix;

public class Messages {
    /* Error Messages */
    public static String
            NO_CONSOLE = consoleErrorPrefix + "You must be a player to execute this command.",
            SQL_CANNOT_CONNECT = consoleErrorPrefix + "An internal error has occurred while connecting to SQL Database.",
            RANKS_CANNOT_CREATE = consoleErrorPrefix + "An internal error has occurred while generating ranks.xml file.",
            RANKS_DATA_CANNOT_CREATE = consoleErrorPrefix + "An internal error has occurred while generating ranks.yml file.",
            RANKS_DATA_CANNOT_SAVE = consoleErrorPrefix + "An internal error has occurred while saving ranks.yml file.",
            MARKET_DATA_CANNOT_CREATE = consoleErrorPrefix + "An internal error has occurred while generating market.yml file.",
            MARKET_DATA_CANNOT_SAVE = consoleErrorPrefix + "An internal error has occurred while saving market.yml file",
            TOO_MANY_ARGS = chatErrorPrefix + "Too many arguments.",
            TOO_FEW_ARGS = chatErrorPrefix + "Too few arguments.",
            NO_PERMISSION = chatErrorPrefix + "You do not have permission.",
            FEATURE_NOT_IMPLEMENTED = chatErrorPrefix + "Feature not implemented yet.",
            GENERIC_SYNTAX_ERROR = chatErrorPrefix + "Syntax error.",
            NO_PENDANT_INVITE = chatErrorPrefix + "You don't have any pendant clan invitation.",
            CLANS_COMMAND_ERROR = "\n" + ChatColor.RED + "Try using " + ChatColor.YELLOW + "/clan help " + ChatColor.RED + "for more information.",
            RANK_COMMAND_ERROR = "\n" + ChatColor.RED + "Try using " + ChatColor.YELLOW + "/rank help " + ChatColor.RED + "for more information",
            NO_CHAT_MATCHED_QUERY = chatErrorPrefix + "No chat mode matched query.",
            NOT_IN_A_CLAN = chatErrorPrefix + "You are not in a clan!",
            ALREADY_IN_ADMIN_CHAT = chatErrorPrefix + "Your chat is already set to admin mode.",
            ALREADY_IN_GLOBAL_CHAT = chatErrorPrefix + "Your chat is already set to global mode.",
            ALREADY_IN_CLAN_CHAT = chatErrorPrefix  + "Your chat is already set to clan mode.",
            CLAN_NAME_TOO_LONG = chatErrorPrefix + "Clan names must be under 16 characters long.",
            CLANS_NOT_ENABLED = chatErrorPrefix + "Clans are not enabled.",
            CANNOT_DEPOSIT = chatErrorPrefix + "You may not deposit that material! Medieval gold only!",
            CLAN_NOT_FOUND = chatErrorPrefix + "Clan not found.",
            PLAYER_NOT_FOUND = chatErrorPrefix + "Player not found.",
            INVALID_PLAYER_NAME = chatErrorPrefix + "Invalid player name.",
            NOT_ENOUGH_GOLD = chatErrorPrefix + "You don't have enough gold in your bank.",
            WITHDRAW_COMPLETE = chatErrorPrefix + "Withdraw complete.",
            INVALID_AMOUNT = chatErrorPrefix + "Invalid material amount.",
            INVALID_MATERIAL_TYPE = chatErrorPrefix + "Invalid material.",
            ONLY_LEADER_PURCHASABLE = chatErrorPrefix + "Only the clan leader may purchase from the central market.",
            NOT_ENOUGH_ALFONSOS = chatErrorPrefix + "You do not have enough alfonsos to purchase this item.",
            NOT_ENOUGH_GOLD_INGOTS = chatErrorPrefix + "The central bank only accepts gold ingots as payment method. You do not have enough ingots in your bank.";

    /* Success Messages */
    public static String
            LOADED_CONFIG = successPrefix + "Plugin configuration was successfully loaded.",
            RANKS_FILE_FOUND = successPrefix + "Successfully loaded ranks.xml.",
            RANKS_FILE_NOT_FOUND = successPrefix + "ranks.xml file not found. Creating one...",
            RANKS_DATA_FILE_FOUND = successPrefix + "Successfully loaded ranks.yml.",
            RANKS_DATA_FILE_NOT_FOUND = successPrefix + "ranks.yml was not found. Creating...",
            MARKET_DATA_FILE_FOUND = successPrefix + "Successfully loaded the market.yml file",
            MARKET_DATA_FILE_NOT_FOUND = successPrefix + "market.yml was not found. Creating...",
            SQL_CONNECT_SUCCESS = successPrefix + "Connected to SQL database successfully.",
            ADMIN_CHAT_SET = successPrefix + "Chat mode set to: " + ChatColor.RESET + ChatColor.ITALIC + "Admin",
            CLAN_CHAT_SET = successPrefix + "Chat mode set to: " + ChatColor.RESET + ChatColor.ITALIC + "Clan",
            GLOBAL_CHAT_SET = successPrefix + "Chat mode set to: " + ChatColor.RESET + ChatColor.ITALIC + "Global",
            RELOADED_CONFIG = successPrefix + "Configuration reloaded successfully.",
            DEPOSIT_SUCCESS = successPrefix + "Deposit complete",
            SUCCESSFULLY_FOUNDED_CENTRAL_BANK = successPrefix + "Central bank was created successfully.",
            TIME_LOCK_ON = successPrefix + "Time lock has been enabled.",
            TIME_LOCK_OFF = ChatColor.RED + "Time lock has been disabled.";
}
