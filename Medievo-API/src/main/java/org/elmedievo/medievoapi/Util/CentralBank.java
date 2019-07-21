package org.elmedievo.medievoapi.Util;

import org.elmedievo.medievoapi.MedievoAPI;

import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievoapi.Database.Entries.ClanEntry.clanExistsInSQLDatabase;
import static org.elmedievo.medievoapi.Database.Entries.ClanEntry.createClanInSQLDatabase;
import static org.elmedievo.medievoapi.Database.Getters.ClanLeader.getClanLeaderUUID;
import static org.elmedievo.medievoapi.Util.Generic.Messages.SUCCESSFULLY_FOUNDED_CENTRAL_BANK;
import static org.elmedievo.medievoapi.Util.Methods.Console.consoleAlert;

public class CentralBank {
    public static void generateCentralBank() {
        try {
            originateCentralBank();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void originateCentralBank() throws SQLException {
        String CENTRAL_BANK_NAME = "Medieval Bank";
        if (!clanExistsInSQLDatabase(CENTRAL_BANK_NAME)) {
            UUID CENTRAL_BANK_UUID = UUID.randomUUID();
            MedievoAPI.instance.getConfig().set("central_bank_uuid", CENTRAL_BANK_UUID.toString());
            MedievoAPI.instance.saveConfig();
            String CENTRAL_BANK_LEADER = "-CentralBank";
            createClanInSQLDatabase(CENTRAL_BANK_NAME, CENTRAL_BANK_UUID, CENTRAL_BANK_LEADER);
            consoleAlert(SUCCESSFULLY_FOUNDED_CENTRAL_BANK);
        } else {
            MedievoAPI.instance.getConfig().set("central_bank_uuid", getClanLeaderUUID(CENTRAL_BANK_NAME));
        }
    }
}
