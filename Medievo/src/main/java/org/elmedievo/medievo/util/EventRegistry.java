package org.elmedievo.medievo.util;

import static org.elmedievo.medievo.Commands.Market.Methods.GUIInteractEvent.registerGUIInteractEvent;
import static org.elmedievo.medievo.EventHandlers.BlockBreak.registerBlockBreakEvent;
import static org.elmedievo.medievo.EventHandlers.PlayerChat.registerPlayerChatEvent;
import static org.elmedievo.medievo.EventHandlers.PlayerDeath.registerPlayerDeathEvent;
import static org.elmedievo.medievo.EventHandlers.PlayerJoin.registerPlayerJoinEvent;
import static org.elmedievo.medievo.EventHandlers.PlayerLeave.registerPlayerDisconnectEvent;

public class EventRegistry {
    public static void registerEvents() {
        registerPlayerJoinEvent();
        registerPlayerChatEvent();
        registerPlayerDisconnectEvent();
        registerPlayerDeathEvent();
        registerGUIInteractEvent();
        registerBlockBreakEvent();
    }
}
