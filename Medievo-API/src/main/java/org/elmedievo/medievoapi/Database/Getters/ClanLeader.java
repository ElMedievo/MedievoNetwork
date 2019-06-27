package org.elmedievo.medievoapi.Database.Getters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class ClanLeader {
    public static String getClanLeaderUUID(String clanName) {
        try {
            return lookupClanLeaderUUID(clanName);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String getClanLeaderName(String clanName) {
        try {
            return lookupClanLeaderName(clanName);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static String lookupClanLeaderUUID(String clanName) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.clans_data_table + " WHERE name=?");
        statement.setString(1, clanName);
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getString("leader_uuid");
    }

    private static String lookupClanLeaderName(String clanName) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.clans_data_table + " WHERE name=?");
        statement.setString(1, clanName);
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getString("leader_name");
    }
}
