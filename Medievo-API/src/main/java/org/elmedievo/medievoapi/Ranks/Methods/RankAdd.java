package org.elmedievo.medievoapi.Ranks.Methods;

import org.bukkit.entity.Player;

import java.util.List;

import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.saveRanksYML;
import static org.elmedievo.medievoapi.Ranks.Methods.CheckRanksExistance.rankExists;

public class RankAdd {
    public static void addRank(Player player, String inputRank) {
        String uuid = player.getUniqueId().toString();
        List currentPlayerRanks = getRanksYML().getList("players." + uuid + ".ranks");
        if (rankExists(inputRank)) {
            if (!currentPlayerRanks.contains(inputRank)) {
                currentPlayerRanks.add(inputRank);
                getRanksYML().set("players." + uuid + ".ranks", currentPlayerRanks);
                saveRanksYML();
            }
        }
    }
}
