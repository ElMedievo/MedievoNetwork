package org.elmedievo.medievoapi.Database.Setters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievoapi.Database.Getters.PlayerDeaths.getPlayerDeaths;
import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class DeathToPlayer {
    public static void addDeathToPlayer(UUID uuid) {
        try {
            lookUpPlayerDeaths(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpPlayerDeaths(UUID uuid) throws SQLException {
        int actualDeaths = getPlayerDeaths(uuid);
        PreparedStatement statement = Connect.getConnection().prepareStatement("UPDATE " + sql.player_data_table + " SET deaths=? WHERE uuid=?");
        statement.setInt(1, actualDeaths + 1);
        statement.setString(2, uuid.toString());
        statement.executeUpdate();
    }
}
