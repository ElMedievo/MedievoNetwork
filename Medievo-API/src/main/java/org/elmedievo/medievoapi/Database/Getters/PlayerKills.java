package org.elmedievo.medievoapi.Database.Getters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class PlayerKills {
    public static int getPlayerKills(UUID uuid) {
        try {
            return lookUpPlayerKills(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static int lookUpPlayerKills(UUID uuid) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.player_data_table + " WHERE uuid=?");
        statement.setString(1, uuid.toString());
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getInt("kills");
    }
}
