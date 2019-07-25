package org.elmedievo.medievoapi.Ranks.Methods;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.saveRanksYML;
import static org.elmedievo.medievoapi.Ranks.Methods.CheckRanksExistance.rankExists;
import static org.elmedievo.medievoapi.Ranks.Methods.RankAdd.ranksYML;

public class RankRemove {
    public static void removeRank(UUID playerUuid, String inputRank) {
        String uuid = playerUuid.toString();
        List<String> currentPlayerRanks = getRanksYML().getStringList("players." + uuid + ".ranks");
        if (currentPlayerRanks.contains(inputRank)) {
            currentPlayerRanks.remove(inputRank);
            getRanksYML().set("players." + uuid + ".ranks", currentPlayerRanks);
            saveRanksYML();
        }
    }

    @SuppressWarnings("deprecation")
    public static void attemptOfflineRankRemoval(String playerName, String inputRank) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
        UUID offlinePlayerUUID = offlinePlayer.getUniqueId();
        if (rankExists(inputRank)) {
            if (Objects.requireNonNull(ranksYML.getConfigurationSection("players")).getKeys(false).contains(offlinePlayerUUID.toString())) {
                List<String> currentPlayerRanks = getRanksYML().getStringList("players." + offlinePlayerUUID.toString() + ".ranks");
                if (Objects.requireNonNull(currentPlayerRanks).contains(inputRank)) {
                    currentPlayerRanks.remove(inputRank);
                    ranksYML.set("players." + offlinePlayerUUID.toString() + ".ranks", currentPlayerRanks);
                    saveRanksYML();
                }
            }
        }
    }
}
