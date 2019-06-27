package org.elmedievo.medievoapi.Ranks.Methods;

import org.bukkit.entity.Player;

import java.util.List;

import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.saveRanksYML;

public class RankRemove {
    public static void removeRank(Player player, String inputRank) {
        String uuid = player.getUniqueId().toString();
        List currentPlayerRanks = getRanksYML().getList("players." + uuid + ".ranks");
        if (currentPlayerRanks.contains(inputRank)) {
            currentPlayerRanks.remove(inputRank);
            getRanksYML().set("players." + uuid + ".ranks", currentPlayerRanks);
            saveRanksYML();
        }
    }
}
