package org.elmedievo.medievoapi.Ranks.Methods;

import java.util.List;
import java.util.UUID;

import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.saveRanksYML;
import static org.elmedievo.medievoapi.Ranks.Methods.CheckRanksExistance.rankExists;

public class RankAdd {
    public static void addRank(UUID playerUuid, String inputRank) {
        String uuid = playerUuid.toString();
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
