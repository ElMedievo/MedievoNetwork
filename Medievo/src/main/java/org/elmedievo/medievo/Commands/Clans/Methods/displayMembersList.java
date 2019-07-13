package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.Objects;

import static org.elmedievo.medievoapi.Database.Getters.ClanMembersToList.getClanMembers;
import static org.elmedievo.medievoapi.Util.Methods.Utility.PlayerIsOnline.evalOnlinePlayer;

class displayMembersList {
    static String displayClanMemberList(String clan) {
        List<String> members = getClanMembers(clan);

        StringBuilder memberListMessage = new StringBuilder();
        Objects.requireNonNull(members).forEach(member -> memberListMessage.append(ChatColor.GRAY + "- ").append(evalOnlinePlayer(member)).append("\n"));

        return memberListMessage.toString();
    }

    static List<String> getClanMembersList(String clan) {
        return getClanMembers(clan);
    }
}
