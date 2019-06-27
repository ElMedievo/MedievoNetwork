package org.elmedievo.medievoapi.Database.Setters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievoapi.Database.Getters.PlayerKills.getPlayerKills;
import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class KillToPlayer {
    public static void addKillToPlayer(UUID uuid) {
        try {
            lookUpPlayerKills(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpPlayerKills(UUID uuid) throws SQLException {
        int actualKills = getPlayerKills(uuid);
        PreparedStatement statement = Connect.getConnection().prepareStatement("UPDATE " + sql.player_data_table + " SET kills=? WHERE uuid=?");
        statement.setInt(1, actualKills + 1);
        statement.setString(2, uuid.toString());
        statement.executeUpdate();
    }
}
