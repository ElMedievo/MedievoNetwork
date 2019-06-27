package org.elmedievo.medievoapi.Database.Setters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class PlayerClan {
    public static void setPlayerClan(UUID uuid, String clanName) {
        try {
            lookUpPlayerClan(uuid, clanName);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpPlayerClan(UUID uuid, String clanName) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("UPDATE " + sql.player_data_table + " SET clan=? WHERE uuid=?");
        statement.setString(1, clanName);
        statement.setString(2, uuid.toString());
        statement.executeUpdate();
    }
}
