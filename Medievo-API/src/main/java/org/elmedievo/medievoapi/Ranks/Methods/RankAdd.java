package org.elmedievo.medievoapi.Ranks.Methods;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.saveRanksYML;
import static org.elmedievo.medievoapi.Ranks.Methods.CheckRanksExistance.rankExists;

public class RankAdd {

    static FileConfiguration ranksYML = getRanksYML();

    public static void addRank(UUID playerUuid, String inputRank) {
        String uuid = playerUuid.toString();
        List<String> currentPlayerRanks = getRanksYML().getStringList("players." + uuid + ".ranks");
        if (rankExists(inputRank)) {
            if (!currentPlayerRanks.contains(inputRank)) {
                currentPlayerRanks.add(inputRank);
                getRanksYML().set("players." + uuid + ".ranks", currentPlayerRanks);
                saveRanksYML();
            }
        }
    }

    @SuppressWarnings("deprecation")
    public static void attemptOfflineRankAdd(String attPlayerName, String inputRank) {
       if (attPlayerName.contains("@")) {
           String playerName = attPlayerName.replaceAll("@", "");
           OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
           UUID offlinePlayerUUID = offlinePlayer.getUniqueId();
           List<String> currentPlayerRanks = getRanksYML().getStringList("players." + offlinePlayerUUID.toString() + ".ranks");

           if (rankExists(inputRank)) {
               if (Objects.requireNonNull(ranksYML.getConfigurationSection("players")).getKeys(false).contains(offlinePlayerUUID.toString())) {
                   if (!Objects.requireNonNull(currentPlayerRanks).contains(inputRank)) {
                       currentPlayerRanks.add(inputRank);
                       ranksYML.set("players." + offlinePlayerUUID.toString() + ".ranks", currentPlayerRanks);
                       saveRanksYML();
                   }
               }
           }
       }
    }
}
