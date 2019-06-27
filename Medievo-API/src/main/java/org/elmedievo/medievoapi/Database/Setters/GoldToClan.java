package org.elmedievo.medievoapi.Database.Setters;

import org.elmedievo.medievoapi.Database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.elmedievo.medievoapi.MedievoAPI.sql;

public class GoldToClan {
    public static void addGoldToClan(String clan, String material, Integer amount) {
        try {
            lookUpClanGold(clan, material, amount);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpClanGold(String clan, String material, Integer amount) throws SQLException {
        PreparedStatement statement = Connect.getConnection().prepareStatement("SELECT * FROM " + sql.clans_economy_data_table+ " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        results.next();
        int actualMaterialAmount = results.getInt(material);

        PreparedStatement insert = Connect.getConnection().prepareStatement("UPDATE " + sql.clans_economy_data_table + " SET " + material + "=?" + " WHERE clan=?");
        insert.setInt(1, actualMaterialAmount + amount);
        insert.setString(2, clan);
        insert.executeUpdate();
    }
}
