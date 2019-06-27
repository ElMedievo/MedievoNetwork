package org.elmedievo.medievoapi.Database.Destroys;

import org.elmedievo.medievoapi.Database.Connect;
import org.elmedievo.medievoapi.MedievoAPI;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class ClanDestroy {
    public static void destroyClanInSQL(String name) {
        try {
            deleteClanInSQL(name);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void deleteClanInSQL(String name) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("UPDATE " + sql.player_data_table + " SET clan=? WHERE clan=?");
        statement.setString(1, "none");
        statement.setString(2, name);
        statement.executeUpdate();

        PreparedStatement statement1 = Connect.getConnection().prepareStatement("DELETE FROM " + sql.clans_data_table + " WHERE name=?");
        statement1.setString(1, name);
        statement1.executeUpdate();

        PreparedStatement statement2 = Connect.getConnection().prepareStatement("DELETE FROM " + sql.clans_economy_data_table + " WHERE clan=?");
        statement2.setString(1, name);
        statement2.executeUpdate();
    }
}