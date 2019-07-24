package org.elmedievo.medievoapi.Ranks.Methods;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.saveRanksYML;

public class RankRemove {
    public static void removeRank(UUID playerUuid, String inputRank) {
        String uuid = playerUuid.toString();
        List currentPlayerRanks = getRanksYML().getList("players." + uuid + ".ranks");
        if (currentPlayerRanks.contains(inputRank)) {
            currentPlayerRanks.remove(inputRank);
            getRanksYML().set("players." + uuid + ".ranks", currentPlayerRanks);
            saveRanksYML();
        }
    }
}
