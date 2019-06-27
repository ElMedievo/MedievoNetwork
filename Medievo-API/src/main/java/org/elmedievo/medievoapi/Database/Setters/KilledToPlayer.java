package org.elmedievo.medievoapi.Database.Setters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievoapi.Database.Getters.PlayerKilled.getPlayerKilled;
import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class KilledToPlayer {
    public static void addKilledToPlayer(UUID uuid) {
        try {
            lookUpPlayerKilled(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpPlayerKilled(UUID uuid) throws SQLException {
        int actualKilled = getPlayerKilled(uuid);
        PreparedStatement statement = Connect.getConnection().prepareStatement("UPDATE " + sql.player_data_table + " SET killed=? WHERE uuid=?");
        statement.setInt(1, actualKilled + 1);
        statement.setString(2, uuid.toString());

        statement.executeUpdate();
    }
}
