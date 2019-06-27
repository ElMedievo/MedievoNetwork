package org.elmedievo.medievoapi.Database.Entries;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.elmedievo.medievoapi.MedievoAPI.sql;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.getRanksYML;
import static org.elmedievo.medievoapi.Ranks.CreateRanksYML.saveRanksYML;

public class PlayerEntry {
    public static boolean playerExistsInDatabase(UUID uuid) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.player_data_table + " WHERE uuid=?");
        statement.setString(1, uuid.toString());
        ResultSet results = statement.executeQuery();
        return results.next();
    }

    public static boolean playerNameExistsInSQLDatabase(String name) {
        try {
            if (lookForPlayerNameInSQLDatabase(name)) {
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private static boolean lookForPlayerNameInSQLDatabase(String name) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.player_data_table + " WHERE name=?");
        statement.setString(1, name);
        ResultSet results = statement.executeQuery();
        return results.next();
    }

    public static void registerPlayerInSQLDatabase(final UUID uuid, String name, int gold, String defaultClan) {
        try {
            PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.player_data_table + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            if (!playerExistsInDatabase(uuid)) {
                PreparedStatement insert = Connect.getConnection().prepareStatement("INSERT INTO " + sql.player_data_table + " (uuid,name,gold,clan,kills,killed,deaths) VALUES (?,?,?,?,?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, name);
                insert.setInt(3, gold);
                insert.setString(4, defaultClan);
                insert.setInt(5, 0);
                insert.setInt(6, 0);
                insert.setInt(7, 0);
                insert.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void registerPlayerInRanksDatabase(final UUID uuid, String name) {
        if (!getRanksYML().isConfigurationSection("players." + uuid)) {
            List<String> ranksList = new ArrayList<>();
            ranksList.add("Default");
            getRanksYML().set("players." + uuid.toString() + ".ranks", ranksList);
        }
        getRanksYML().set("players." + uuid.toString() + ".name", name);
        saveRanksYML();
    }
}
