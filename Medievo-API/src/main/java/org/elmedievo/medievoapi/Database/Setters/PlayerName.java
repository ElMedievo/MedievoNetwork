package org.elmedievo.medievoapi.Database.Setters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class PlayerName {
    public static void updatePlayerNameInSQL(UUID uuid, String name) {
        try {
            lookupPlayerName(uuid, name);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookupPlayerName(UUID uuid, String name) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("UPDATE " + sql.player_data_table + " SET name=? WHERE uuid=?");
        statement.setString(1, name);
        statement.setString(2, uuid.toString());
        statement.executeUpdate();

        PreparedStatement statement1 = Connect.getConnection().prepareStatement("UPDATE " + sql.clans_data_table + " SET leader_name=? WHERE leader_uuid=?");
        statement1.setString(1, name);
        statement1.setString(2, uuid.toString());
        statement1.executeUpdate();
    }
}
