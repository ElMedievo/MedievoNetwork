package org.elmedievo.medievoapi.Database.Getters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class PlayerClan {
    public static String getPlayerClan(UUID uuid) {
        try {
            return lookupPlayerClan(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static String lookupPlayerClan(UUID uuid) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.player_data_table + " WHERE uuid=?");
        statement.setString(1, uuid.toString());
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getString("clan");
    }
}
